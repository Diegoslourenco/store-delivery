package com.gft.delivery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gft.delivery.model.Usuario;
import com.gft.delivery.repository.UsuarioRepository;
import com.gft.delivery.security.UsuarioSistema;

@Component
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarios.findByEmail(email);
		
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		
		Usuario usuarioSaved = usuario.get();
		
		return new UsuarioSistema(usuarioSaved.getEmail(), usuarioSaved.getPassword(), auth(usuarioSaved));
	}

	private Collection<? extends GrantedAuthority> auth(Usuario usuario) {
		
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		auths.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().getAuthority()));
		
		return auths;	
	}

}
