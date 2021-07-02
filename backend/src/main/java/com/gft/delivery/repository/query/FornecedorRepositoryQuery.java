package com.gft.delivery.repository.query;

import java.util.List;

import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.repository.filter.FornecedorFilter;

public interface FornecedorRepositoryQuery {
	
	public List<Fornecedor> filter(FornecedorFilter filter);

}
