package com.gft.delivery.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.ClienteAssembler;
import com.gft.delivery.dto.ClienteDto;
import com.gft.delivery.dto.ClienteRequestDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ClienteCpfNotUniqueException;
import com.gft.delivery.exceptionhandler.ClienteEmailNotUniqueException;
import com.gft.delivery.exceptionhandler.ClienteNotSameException;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteAssembler clienteAssembler;
	
	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<ClienteDto> search() {
		return clienteAssembler.toCollectionModel(clientes.findAll());
	}

	public ClienteDto getOne(Long id) {
		return clienteAssembler.toModel(getById(id));
	}
	
	public ClienteDto save(ClienteRequestDto clienteRequestDto, HttpServletResponse response) {
		
		Cliente novoCliente = new Cliente(clienteRequestDto.getCpf(), clienteRequestDto.getName(), clienteRequestDto.getPhone(),
										clienteRequestDto.getEmail(), clienteRequestDto.getAddress());;
		
		checkUniqueCliente(novoCliente);
			
		Cliente clienteSaved = clientes.save(novoCliente);
		
		usuarioService.save(novoCliente, clienteRequestDto.getPassword());
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, clienteSaved.getId()));
		
		return clienteAssembler.toModel(clienteSaved);
	}

	public ClienteDto update(Long id, Cliente cliente) {
		
		Cliente clienteSaved = getById(id);
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userEmail = user.getUsername();
		
		if (!clienteSaved.getEmail().equals(userEmail)) {
			throw new ClienteNotSameException();
		}
		
		checkUniqueCliente(id, cliente);
		
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
