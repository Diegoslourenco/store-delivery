package com.gft.delivery.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.model.ItemCompra;

/**
 * CompraDto --- represents the DTO for the resource Compra.
 * @author    Diego da Silva Lourenco
 */

@JsonRootName(value = "compra")
@Relation(collectionRelation = "compras")
public class CompraDto extends RepresentationModel<CompraDto> {
	
	private Long id;
	private Fornecedor fornecedor;
	private List<ItemCompra> itens;
	
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
	
	public List<ItemCompra> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}
	
}
