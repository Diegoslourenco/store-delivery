package com.gft.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	public List<Compra> findByFornecedorId(Long id);

}
