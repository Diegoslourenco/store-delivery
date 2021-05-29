package com.gft.delivery.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.delivery.dto.VendaDto;
import com.gft.delivery.dto.VendaRequestDto;
import com.gft.delivery.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<VendaDto>> search() {	
		return new ResponseEntity<CollectionModel<VendaDto>>(vendaService.search(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendaDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<VendaDto>(vendaService.getOne(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VendaDto> create(@Valid @RequestBody VendaRequestDto vendaRequest, HttpServletResponse response) {
		return new ResponseEntity<VendaDto>(vendaService.save(vendaRequest, response), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VendaDto> update(@PathVariable Long id) {
		return new ResponseEntity<VendaDto>(vendaService.update(id), HttpStatus.OK);
	}

}
