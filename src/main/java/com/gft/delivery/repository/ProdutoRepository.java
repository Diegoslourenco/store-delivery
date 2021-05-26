package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
