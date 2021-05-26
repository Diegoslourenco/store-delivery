package com.gft.delivery.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoPriceDto {
	
	@NotNull
	@Positive
	private Double price;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
