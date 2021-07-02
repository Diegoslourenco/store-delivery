package com.gft.delivery.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.model.ItemVenda;
import com.gft.delivery.model.VendaStatus;

/**
 * VendaDto --- represents the DTO for the resource Venda.
 * @author    Diego da Silva Lourenco
 */

@JsonRootName(value = "venda")
@Relation(collectionRelation = "vendas")
public class VendaDto extends RepresentationModel<VendaDto> {
	
	private Long id;
	private Cliente cliente;
	private VendaStatus status;
	private List<ItemVenda> itens;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public VendaStatus getStatus() {
		return status;
	}

	public void setStatus(VendaStatus status) {
		this.status = status;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

}
