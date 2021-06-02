package com.gft.delivery.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.gft.delivery.model.Produto;
import com.gft.delivery.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Produto> filter(ProdutoFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		
		Root<Produto> root = criteria.from(Produto.class);
		
		Predicate[] predicates = createRestriction(filter, builder, root);;
		criteria.where(predicates);
		
		TypedQuery<Produto> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestriction(ProdutoFilter filter, CriteriaBuilder builder, Root<Produto> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!StringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getUnit())) {
			predicates.add(builder.like(
					builder.lower(root.get("unit")), "%" + filter.getUnit().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
