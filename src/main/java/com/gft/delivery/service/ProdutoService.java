package com.gft.delivery.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.ProdutoAssembler;
import com.gft.delivery.dto.ProdutoDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ProdutoNameNotUniqueException;
import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoAssembler produtoAssembler;
	
	@Autowired
	private ProdutoRepository produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<ProdutoDto> search() {
		return produtoAssembler.toCollectionModel(produtos.findAll());
	}

	public ProdutoDto getOne(Long id) {
		return produtoAssembler.toModel(getById(id));
	}
	
	public ProdutoDto save(Produto produto, HttpServletResponse response) {
		
		if (!checkUniqueName(produto)) {
			throw new ProdutoNameNotUniqueException();
		}
			
		Produto produtoSaved = produtos.save(produto);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, produtoSaved.getId()));
		
		return produtoAssembler.toModel(produtoSaved);
	}
	
	public ProdutoDto update(Long id, Produto produto) {
		
		if (!checkUniqueName(produto)) {
			throw new ProdutoNameNotUniqueException();
		}
		
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

	private Produto getById(Long id) {
		Optional<Produto> produtoSaved = produtos.findById(id);
		
		if (produtoSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return produtoSaved.get();
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

}
