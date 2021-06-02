package com.gft.delivery.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.FornecedorAssembler;
import com.gft.delivery.dto.FornecedorDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.FornecedorCnpjNotUniqueException;
import com.gft.delivery.exceptionhandler.FornecedorEmailNotUniqueException;
import com.gft.delivery.exceptionhandler.FornecedorNameNotUniqueException;
import com.gft.delivery.exceptionhandler.FornecedorNotFoundException;
import com.gft.delivery.exceptionhandler.FornecedorPhoneNotUniqueException;
import com.gft.delivery.model.Fornecedor;
import com.gft.delivery.repository.FornecedorRepository;
import com.gft.delivery.repository.filter.FornecedorFilter;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorAssembler fornecedorAssembler;
	
	@Autowired
	private FornecedorRepository fornecedores;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public CollectionModel<FornecedorDto> search(FornecedorFilter filter) {
		return	fornecedorAssembler.toCollectionModel(checkEmptyList(fornecedores.filter(filter)));
	}
	
	public CollectionModel<FornecedorDto> searchWithNameAsc(FornecedorFilter filter) {
		
		List<Fornecedor> allFornecedores = fornecedores.filter(filter);
		
		allFornecedores.sort(Comparator.comparing(Fornecedor::getName));		
		
		return	fornecedorAssembler.toCollectionModel(checkEmptyList(allFornecedores));
	}
	
	public CollectionModel<FornecedorDto> searchWithNameDesc(FornecedorFilter filter) {
		
		List<Fornecedor> allFornecedores = fornecedores.filter(filter);
		
		allFornecedores.sort(Comparator.comparing(Fornecedor::getName).reversed());		
		
		return	fornecedorAssembler.toCollectionModel(checkEmptyList(allFornecedores));
	}

	public FornecedorDto getOne(Long id) {
		return fornecedorAssembler.toModel(getById(id));
	}
	
	public FornecedorDto save(Fornecedor fornecedor, HttpServletResponse response) {
		
		checkUniqueFornecedor(fornecedor);
			
		Fornecedor fornecedorSaved = fornecedores.save(fornecedor);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, fornecedorSaved.getId()));
		
		return fornecedorAssembler.toModel(fornecedorSaved);
	}

	public FornecedorDto update(Long id, Fornecedor fornecedor) {
		
		checkUniqueFornecedor(id, fornecedor);

		Fornecedor fornecedorSaved = getById(id);
		
		BeanUtils.copyProperties(fornecedor, fornecedorSaved, "id");
		
		return fornecedorAssembler.toModel(fornecedores.save(fornecedorSaved));
	}

	public void delete(Long id) {
		fornecedores.deleteById(id);
	}
	
	public boolean fornecedorExists(Long id) {
		return fornecedores.existsById(id);	
	}

	private Fornecedor getById(Long id) {
		
		Optional<Fornecedor> fornecedorSaved = fornecedores.findById(id);
		
		if (fornecedorSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return fornecedorSaved.get();
	}
	
	private void checkUniqueFornecedor(Fornecedor novoFornecedor) {
	
		List<Fornecedor> allFornecedors = fornecedores.findAll();
		
		for (Fornecedor fornecedor : allFornecedors) {
			checkFieldsFornecedor(fornecedor, novoFornecedor);
		}
	}
	
	private void checkUniqueFornecedor(Long id, Fornecedor novoFornecedor) {
		
		List<Fornecedor> allFornecedors = fornecedores.findAll();
		
		for (Fornecedor fornecedor : allFornecedors) {
			
			if ((!fornecedor.getId().equals(id))) {
				checkFieldsFornecedor(fornecedor, novoFornecedor);
			}	
		}
	}
	
	private void checkFieldsFornecedor(Fornecedor fornecedor, Fornecedor novoFornecedor) {
		
		if (fornecedor.getCnpj().equals(novoFornecedor.getCnpj())) {
			throw new FornecedorCnpjNotUniqueException();
		}	
		
		if (fornecedor.getName().equals(novoFornecedor.getName())) {
			throw new FornecedorNameNotUniqueException();
		}
		
		if (fornecedor.getPhone().equals(novoFornecedor.getPhone())) {
			throw new FornecedorPhoneNotUniqueException();
		}	
		
		if (fornecedor.getEmail().equals(novoFornecedor.getEmail())) {
			throw new FornecedorEmailNotUniqueException();
		}	
	}
	
	private List<Fornecedor> checkEmptyList(List<Fornecedor> list) {

		if (list.isEmpty()) {
			throw new FornecedorNotFoundException();
		}
		
		return list;
	}
	
}
