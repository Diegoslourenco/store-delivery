package com.gft.delivery.dto;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.gft.delivery.model.Address;

/**
 * ClienteRequestDto --- represents a request to create a new client.
 * @author    Diego da Silva Lourenco
 */

public class ClienteRequestDto {
	
	@NotBlank
	@CPF
	private String cpf;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	@Email
	private String email;
	
	@Embedded
	private Address address;
	
	@NotBlank
	private String password;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
