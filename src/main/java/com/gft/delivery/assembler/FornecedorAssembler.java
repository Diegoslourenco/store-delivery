package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.FornecedorController;
import com.gft.delivery.dto.FornecedorDto;
import com.gft.delivery.model.Fornecedor;

@Component
public class FornecedorAssembler extends RepresentationModelAssemblerSupport<Fornecedor, FornecedorDto> {
	
	public FornecedorAssembler() {
		super(FornecedorController.class, FornecedorDto.class);
	}

	@Override
	public FornecedorDto toModel(Fornecedor fornecedor) {
		
		FornecedorDto resource = instantiateModel(fornecedor);
		
		resource.setId(fornecedor.getId());
		resource.setCnpj(fornecedor.getCnpj());
		resource.setName(fornecedor.getName());
		resource.setPhone(fornecedor.getEmail());
		resource.setEmail(fornecedor.getEmail());
		resource.setAddress(fornecedor.getAddress());

		resource.add(linkTo(methodOn(FornecedorController.class)
				.getOne(fornecedor.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<FornecedorDto> toCollectionModel(Iterable<? extends Fornecedor> allFornecedores) {
	
		CollectionModel<FornecedorDto> resources = super.toCollectionModel(allFornecedores);
		
		resources.add(linkTo(methodOn(FornecedorController.class).search()).withSelfRel());
		
		return resources;
	}
	
}
