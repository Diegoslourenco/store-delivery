package com.gft.delivery.dto;

import org.springframework.hateoas.RepresentationModel;

import com.gft.delivery.model.Address;

/**
 * FornecedorDto --- represents the DTO for the resource Fornecedor.
 * @author    Diego da Silva Lourenco
 */

public class FornecedorDto extends RepresentationModel<FornecedorDto>  {
	
	private Long id;
	private String cnpj;
	private String name;
	private String phone;
	private String email;
	private Address address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
}
