package com.gft.delivery.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.ClienteDto;
import com.gft.delivery.dto.ClienteRequestDto;
import com.gft.delivery.dto.PasswordDto;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.filter.ClienteFilter;
import com.gft.delivery.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping
	public ResponseEntity<CollectionModel<ClienteDto>> search(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<ClienteDto>>(clienteService.search(filter), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/nome/asc")
	public ResponseEntity<CollectionModel<ClienteDto>> searchWithNameAsc(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<ClienteDto>>(clienteService.searchWithNameAsc(filter), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/nome/desc")
	public ResponseEntity<CollectionModel<ClienteDto>> searchWithNameDesc(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<ClienteDto>>(clienteService.searchWithNameDesc(filter), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<ClienteDto>(clienteService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteRequestDto clienteRequestDto, HttpServletResponse response) {
		return new ResponseEntity<ClienteDto>(clienteService.save(clienteRequestDto, response), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('CLIENTE')")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {	
		return new ResponseEntity<ClienteDto>(clienteService.update(id, cliente), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@PutMapping("/{id}/senha")
	public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordDto passwordDto) {	
		clienteService.updatePassword(id, passwordDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
}