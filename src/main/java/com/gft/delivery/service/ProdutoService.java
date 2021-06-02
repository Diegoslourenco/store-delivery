package com.gft.delivery.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.ProdutoAssembler;
import com.gft.delivery.dto.ProdutoDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ProdutoNameNotUniqueException;
import com.gft.delivery.exceptionhandler.ProdutoNotFoundException;
import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.ProdutoRepository;
import com.gft.delivery.repository.filter.ProdutoFilter;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoAssembler produtoAssembler;
	
	@Autowired
	private ProdutoRepository produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private final String CLIENTE = "CLIENTE";
	
	public CollectionModel<ProdutoDto> search(ProdutoFilter filter) {		
		return produtoAssembler.toCollectionModel(
				checkEmptyList(filterByStock(produtos.filter(filter))));
	}
	
	public CollectionModel<ProdutoDto> searchByName(String name) {		
		return produtoAssembler.toCollectionModel(
				checkEmptyList(produtos.findByNameContainingOrderByNameAsc(name.toLowerCase())));
	}

	public CollectionModel<ProdutoDto> searchWithNameAsc(ProdutoFilter filter) {
		
		List<Produto> allProdutos = filterByStock(produtos.filter(filter));
		
		allProdutos.sort(Comparator.comparing(Produto::getName));	
		
		return produtoAssembler.toCollectionModel(checkEmptyList(allProdutos));
	}
	
	public CollectionModel<ProdutoDto> searchWithNameDesc(ProdutoFilter filter) {
		
		List<Produto> allProdutos = filterByStock(produtos.filter(filter));
		
		allProdutos.sort(Comparator.comparing(Produto::getName).reversed());	
		
		return produtoAssembler.toCollectionModel(checkEmptyList(allProdutos));
	}

	public ProdutoDto getOne(Long id) {
		return produtoAssembler.toModel(getById(id));
	}
	
	public ProdutoDto save(Produto produto, HttpServletResponse response) {
		
		checkProduto(produto);
			
		Produto produtoSaved = produtos.save(produto);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, produtoSaved.getId()));
		
		return produtoAssembler.toModel(produtoSaved);
	}

	public ProdutoDto update(Long id, Produto produto) {
		
		checkProduto(produto);
		
		Produto produtoSaved = getById(id);
		
		BeanUtils.copyProperties(produto, produtoSaved, "id");
		
		return produtoAssembler.toModel(produtos.save(produtoSaved));
	}

	public void delete(Long id) {
		produtos.deleteById(id);
	}
	
	public boolean produtoExists(Long id) {
		return produtos.existsById(id);	
	}
	
	private List<Produto> checkEmptyList(List<Produto> list) {

		if (list.isEmpty()) {
			throw new ProdutoNotFoundException();
		}
		
		return list;
	}
	
	private void checkProduto(Produto produto) {
		
		if (!checkUniqueName(produto)) {
			throw new ProdutoNameNotUniqueException();
		}	
	}
	
	private boolean checkUniqueName(Produto novoProduto) {
		List<Produto> allProdutos = produtos.findAll();
		
		for (Produto produto : allProdutos) {
			
			if (produto.getName().equals(novoProduto.getName())) {
				return false;
			}	
		}
		
		return true;	
	}

	private Produto getById(Long id) {
		Optional<Produto> produtoSaved = produtos.findById(id);
		
		if (produtoSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return produtoSaved.get();
	}
	
	private List<Produto> filterByStock(List<Produto> allProdutos) {
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (StringUtils.contains(user.getAuthorities().toString(), CLIENTE)) {
					
			allProdutos.removeIf(produto -> 
								produto.getEstoque().getQuantity() == 0 ||
								produto.getEstoque().getSellingPrice().doubleValue() == 0);
		}
		
		return allProdutos;
	}
	
}
