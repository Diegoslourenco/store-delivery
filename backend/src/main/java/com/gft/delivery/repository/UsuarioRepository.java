package com.gft.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByClienteId(Long id);
}
