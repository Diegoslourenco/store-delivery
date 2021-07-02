package com.gft.delivery.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoPriceDto {
	
	@NotNull
	@Positive
	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
