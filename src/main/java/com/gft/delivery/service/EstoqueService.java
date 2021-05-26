package com.gft.delivery.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.EstoqueAssembler;
import com.gft.delivery.dto.EstoqueDto;
import com.gft.delivery.dto.ProdutoPriceDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.model.Estoque;
import com.gft.delivery.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	EstoqueAssembler estoqueAssembler;
	
	@Autowired
	private EstoqueRepository estoques;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<EstoqueDto> search() {
		return estoqueAssembler.toCollectionModel(estoques.findAll());
	}

	public EstoqueDto getOne(Long id) {
		return estoqueAssembler.toModel(getById(id));
	}
	
	// TODO
	public EstoqueDto save(Estoque estoque, HttpServletResponse response) {	
			
		Estoque estoqueSaved = estoques.save(estoque);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, estoqueSaved.getId()));
		
		return estoqueAssembler.toModel(estoqueSaved);
	}
	
	public EstoqueDto update(Long id, ProdutoPriceDto price) {
		
		Estoque estoqueSaved = getById(id);	
		estoqueSaved.setSellingPrice(price.getPrice());
		
		return estoqueAssembler.toModel(estoques.save(estoqueSaved));
	}
	
	// TODO
	public void delete(Long id) {
		estoques.deleteById(id);
	}

	private Estoque getById(Long id) {
		Optional<Estoque> starterSaved = estoques.findById(id);
		
		if (starterSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return starterSaved.get();
	}

}
