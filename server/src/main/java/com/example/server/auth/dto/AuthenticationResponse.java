package com.example.server.auth.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
  private String token;
  private String refresh_token;
  private String username;
  private Instant expires_at;
}
