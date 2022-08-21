package com.example.server.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  
  @PostMapping("/login")
  public void login() {

  }

  @PostMapping("/logout")
  public void logout() {

  }

  @PostMapping("token/refresh")
  public void refresh() {
    
  }
}
