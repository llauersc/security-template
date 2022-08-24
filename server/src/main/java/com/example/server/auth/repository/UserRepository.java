package com.example.server.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.server.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserById(Long id);
  Optional<User> findByUsername(String username);
}
