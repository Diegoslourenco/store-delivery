package com.gft.delivery.repository.query;

import java.util.List;

import com.gft.delivery.model.Cliente;
import com.gft.delivery.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {
	
	public List<Cliente> filter(ClienteFilter filter);

}
