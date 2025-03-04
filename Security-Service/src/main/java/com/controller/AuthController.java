package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AuthRequest;
import com.entity.UserInfo;
import com.repository.UserInfoRepository;
import com.service.JwtService;
import com.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService service;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserInfoRepository repo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome!! Login as ADMIN or MANAGER";
	}

	@PostMapping("/new") // http://localhost:9090/auth/new
	public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
		String result = service.addUser(userInfo);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		logger.info("Authentication request for user: {}", authRequest.getUsername());

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				UserInfo userInfo = repo.findByName(authRequest.getUsername())
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));

				String jwtToken = jwtService.generateToken(authRequest.getUsername(), userInfo.getRoles());

				Map<String, String> response = new HashMap<>();
				response.put("token", jwtToken);
				response.put("role", userInfo.getRoles());

				logger.info("Authentication successful for user: {}", authRequest.getUsername());
				return ResponseEntity.ok(response);
			} else {
				logger.warn("Authentication failed for user: {}", authRequest.getUsername());
				throw new UsernameNotFoundException("Invalid user request!");
			}
		} catch (UsernameNotFoundException e) {
			logger.error("User not found or invalid credentials for user: {}", authRequest.getUsername(), e);
			return ResponseEntity.badRequest().body(Map.of("error", "Invalid username or password"));
		} catch (Exception e) {
			logger.error("Authentication error for user: {}", authRequest.getUsername(), e);
			return ResponseEntity.internalServerError().body(Map.of("error", "Authentication error"));
		}
	}

	@GetMapping("/getroles/{username}")
	public String getRoles(@PathVariable String username) {
		return service.getRoles(username);
	}
}
