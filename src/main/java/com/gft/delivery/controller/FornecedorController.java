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

import com.gft.delivery.dto.FornecedorDto;
import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
@PreAuthorize("hasRole('LOJA')")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<FornecedorDto>> search() {	
		return new ResponseEntity<CollectionModel<FornecedorDto>>(fornecedorService.search(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<FornecedorDto>(fornecedorService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<FornecedorDto> create(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response) {
		return new ResponseEntity<FornecedorDto>(fornecedorService.save(fornecedor, response), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FornecedorDto> update(@PathVariable Long id, @Valid @RequestBody Fornecedor fornecedor) {	
		return new ResponseEntity<FornecedorDto>(fornecedorService.update(id, fornecedor), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		fornecedorService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
