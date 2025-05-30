package com.crudApi.retailShopApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    @GetMapping("/findAll")
    public String findAll(){
      return "API Working";
    };
}
