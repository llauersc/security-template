package com.example.server.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.auth.dto.LoginRequest;
import com.example.server.auth.dto.LogoutRequest;
import com.example.server.auth.dto.RefreshTokenRequiest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  
  @PostMapping("/login")
  public void login(@RequestBody LoginRequest loginRequest) {

  }

  @PostMapping("/logout")
  public void logout(@RequestBody LogoutRequest logoutRequest) {

  }

  @PostMapping("token/refresh")
  public void refresh(@RequestBody RefreshTokenRequiest refreshTokenRequiest) {
    
  }
}
