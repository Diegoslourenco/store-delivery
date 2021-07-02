package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	public Perfil findByName(String name);

}
