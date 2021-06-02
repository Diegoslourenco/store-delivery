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

import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.repository.filter.FornecedorFilter;

public class FornecedorRepositoryQueryImpl implements FornecedorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Fornecedor> filter(FornecedorFilter filter) {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> criteria = builder.createQuery(Fornecedor.class);
		
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		
		Predicate[] predicates = createRestriction(filter, builder, root);;
		criteria.where(predicates);
		
		TypedQuery<Fornecedor> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestriction(FornecedorFilter filter, CriteriaBuilder builder, Root<Fornecedor> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!StringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getDistrict())) {
			predicates.add(builder.like(
					builder.lower(root.get("address").get("district")), "%" + filter.getDistrict().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getCep())) {
			predicates.add(builder.like(
					builder.lower(root.get("address").get("cep")), "%" + filter.getCep().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getCity())) {
			predicates.add(builder.like(
					builder.lower(root.get("address").get("city")), "%" + filter.getCity().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getState())) {
			predicates.add(builder.like(
					builder.lower(root.get("address").get("state")), "%" + filter.getState().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
