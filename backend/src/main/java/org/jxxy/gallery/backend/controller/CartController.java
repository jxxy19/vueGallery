package org.jxxy.gallery.backend.controller;

import org.jxxy.gallery.backend.domain.CartEntity;
import org.jxxy.gallery.backend.domain.ItemEntity;
import org.jxxy.gallery.backend.repository.CartRepository;
import org.jxxy.gallery.backend.repository.ItemRepository;
import org.jxxy.gallery.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    JwtService jwtService;
    CartRepository CartRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/api/cart/items")
    public ResponseEntity<List<ItemEntity>> getCartItems(@CookieValue (value = "token", required = false) String token) {
        if (jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getClaims(token);
        List<CartEntity> cartList = cartRepository.findByMemberId(memberId);
        return new ResponseEntity<List<ItemEntity>>(cartList, HttpStatus.OK);
    }

    @GetMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem(@PathVariable int itemId,
                                         @CookieValue(value = "token", required = false) String token) {
        if (jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        CartEntity cartEntity = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        if (cartEntity != null) {
            CartEntity newCart = new CartEntity();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }
        return new ResponseEntity<List<ItemEntity>>(HttpStatus.OK);
    }
}
