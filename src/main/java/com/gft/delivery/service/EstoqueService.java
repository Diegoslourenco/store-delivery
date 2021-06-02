package com.gft.delivery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.EstoqueAssembler;
import com.gft.delivery.dto.EstoqueDto;
import com.gft.delivery.dto.ProdutoPriceDto;
import com.gft.delivery.exceptionhandler.EstoqueNotFoundException;
import com.gft.delivery.model.Estoque;
import com.gft.delivery.model.ItemCompra;
import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.EstoqueRepository;
import com.gft.delivery.repository.ProdutoRepository;
import com.gft.delivery.repository.filter.ProdutoFilter;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueAssembler estoqueAssembler;
	
	@Autowired
	private EstoqueRepository estoques;
	
	@Autowired
	private ProdutoRepository produtos;
	
	public CollectionModel<EstoqueDto> search(ProdutoFilter filter) {	
		return estoqueAssembler.toCollectionModel(checkEmptyList(filterByProduct(filter)));
	}

	public CollectionModel<EstoqueDto> searchWithQuantityAsc(ProdutoFilter filter) {
				
		List<Estoque> allEstoques = filterByProduct(filter);
		
		allEstoques.sort(Comparator.comparing(Estoque::getQuantity));
		
		return estoqueAssembler.toCollectionModel(checkEmptyList(allEstoques));
	}
	
	public CollectionModel<EstoqueDto> searchWithQuantityDesc(ProdutoFilter filter) {
		
		List<Estoque> allEstoques = filterByProduct(filter);
		
		allEstoques.sort(Comparator.comparing(Estoque::getQuantity).reversed());
		
		return estoqueAssembler.toCollectionModel(checkEmptyList(allEstoques));
	}
	
	public CollectionModel<EstoqueDto> searchWithPriceAsc(ProdutoFilter filter) {
		
		List<Estoque> allEstoques = filterByProduct(filter);
		
		allEstoques.sort(Comparator.comparing(Estoque::getSellingPrice));
		
		return estoqueAssembler.toCollectionModel(checkEmptyList(allEstoques));
	}
	
	public CollectionModel<EstoqueDto> searchWithPriceDesc(ProdutoFilter filter) {
		
		List<Estoque> allEstoques = filterByProduct(filter);
		
		allEstoques.sort(Comparator.comparing(Estoque::getSellingPrice).reversed());
		
		return estoqueAssembler.toCollectionModel(checkEmptyList(allEstoques));
	}

	public EstoqueDto getOne(Long id) {
		return estoqueAssembler.toModel(getById(id));
	}
	
	public void save(ItemCompra itemCompra) {
		
		estoques.save(new Estoque(itemCompra.getProduto(),
								itemCompra.getQuantity(),
								BigDecimal.ZERO));
	}
	
	public EstoqueDto updatePrice(Long id, ProdutoPriceDto price) {
		
		Estoque estoqueSaved = getById(id);	
		estoqueSaved.setSellingPrice(price.getPrice());
		
		return estoqueAssembler.toModel(estoques.save(estoqueSaved));
	}
	
	public void updateQuantity(Long produtoId, int quantity) {
		
		Estoque estoqueSaved = estoques.findByProdutoId(produtoId).get();
		
		// For a buy it increases and for a sell it decreases the quantity
		estoqueSaved.setQuantity(estoqueSaved.getQuantity() + quantity);
		
		estoques.save(estoqueSaved);
	}
	
	public boolean estoqueExists(Long produtoId) {
		
		if (this.getByProdutoId(produtoId).isEmpty()) {
			return false;
		}
		
		return true;	
	}

	public Estoque getById(Long id) {
		Optional<Estoque> estoqueSaved = estoques.findById(id);
		
		if (estoqueSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return estoqueSaved.get();
	}
	
	public Optional<Estoque> getByProdutoId(Long produtoId) {
		return estoques.findByProdutoId(produtoId);
	}
	
	private List<Estoque> checkEmptyList(List<Estoque> list) {

		if (list.isEmpty()) {
			throw new EstoqueNotFoundException();
		}
		
		return list;
	}
	
	private List<Estoque> filterByProduct(ProdutoFilter filter) {
		
		List<Produto> allProdutos = produtos.filter(filter);

		List<Estoque> allEstoques = new ArrayList<>();
		
		for (Produto produto : allProdutos) {
			allEstoques.add(estoques.findByProdutoId(produto.getId()).get());
		}
		
		return allEstoques;
	}
	
}
