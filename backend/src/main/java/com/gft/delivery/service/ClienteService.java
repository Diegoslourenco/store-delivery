package com.gft.delivery.service;

import java.util.Comparator;
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
import com.gft.delivery.dto.PasswordDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ClienteCpfNotUniqueException;
import com.gft.delivery.exceptionhandler.ClienteEmailNotUniqueException;
import com.gft.delivery.exceptionhandler.ClienteNotFoundException;
import com.gft.delivery.exceptionhandler.ClienteNotSameException;
import com.gft.delivery.exceptionhandler.PasswordNotSameException;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.ClienteRepository;
import com.gft.delivery.repository.filter.ClienteFilter;

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
	
	public CollectionModel<ClienteDto> search(ClienteFilter filter) {
		return clienteAssembler.toCollectionModel(checkEmptyList(clientes.filter(filter)));
	}
	
	public CollectionModel<ClienteDto> searchWithNameAsc(ClienteFilter filter) {
		
		List<Cliente> allClientes = clientes.filter(filter);
		
		allClientes.sort(Comparator.comparing(Cliente::getName));		
		
		return	clienteAssembler.toCollectionModel(checkEmptyList(allClientes));
	}
	
	public CollectionModel<ClienteDto> searchWithNameDesc(ClienteFilter filter) {
		
		List<Cliente> allClientes = clientes.filter(filter);
		
		allClientes.sort(Comparator.comparing(Cliente::getName).reversed());		
		
		return	clienteAssembler.toCollectionModel(checkEmptyList(allClientes));
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
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		checkSameClient(id, user);
		
		Cliente clienteSaved = getById(id);
		
		checkUniqueCliente(id, cliente);
		
		BeanUtils.copyProperties(cliente, clienteSaved, "id");
		
		usuarioService.update(clienteSaved);
		
		return clienteAssembler.toModel(clientes.save(clienteSaved));
	}
	
	private void checkSameClient(Long id, UserDetails user) {
		
		Cliente clienteSaved = getById(id);
		
		String userEmail = user.getUsername();
		
		if (!clienteSaved.getEmail().equals(userEmail)) {
			throw new ClienteNotSameException();
		}
	}

	public void updatePassword(Long id, PasswordDto passwordDto) {
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		checkSameClient(id, user);	
		
		if (!passwordDto.getPassword().equals(passwordDto.getConfirmation())) {
			throw new PasswordNotSameException();
		}
		
		String userEmail = user.getUsername();

		usuarioService.updatePassword(passwordDto, userEmail);
	}

	public void delete(Long id) {
		clientes.deleteById(id);
	}
	
	private List<Cliente> checkEmptyList(List<Cliente> list) {

		if (list.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		
		return list;
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
	
	private Cliente getById(Long id) {
		Optional<Cliente> clienteSaved = clientes.findById(id);
		
		if (clienteSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return clienteSaved.get();
	}
	
}
