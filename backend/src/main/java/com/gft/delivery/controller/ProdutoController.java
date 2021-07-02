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

import com.gft.delivery.dto.ProdutoDto;
import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.filter.ProdutoFilter;
import com.gft.delivery.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<ProdutoDto>> search(ProdutoFilter filter) {	
		return new ResponseEntity<CollectionModel<ProdutoDto>>(produtoService.search(filter), HttpStatus.OK);	
	}
	
	@GetMapping("/asc")
	public ResponseEntity<CollectionModel<ProdutoDto>> searchWithNameAsc(ProdutoFilter filter) {
		return new ResponseEntity<CollectionModel<ProdutoDto>>(produtoService.searchWithNameAsc(filter), HttpStatus.OK);
	}
	
	@GetMapping("/desc")
	public ResponseEntity<CollectionModel<ProdutoDto>> searchWithNameDesc(ProdutoFilter filter) {
		return new ResponseEntity<CollectionModel<ProdutoDto>>(produtoService.searchWithNameDesc(filter), HttpStatus.OK);
	}
	
	@GetMapping("/nome/{name}")
	public ResponseEntity<CollectionModel<ProdutoDto>> searchByName(@PathVariable String name) {
		return new ResponseEntity<CollectionModel<ProdutoDto>>(produtoService.searchByName(name), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<ProdutoDto>(produtoService.getOne(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('LOJA')")
	@PostMapping
	public ResponseEntity<ProdutoDto> create(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		return new ResponseEntity<ProdutoDto>(produtoService.save(produto, response), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('LOJA')")
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @Valid @RequestBody Produto produto) {			
		return new ResponseEntity<ProdutoDto>(produtoService.update(id, produto), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('LOJA')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
