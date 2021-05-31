package com.gft.delivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.LoginRequest;
import com.gft.delivery.dto.TokenDto;
import com.gft.delivery.exceptionhandler.AuthException;
import com.gft.delivery.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(loginRequest.convert());
			
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, loginRequest.getEmail()));
		
		} catch (AuthenticationException e) {
			
			throw new AuthException();
		}
	}
	

}
