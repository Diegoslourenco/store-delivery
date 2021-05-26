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

import com.gft.delivery.dto.ProdutoDto;
import com.gft.delivery.model.Produto;
import com.gft.delivery.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<ProdutoDto>> search() {	
		return new ResponseEntity<CollectionModel<ProdutoDto>>(produtoService.search(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<ProdutoDto>(produtoService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> create(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		return new ResponseEntity<ProdutoDto>(produtoService.save(produto, response), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @Valid @RequestBody Produto produto) {			
		return new ResponseEntity<ProdutoDto>(produtoService.update(id, produto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
