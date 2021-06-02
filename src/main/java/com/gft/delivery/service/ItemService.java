package com.gft.delivery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.delivery.exceptionhandler.ItemPriceNotNegativeOrZeroException;
import com.gft.delivery.exceptionhandler.ItemQuantityNotNegativeOrZeroException;
import com.gft.delivery.model.Compra;
import com.gft.delivery.model.ItemCompra;
import com.gft.delivery.model.ItemVenda;
import com.gft.delivery.model.Venda;
import com.gft.delivery.repository.ItemCompraRepository;
import com.gft.delivery.repository.ItemVendaRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemCompraRepository itensCompra;
	
	@Autowired
	private ItemVendaRepository itensVenda;
	
	@Autowired
	private EstoqueService estoqueService;
	
	public void saveCompra(ItemCompra itemCompra) {	
		itensCompra.save(itemCompra);	
	}
	
	public void saveVenda(ItemVenda itemVenda) {	
		itensVenda.save(itemVenda);	
	}
	
	public void saveItemCompraList(List<ItemCompra> itens, Compra compra) {
		
		for (ItemCompra itemCompra : itens) {
			
			checkValidItemCompra(itemCompra);
			
			itemCompra.setCompra(compra);
			this.saveCompra(itemCompra);
			
			if (estoqueService.estoqueExists(itemCompra.getProduto().getId())) {
				
				estoqueService.updateQuantity(itemCompra.getProduto().getId(), itemCompra.getQuantity());
			}
			else {			
				estoqueService.save(itemCompra);
			}	
		}	
	}

	public List<ItemVenda> saveItemVendaList(List<ItemVenda> itens, Venda venda) {
		
		for (ItemVenda itemVenda : itens) {
			
			checkValidItemVenda(itemVenda);
			
			BigDecimal price = estoqueService.getByProdutoId(itemVenda.getProduto().getId()).get().getSellingPrice();
			
			itemVenda.setVenda(venda);
			itemVenda.setPrice(price);
			this.saveVenda(itemVenda);
			
			estoqueService.updateQuantity(itemVenda.getProduto().getId(), -itemVenda.getQuantity());
		}
		
		return itens;	
	}
	
	private void checkValidItemVenda(ItemVenda itemVenda) {
		if (itemVenda.getQuantity() < 1) {
			throw new ItemQuantityNotNegativeOrZeroException();
		}		
	}

	private void checkValidItemCompra(ItemCompra itemCompra) {
		
		if (itemCompra.getQuantity() < 1) {
			throw new ItemQuantityNotNegativeOrZeroException();
		}
		
		if (itemCompra.getPrice().compareTo(BigDecimal.ZERO) != 1) {
			throw new ItemPriceNotNegativeOrZeroException();
		}
	}

}
