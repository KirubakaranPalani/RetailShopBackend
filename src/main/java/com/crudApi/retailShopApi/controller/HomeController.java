package com.crudApi.retailShopApi.controller;

import com.crudApi.retailShopApi.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@ResponseBody
public class HomeController {

    @GetMapping("/dashboard")
    public String getDashboardPage(){
        return "Welcome to the dashboard page";
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "Welcome to the home page";
    }
}
