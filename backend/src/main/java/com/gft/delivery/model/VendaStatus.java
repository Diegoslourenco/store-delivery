package com.gft.delivery.model;

/**
 * VendaStatus --- contains the possible status for a Venda.
 * @author    Diego da Silva Lourenco
 */

public enum VendaStatus {
	
	PENDENTE("pendente"),
	CONCLUIDO("concluido");

	private String status;
	
	VendaStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}	

}
