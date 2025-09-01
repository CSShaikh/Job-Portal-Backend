package com.jobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.Dto.AuthResponseDTO;
import com.jobPortal.Dto.LoginRequestDTO;
import com.jobPortal.Enums.Role;
import com.jobPortal.Models.User;
import com.jobPortal.Repository.UserRepository;
import com.jobPortal.Security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtService helper;

	@Autowired
	private AuthenticationManager manager;

	// ---------- REGISTER API ----------
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {

		// check if email already exists
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			return new ResponseEntity<>("Email already registered!", HttpStatus.BAD_REQUEST);
		}

		// encode password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// default role if null
		if (user.getRole() == null) {
			user.setRole(Role.JOBSEEKER);
		}

		User savedUser = userRepository.save(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		AuthResponseDTO response = AuthResponseDTO.builder().token(token).username(userDetails.getUsername())
				.role(userDetails.getAuthorities().iterator().next().getAuthority()) // ROLE_JOBSEEKER etc.
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password !!");
		}
	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}
}
