package com.gft.delivery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.EstoqueAssembler;
import com.gft.delivery.dto.EstoqueDto;
import com.gft.delivery.dto.ProdutoPriceDto;
import com.gft.delivery.model.Estoque;
import com.gft.delivery.model.Item;
import com.gft.delivery.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	EstoqueAssembler estoqueAssembler;
	
	@Autowired
	private EstoqueRepository estoques;
	
	public CollectionModel<EstoqueDto> search() {
		return estoqueAssembler.toCollectionModel(estoques.findAll());
	}

	public EstoqueDto getOne(Long id) {
		return estoqueAssembler.toModel(getById(id));
	}
	
	public void save(Item item) {
		
		Estoque estoque = new Estoque();
		
		estoque.setProduto(item.getProduto());
		estoque.setQuantity(item.getQuantity());
		estoque.setSellingPrice(item.getPrice());
			
		estoques.save(estoque);
	}
	
	public EstoqueDto updatePrice(Long id, ProdutoPriceDto price) {
		
		Estoque estoqueSaved = getById(id);	
		estoqueSaved.setSellingPrice(price.getPrice());
		
		return estoqueAssembler.toModel(estoques.save(estoqueSaved));
	}
	
	public void updateQuantity(Long produtoId, int quantity) {
		
		Estoque estoqueSaved = estoques.findByProdutoId(produtoId).get();
		
		estoqueSaved.setQuantity(estoqueSaved.getQuantity() + quantity);
		
		estoques.save(estoqueSaved);
	}
	
	public boolean estoqueExists(Long produtoId) {
		
		if (estoques.findByProdutoId(produtoId).isEmpty()) {
			return false;
		}
		
		return true;	
	}

	private Estoque getById(Long id) {
		Optional<Estoque> starterSaved = estoques.findById(id);
		
		if (starterSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return starterSaved.get();
	}

}
