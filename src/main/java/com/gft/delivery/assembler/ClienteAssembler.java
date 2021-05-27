package com.gft.delivery.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.delivery.controller.ClienteController;
import com.gft.delivery.dto.ClienteDto;
import com.gft.delivery.model.Cliente;

@Component
public class ClienteAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteDto> {
	
	public ClienteAssembler() {
		super(ClienteController.class, ClienteDto.class);
	}

	@Override
	public ClienteDto toModel(Cliente cliente) {
		
		ClienteDto resource = instantiateModel(cliente);
		
		resource.setId(cliente.getId());
		resource.setCpf(cliente.getCpf());
		resource.setName(cliente.getName());
		resource.setPhone(cliente.getPhone());
		resource.setEmail(cliente.getEmail());
		resource.setAddress(cliente.getAddress());

		resource.add(linkTo(methodOn(ClienteController.class)
				.getOne(cliente.getId()))
				.withSelfRel());
		
		return resource;
	}
	
	@Override
	public CollectionModel<ClienteDto> toCollectionModel(Iterable<? extends Cliente> allClientes) {
	
		CollectionModel<ClienteDto> resources = super.toCollectionModel(allClientes);
		
		resources.add(linkTo(methodOn(ClienteController.class).search()).withSelfRel());
		
		return resources;
	}
	
}
