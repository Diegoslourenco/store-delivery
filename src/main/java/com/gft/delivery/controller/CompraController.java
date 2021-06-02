package com.gft.delivery.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.CompraDto;
import com.gft.delivery.dto.CompraRequestDto;
import com.gft.delivery.repository.filter.FornecedorFilter;
import com.gft.delivery.service.CompraService;

@RestController
@RequestMapping("/api/compras")
@PreAuthorize("hasRole('LOJA')")
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<CompraDto>> search(FornecedorFilter filter) {	
		return new ResponseEntity<CollectionModel<CompraDto>>(compraService.search(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<CompraDto>(compraService.getOne(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CompraDto> create(@Valid @RequestBody CompraRequestDto compraRequest, HttpServletResponse response) {
		return new ResponseEntity<CompraDto>(compraService.save(compraRequest, response), HttpStatus.CREATED);
	}
	
}
