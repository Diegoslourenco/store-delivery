package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
