package com.gft.delivery.dto;

import javax.validation.constraints.NotBlank;

public class PasswordDto {
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String confirmation;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

}
