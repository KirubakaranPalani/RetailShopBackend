package com.crudApi.retailShopApi.service;

import com.crudApi.retailShopApi.entity.UserEntity;
import com.crudApi.retailShopApi.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Fetch user from database
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found "));
        return new User(userEntity.getUserName(), userEntity.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
    }
}
