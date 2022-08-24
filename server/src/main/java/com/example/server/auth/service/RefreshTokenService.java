package com.example.server.auth.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.auth.entity.RefreshToken;
import com.example.server.auth.exception.CustomException;
import com.example.server.auth.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken generateRefreshToken() {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID().toString());

    return refreshTokenRepository.save(refreshToken);
  }

  void validateRefreshToken(String token) throws CustomException {
    refreshTokenRepository.findByToken(token)
        .orElseThrow(() -> new CustomException("Invalid refresh Token"));
  }

  public void deleteRefreshToken(String token) {
    refreshTokenRepository.deleteByToken(token);
  }
}
