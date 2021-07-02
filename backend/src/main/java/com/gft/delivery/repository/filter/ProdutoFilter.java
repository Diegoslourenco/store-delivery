package com.gft.delivery.repository.filter;

/**
 * ProdutoFilter --- represents a filter for the resource Produto.
 * @author    Diego da Silva Lourenco
 */

public class ProdutoFilter {
	
	private String name;
	
	private String description;
	
	private String unit;

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
