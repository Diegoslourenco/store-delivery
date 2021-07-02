package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.EstoqueController;
import com.gft.delivery.dto.EstoqueDto;
import com.gft.delivery.model.Estoque;

@Component
public class EstoqueAssembler extends RepresentationModelAssemblerSupport<Estoque, EstoqueDto> {
	
	public EstoqueAssembler() {
		super(EstoqueController.class, EstoqueDto.class);
	}

	@Override
	public EstoqueDto toModel(Estoque estoque) {
		
		EstoqueDto resource = instantiateModel(estoque);
				
		resource.setId(estoque.getId());
		resource.setProduto(estoque.getProduto());
		resource.setQuantity(estoque.getQuantity());
		resource.setSellingPrice(estoque.getSellingPrice());
		
		resource.add(linkTo(methodOn(EstoqueController.class)
				.getOne(estoque.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<EstoqueDto> toCollectionModel(Iterable<? extends Estoque> allEstoques) {

		CollectionModel<EstoqueDto> resources = super.toCollectionModel(allEstoques);
		
		resources.add(linkTo(methodOn(EstoqueController.class).search(null)).withSelfRel());
		
		return resources;
	}
	
}
