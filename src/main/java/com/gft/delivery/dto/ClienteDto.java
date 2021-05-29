package com.gft.delivery.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.gft.delivery.model.Address;

/**
 * ClienteDto --- represents the DTO for the resource Cliente.
 * @author    Diego da Silva Lourenco
 */

@JsonRootName(value = "cliente")
@Relation(collectionRelation = "clientes")
public class ClienteDto extends RepresentationModel<ClienteDto> {
	
	private Long id;
	private String cpf;
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
	
}
