package com.crudApi.retailShopApi.controller;

import com.crudApi.retailShopApi.entity.UserEntity;
import com.crudApi.retailShopApi.exception.ResourceNotFoundException;
import com.crudApi.retailShopApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserEntity> getUser(){
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity){
        userEntity.setUserName(userEntity.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @GetMapping("/{id}")
    public UserEntity findUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this "+ id));
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity userEntityData){
        UserEntity data = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this "+ id));
        data.setUserName(userEntityData.getUserName());
        data.setPassword(passwordEncoder.encode(userEntityData.getPassword()));
        return userRepository.save(data);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserEntity data = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this "));
        userRepository.delete(data);
        return ResponseEntity.ok().build();
    }
}
