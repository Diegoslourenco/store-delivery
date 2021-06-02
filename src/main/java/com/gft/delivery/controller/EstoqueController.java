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
import com.gft.delivery.repository.filter.ProdutoFilter;
import com.gft.delivery.service.EstoqueService;

@RestController
@RequestMapping("/api/estoques")
@PreAuthorize("hasRole('LOJA')")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<EstoqueDto>> search(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.search(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/quantidade/asc")
	public ResponseEntity<CollectionModel<EstoqueDto>> searchWithQuantityAsc(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.searchWithQuantityAsc(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/quantidade/desc")
	public ResponseEntity<CollectionModel<EstoqueDto>> searchWithQuantityDesc(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.searchWithQuantityDesc(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/preco/asc")
	public ResponseEntity<CollectionModel<EstoqueDto>> searchWithPriceAsc(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.searchWithPriceAsc(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/preco/desc")
	public ResponseEntity<CollectionModel<EstoqueDto>> searchWithPriceDesc(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<EstoqueDto>>(estoqueService.searchWithPriceDesc(filter), HttpStatus.OK);	
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
