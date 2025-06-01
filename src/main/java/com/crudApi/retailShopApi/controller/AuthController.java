package com.crudApi.retailShopApi.controller;

import com.crudApi.retailShopApi.entity.UserEntity;
import com.crudApi.retailShopApi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login (@RequestBody UserEntity userEntity) {
        try{
            Authentication authentication = auth
                    .authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUserName(), userEntity.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token",token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid username kiruba"));
        }

    }
}
