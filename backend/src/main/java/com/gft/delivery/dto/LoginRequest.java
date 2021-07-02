package com.gft.delivery.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * LoginRequest --- represents a login request from a client.
 * @author    Diego da Silva Lourenco
 */

public class LoginRequest {

	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
