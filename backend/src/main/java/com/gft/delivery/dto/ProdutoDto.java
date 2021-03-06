package com.gft.delivery.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * ProdutoDto --- represents the DTO for the resource Produto.
 * @author    Diego da Silva Lourenco
 */

@JsonRootName(value = "produto")
@Relation(collectionRelation = "produtos")
public class ProdutoDto extends RepresentationModel<ProdutoDto> {
	
	private Long id;	
	private String name;	
	private String description;
	private String unit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
