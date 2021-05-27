package com.gft.delivery.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.ClienteDto;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<ClienteDto>> search() {	
		return new ResponseEntity<CollectionModel<ClienteDto>>(clienteService.search(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<ClienteDto>(clienteService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> create(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		return new ResponseEntity<ClienteDto>(clienteService.save(cliente, response), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {	
		return new ResponseEntity<ClienteDto>(clienteService.update(id, cliente), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}