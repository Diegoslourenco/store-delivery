package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.CompraController;
import com.gft.delivery.dto.CompraDto;
import com.gft.delivery.model.Compra;

@Component
public class CompraAssembler extends RepresentationModelAssemblerSupport<Compra, CompraDto> {
	
	public CompraAssembler() {
		super(CompraController.class, CompraDto.class);
	}

	@Override
	public CompraDto toModel(Compra compra) {
		
		CompraDto resource = instantiateModel(compra);
				
		resource.setId(compra.getId());
		resource.setFornecedor(compra.getFornecedor());
		resource.setItens(compra.getItens());
		
		resource.add(linkTo(methodOn(CompraController.class)
				.getOne(compra.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<CompraDto> toCollectionModel(Iterable<? extends Compra> allCompras) {

		CollectionModel<CompraDto> resources = super.toCollectionModel(allCompras);
		
		resources.add(linkTo(methodOn(CompraController.class).search()).withSelfRel());
		
		return resources;
	}
	
}
