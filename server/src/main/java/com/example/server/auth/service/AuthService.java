package com.example.server.auth.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.auth.dto.AuthenticationResponse;
import com.example.server.auth.dto.RefreshTokenRequest;
import com.example.server.auth.dto.RegisterRequest;
import com.example.server.auth.entity.User;
import com.example.server.auth.exception.CustomException;
import com.example.server.auth.repository.UserRepository;
import com.example.server.auth.security.JwtProvider;

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
    User user = new User(
        registerRequest.getUsername(),
        passwordEncoder.encode(registerRequest.getPassword()),
        registerRequest.getEmail(),
        true);

    userRepository.save(user);
  }
}
