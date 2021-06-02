package com.gft.delivery.repository.query;

import java.util.List;

import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	
	public List<Produto> filter(ProdutoFilter filter);
}
