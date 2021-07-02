package com.gft.delivery.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

/**
 * Address --- represents the address details for a Fornecedor or Cliente.
 * @author    Diego da Silva Lourenco
 */

@Embeddable
public class Address {
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	private String complement;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getComplement() {
		return complement;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
}
