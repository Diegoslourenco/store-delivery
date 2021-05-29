package com.gft.delivery.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.VendaAssembler;
import com.gft.delivery.dto.VendaDto;
import com.gft.delivery.dto.VendaRequestDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.EstoqueNotEnoughException;
import com.gft.delivery.exceptionhandler.EstoqueNotFoundException;
import com.gft.delivery.exceptionhandler.ProdutoNotFoundException;
import com.gft.delivery.model.ItemVenda;
import com.gft.delivery.model.Venda;
import com.gft.delivery.model.VendaStatus;
import com.gft.delivery.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	VendaAssembler vendaAssembler;
	
	@Autowired
	VendaRepository vendas;
	
	@Autowired
	EstoqueService estoqueService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<VendaDto> search() {
		return vendaAssembler.toCollectionModel(vendas.findAll());
	}

	public VendaDto getOne(Long id) {
		return vendaAssembler.toModel(getById(id));
	}
	
	public VendaDto save(VendaRequestDto vendaRequest, HttpServletResponse response) {
		
		for (ItemVenda itemVenda : vendaRequest.getItens()) {
			
			Long itemId = itemVenda.getProduto().getId();
			
			if (!produtoService.produtoExists(itemId)) {
				throw new ProdutoNotFoundException();			
			}
			
			if (!estoqueService.estoqueExists(itemId)) {
				throw new EstoqueNotFoundException();				
			}
			
			if ((estoqueService.getById(itemId).getQuantity() - itemVenda.getQuantity()) < 0) {
				throw new EstoqueNotEnoughException();
			}
		}
		
		Venda venda = new Venda();
		venda.setCliente(vendaRequest.getCliente());
		venda.setStatus(VendaStatus.PENDENTE);
	
		Venda vendaSaved = vendas.save(venda);
				
		// Updating quantity and saving ItemCompra list
		itemService.saveItemVendaList(vendaRequest.getItens(), vendaSaved);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, vendaSaved.getId()));
		
		return vendaAssembler.toModel(vendaSaved);
	}
	
	public VendaDto update(Long id) {
				
		Venda vendaSaved = getById(id);
		vendaSaved.setStatus(VendaStatus.CONCLUIDO);
		
		return vendaAssembler.toModel(vendas.save(vendaSaved));
	}
	
	private Venda getById(Long id) {
		Optional<Venda> vendaSaved = vendas.findById(id);
		
		if (vendaSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return vendaSaved.get();
	}

}
