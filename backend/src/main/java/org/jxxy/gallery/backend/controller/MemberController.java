package org.jxxy.gallery.backend.controller;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.jxxy.gallery.backend.domain.MemberEntity;
import org.jxxy.gallery.backend.repository.MemberRepository;
import org.jxxy.gallery.backend.service.JwtService;
import org.jxxy.gallery.backend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse res) {
        MemberEntity member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if(member != null) {
            JwtService jwtService = new JwtServiceImpl();
            int id =  member.getId();
            String token = jwtService.getToken("id", id);
//            쿠키 값을 넣어주기
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

//            return ResponseEntity.ok().build();
            return new ResponseEntity<>(id, HttpStatus.OK);
//            응답 값을 id 값을 줌

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if(claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(memberRepository.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
