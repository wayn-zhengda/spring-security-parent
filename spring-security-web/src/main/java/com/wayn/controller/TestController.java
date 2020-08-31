package com.wayn.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello word";
    }

    @RequestMapping("/user")
    public Object currentUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
    @RequestMapping("/user2")
    public Object currentUserInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
