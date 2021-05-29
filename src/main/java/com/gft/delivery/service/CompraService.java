package com.gft.delivery.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.CompraAssembler;
import com.gft.delivery.dto.CompraDto;
import com.gft.delivery.dto.CompraRequestDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.FornecedorNotFoundException;
import com.gft.delivery.exceptionhandler.ProdutoNotFoundException;
import com.gft.delivery.model.Compra;
import com.gft.delivery.model.Item;
import com.gft.delivery.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	CompraAssembler compraAssembler;
	
	@Autowired
	private CompraRepository compras;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<CompraDto> search() {
		return compraAssembler.toCollectionModel(compras.findAll());
	}

	public CompraDto getOne(Long id) {
		return compraAssembler.toModel(getById(id));
	}
	
	public CompraDto save(CompraRequestDto compraRequest, HttpServletResponse response) {
		
		if (!fornecedorService.fornecedorExists(compraRequest.getFornecedor().getId())) {
			throw new FornecedorNotFoundException();	
		}
		
		for (Item item : compraRequest.getItens()) {			
			if (!produtoService.produtoExists(item.getProduto().getId())) {
				throw new ProdutoNotFoundException();			
			}
		}
		
		Compra compra = new Compra();
		compra.setFornecedor(compraRequest.getFornecedor());
	
		Compra compraSaved = compras.save(compra);
				
		// Updating quantity and saving Item list
		itemService.saveList(compraRequest.getItens(), compraSaved);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, compraSaved.getId()));
		
		return compraAssembler.toModel(compraSaved);
	}

	private Compra getById(Long id) {
		Optional<Compra> starterSaved = compras.findById(id);
		
		if (starterSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return starterSaved.get();
	}

}
