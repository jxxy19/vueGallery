package org.jxxy.gallery.backend.controller;


import org.jxxy.gallery.backend.domain.MemberEntity;
import org.jxxy.gallery.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public int login(@RequestBody Map<String, String> params) {
        MemberEntity member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if(member != null) {
            return member.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
