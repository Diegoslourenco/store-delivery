package com.gft.delivery.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.model.ItemCompra;

public class CompraRequestDto {
	
	@NotNull
	private Fornecedor fornecedor;
	
	@NotNull
	private List<ItemCompra> itens;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}

}
