package com.gft.delivery.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.ClienteAssembler;
import com.gft.delivery.dto.ClienteDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ClienteCpfNotUniqueException;
import com.gft.delivery.exceptionhandler.ClienteEmailNotUniqueException;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteAssembler clienteAssembler;
	
	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<ClienteDto> search() {
		return clienteAssembler.toCollectionModel(clientes.findAll());
	}

	public ClienteDto getOne(Long id) {
		return clienteAssembler.toModel(getById(id));
	}
	
	public ClienteDto save(Cliente cliente, HttpServletResponse response) {
		
		checkUniqueCliente(cliente);
			
		Cliente clienteSaved = clientes.save(cliente);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, clienteSaved.getId()));
		
		return clienteAssembler.toModel(clienteSaved);
	}

	public ClienteDto update(Long id, Cliente cliente) {
		
		checkUniqueCliente(id, cliente);

		Cliente clienteSaved = getById(id);
		
		BeanUtils.copyProperties(cliente, clienteSaved, "id");
		
		return clienteAssembler.toModel(clientes.save(clienteSaved));
	}

	public void delete(Long id) {
		clientes.deleteById(id);
	}

	private Cliente getById(Long id) {
		Optional<Cliente> clienteSaved = clientes.findById(id);
		
		if (clienteSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return clienteSaved.get();
	}
	
	private void checkUniqueCliente(Cliente novoCliente) {
	
		List<Cliente> allClientes = clientes.findAll();
		
		for (Cliente cliente : allClientes) {
			checkFieldsCliente(cliente, novoCliente);
		}
	}
	
	private void checkUniqueCliente(Long id, Cliente novoCliente) {
		
		List<Cliente> allClientes = clientes.findAll();
		
		for (Cliente cliente : allClientes) {
			
			if (!cliente.getId().equals(id)) {
				checkFieldsCliente(cliente, novoCliente);
			}	
		}
	}
	
	private void checkFieldsCliente(Cliente cliente, Cliente novoCliente) {
		
		if (cliente.getCpf().equals(novoCliente.getCpf())) {
			throw new ClienteCpfNotUniqueException();
		}	
		
		if (cliente.getEmail().equals(novoCliente.getEmail())) {
			throw new ClienteEmailNotUniqueException();
		}	
	}
	
}
