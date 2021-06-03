package com.gft.delivery.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.VendaDto;
import com.gft.delivery.dto.VendaRequestDto;
import com.gft.delivery.repository.filter.ClienteFilter;
import com.gft.delivery.service.VendaService;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping
	public ResponseEntity<CollectionModel<VendaDto>> search(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<VendaDto>>(vendaService.search(filter), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/concluido")
	public ResponseEntity<CollectionModel<VendaDto>> searchFinished(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<VendaDto>>(vendaService.searchFinished(filter), HttpStatus.OK);	
	}
	
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/pendente")
	public ResponseEntity<CollectionModel<VendaDto>> searchPending(ClienteFilter filter) {	
		return new ResponseEntity<CollectionModel<VendaDto>>(vendaService.searchPending(filter), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/{id}")
	public ResponseEntity<VendaDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<VendaDto>(vendaService.getOne(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CLIENTE')")
	@PostMapping
	public ResponseEntity<VendaDto> create(@Valid @RequestBody VendaRequestDto vendaRequest, HttpServletResponse response) 
			throws MessagingException {
		return new ResponseEntity<VendaDto>(vendaService.save(vendaRequest, response), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@PutMapping("/{id}/recebido")
	public ResponseEntity<VendaDto> update(@PathVariable Long id) {
		return new ResponseEntity<VendaDto>(vendaService.update(id), HttpStatus.OK);
	}

}
