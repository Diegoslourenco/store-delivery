package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.ProdutoController;
import com.gft.delivery.dto.ProdutoDto;
import com.gft.delivery.model.Produto;

@Component
public class ProdutoAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDto> {
	
	public ProdutoAssembler() {
		super(ProdutoController.class, ProdutoDto.class);
	}

	@Override
	public ProdutoDto toModel(Produto produto) {
		
		ProdutoDto resource = instantiateModel(produto);
		
		resource.setId(produto.getId());
		resource.setName(produto.getName());
		resource.setDescription(produto.getDescription());
		resource.setUnit(produto.getUnit());
		
		resource.add(linkTo(methodOn(ProdutoController.class)
				.getOne(produto.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<ProdutoDto> toCollectionModel(Iterable<? extends Produto> allProdutos) {

		CollectionModel<ProdutoDto> resources = super.toCollectionModel(allProdutos);
		
		resources.add(linkTo(methodOn(ProdutoController.class).search(null)).withSelfRel());
		
		return resources;
	}
	
}
