package com.gft.delivery.model;

/**
 * TypeTransaction --- contains the possible types for a transaction.
 * @author    Diego da Silva Lourenco
 */

public enum TypeTransaction {
	
	COMPRA("compra"),
	VENDA("venda");
	
	private String transaction;
	
	TypeTransaction(String transaction) {
		this.transaction = transaction;
	}
	
	public String getTransaction() {
		return transaction;
	}

}
