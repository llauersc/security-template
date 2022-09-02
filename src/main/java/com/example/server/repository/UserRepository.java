package com.example.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserById(Long id);
  Optional<User> findByUsername(String username);
}
