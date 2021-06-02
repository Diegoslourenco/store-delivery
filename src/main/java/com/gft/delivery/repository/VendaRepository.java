package com.gft.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	public List<Venda> findByClienteId(Long id);

}
