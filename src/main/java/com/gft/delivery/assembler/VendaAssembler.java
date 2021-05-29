package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.VendaController;
import com.gft.delivery.dto.VendaDto;
import com.gft.delivery.model.Venda;

@Component
public class VendaAssembler extends RepresentationModelAssemblerSupport<Venda, VendaDto> {
	
	public VendaAssembler() {
		super(VendaController.class, VendaDto.class);
	}

	@Override
	public VendaDto toModel(Venda venda) {
		
		VendaDto resource = instantiateModel(venda);
				
		resource.setId(venda.getId());
		resource.setCliente(venda.getCliente());
		resource.setItens(venda.getItens());
		resource.setStatus(venda.getStatus());
		
		resource.add(linkTo(methodOn(VendaController.class)
				.getOne(venda.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<VendaDto> toCollectionModel(Iterable<? extends Venda> allVendas) {

		CollectionModel<VendaDto> resources = super.toCollectionModel(allVendas);
		
		resources.add(linkTo(methodOn(VendaController.class).search()).withSelfRel());
		
		return resources;
	}

}
