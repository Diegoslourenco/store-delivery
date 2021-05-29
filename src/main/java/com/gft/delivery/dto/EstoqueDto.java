package com.gft.delivery.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.gft.delivery.model.Produto;

/**
 * EstoqueDto --- represents the DTO for the resource Estoque.
 * @author    Diego da Silva Lourenco
 */

@JsonRootName(value = "estoque")
@Relation(collectionRelation = "estoques")
public class EstoqueDto extends RepresentationModel<EstoqueDto> {
	
	private Long id;
	private Produto produto;
	private int quantity;
	private Double sellingPrice;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
}
