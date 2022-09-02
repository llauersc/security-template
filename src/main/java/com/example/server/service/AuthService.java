package com.example.server.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.dto.AuthenticationResponse;
import com.example.server.dto.RefreshTokenRequest;
import com.example.server.dto.RegisterRequest;
import com.example.server.entity.User;
import com.example.server.exception.CustomException;
import com.example.server.repository.UserRepository;
import com.example.server.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;

  public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws CustomException {
    try {
      refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
    } catch (CustomException ex) {
      throw new CustomException(ex.getMessage());
    }
    
    String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
    return AuthenticationResponse.builder()
        .authenticationToken(token)
        .refreshToken(refreshTokenRequest.getRefreshToken())
        .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMs()))
        .username(refreshTokenRequest.getUsername())
        .build();
  }

  @Transactional
  public void signup(RegisterRequest registerRequest) {

    User user = User.builder()
        .username(registerRequest.getUsername())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .email(registerRequest.getEmail())
        .enabled(true)
        .build();

    userRepository.save(user);
  }
}
