package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.query.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {

}
