package com.gft.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.query.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {
	
	public List<Produto> findByNameContainingOrderByNameAsc(String name);
}
