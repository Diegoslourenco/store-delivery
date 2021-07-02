package com.gft.delivery.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.gft.delivery.model.ItemVenda;

public class VendaRequestDto {
	
	@NotNull
	private List<ItemVenda> itens;

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

}
