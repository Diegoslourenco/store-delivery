package com.gft.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.delivery.model.Compra;
import com.gft.delivery.model.Item;
import com.gft.delivery.model.TypeTransaction;
import com.gft.delivery.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itens;
	
	@Autowired
	EstoqueService estoqueService;
	
	public void save(Item item) {	
		itens.save(item);	
	}
	
	public void saveList(List<Item> itens, Compra compra) {
		
		for (Item item : itens) {
			
			item.setCompra(compra);
			item.setTransaction(TypeTransaction.COMPRA);
			this.save(item);
			
			if (estoqueService.estoqueExists(item.getProduto().getId())) {
				
				estoqueService.updateQuantity(item.getProduto().getId(), item.getQuantity());
			}
			else {			
				estoqueService.save(item);
			}	
		}	
	}

}
