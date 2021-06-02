package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.repository.query.FornecedorRepositoryQuery;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryQuery {

}
