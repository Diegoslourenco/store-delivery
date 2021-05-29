package com.gft.delivery.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.gft.delivery.model.Cliente;
import com.gft.delivery.model.ItemVenda;

public class VendaRequestDto {
	
	@NotNull
	private Cliente cliente;
	
	@NotNull
	private List<ItemVenda> itens;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

}
