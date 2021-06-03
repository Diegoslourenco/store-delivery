package com.gft.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.delivery.dto.PasswordDto;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.model.Usuario;
import com.gft.delivery.repository.PerfilRepository;
import com.gft.delivery.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final String CLIENTE = "CLIENTE";
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private PerfilRepository perfis;

	public void save(Cliente cliente, String password) {
		
		Usuario novoUsuario = new Usuario(
			cliente.getEmail(), 
			new BCryptPasswordEncoder().encode(password),
			cliente,
			perfis.findByName(CLIENTE)
		);
		
		usuarios.save(novoUsuario);	
	}
	
	public void update(Cliente cliente) {
		
		Usuario usuarioSaved = usuarios.findByClienteId(cliente.getId()).get();
		
		usuarioSaved.setEmail(cliente.getEmail());
		
		usuarios.save(usuarioSaved);	
	}
	
	public void updatePassword(PasswordDto passwordDto, String userEmail) {
		
		Usuario usuarioSaved = usuarios.findByEmail(userEmail).get();
		
		usuarioSaved.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getPassword()));
		
		usuarios.save(usuarioSaved);	
	}

}
