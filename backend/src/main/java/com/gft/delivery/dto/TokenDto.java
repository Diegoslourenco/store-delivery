package com.gft.delivery.dto;

/**
 * TokenDto --- represents a JSON Web Token that corresponds to an authenticated client.
 * @author    Diego da Silva Lourenco
 */

public class TokenDto {
	
	private String token;
	
	private String type = "Bearer";
	
	private String username;
	
	public TokenDto(String token, String username) {
		this.token = token;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

}
