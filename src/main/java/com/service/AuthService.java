//package com.service;
//
//import java.util.Map;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.dto.AuthResponse;
//import com.dto.LoginRequest;
//import com.dto.RegisterRequest;
//import com.enums.Role;
//import com.model.User;
//import com.repository.UserRepository;
//import com.security.JwtService;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//	private final UserRepository userRepo;
//	private final PasswordEncoder passwordEncoder;
//	private final JwtService jwtService;
//
//	public AuthResponse register(RegisterRequest req) {
//		if (userRepo.existsByEmail(req.getEmail()))
//			throw new RuntimeException("Email already registered");
//
//		Role role = (req.getRole() == null) ? Role.JOBSEEKER : req.getRole();
//
//		User user = User.builder().username(req.getUsername()).email(req.getEmail())
//				.password(passwordEncoder.encode(req.getPassword())).role(role).build();
//
//		userRepo.save(user);
//
//		String token = jwtService.generateToken(user.getEmail(),
//				Map.of("role", user.getRole().name(), "username", user.getUsername()));
//
//		return AuthResponse.builder().token(token).role(user.getRole().name()).email(user.getEmail()).build();
//	}
//
//	public AuthResponse login(LoginRequest req) {
//		User user = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
//
//		if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
//			throw new RuntimeException("Invalid credentials");
//
//		String token = jwtService.generateToken(user.getEmail(),
//				Map.of("role", user.getRole().name(), "username", user.getUsername()));
//
//		return AuthResponse.builder().token(token).role(user.getRole().name()).email(user.getEmail()).build();
//	}
//}
