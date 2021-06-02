package com.gft.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
	public Optional<Estoque> findByProdutoId(Long produtoId);
}
