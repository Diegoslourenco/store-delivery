package com.gft.delivery.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.model.Item;

/**
 * CompraDto --- represents the DTO for the resource Compra.
 * @author    Diego da Silva Lourenco
 */

public class CompraDto extends RepresentationModel<CompraDto> {
	
	private Long id;
	private Fornecedor fornecedor;
	private List<Item> itens;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
}
