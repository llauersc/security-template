package com.example.server;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.server.auth.entity.User;
import com.example.server.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ServerApplication {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@PostConstruct
	public void UserInit() {
		List<User> users = Stream.of(
				new User("email@mail.ru", "user", passwordEncoder.encode("pass")),
				new User("email1@mail.ru", "user1", passwordEncoder.encode("pass1")))
				.collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
