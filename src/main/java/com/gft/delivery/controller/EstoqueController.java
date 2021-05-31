package com.gft.delivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.EstoqueDto;
import com.gft.delivery.dto.ProdutoPriceDto;
import com.gft.delivery.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
@PreAuthorize("hasRole('LOJA')")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<EstoqueDto>> search() {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.search(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstoqueDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<EstoqueDto>(estoqueService.getOne(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstoqueDto> updatePrice(@PathVariable Long id, @Valid @RequestBody ProdutoPriceDto price) {	
		return new ResponseEntity<EstoqueDto>(estoqueService.updatePrice(id, price), HttpStatus.OK);
	}
	
}
