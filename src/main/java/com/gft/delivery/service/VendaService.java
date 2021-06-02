package com.gft.delivery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gft.delivery.assembler.VendaAssembler;
import com.gft.delivery.dto.VendaDto;
import com.gft.delivery.dto.VendaRequestDto;
import com.gft.delivery.event.ResourceCreatedEvent;
import com.gft.delivery.exceptionhandler.ClienteNotSameException;
import com.gft.delivery.exceptionhandler.EstoqueNotEnoughException;
import com.gft.delivery.exceptionhandler.EstoqueNotFoundException;
import com.gft.delivery.exceptionhandler.ProdutoNotFoundException;
import com.gft.delivery.exceptionhandler.VendaAlreadyReceivedException;
import com.gft.delivery.exceptionhandler.VendaNotFoundException;
import com.gft.delivery.model.Cliente;
import com.gft.delivery.model.ItemVenda;
import com.gft.delivery.model.Usuario;
import com.gft.delivery.model.Venda;
import com.gft.delivery.model.VendaStatus;
import com.gft.delivery.repository.ClienteRepository;
import com.gft.delivery.repository.UsuarioRepository;
import com.gft.delivery.repository.VendaRepository;
import com.gft.delivery.repository.filter.ClienteFilter;

@Service
public class VendaService {
	
	@Autowired
	private VendaAssembler vendaAssembler;
	
	@Autowired
	private VendaRepository vendas;
	
	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public CollectionModel<VendaDto> search(ClienteFilter filter) {
		return vendaAssembler.toCollectionModel(checkEmptyList(filterByCliente(filter)));
	}
	
	public CollectionModel<VendaDto> searchFinished(ClienteFilter filter) {
		
		List<Venda> allVendas = checkEmptyList(filterByCliente(filter));
		
		return vendaAssembler.toCollectionModel(removeByStatus(allVendas, VendaStatus.PENDENTE.ordinal()));
	}
	
	public CollectionModel<VendaDto> searchPending(ClienteFilter filter) {
		
		List<Venda> allVendas = checkEmptyList(filterByCliente(filter));
		
		return vendaAssembler.toCollectionModel(removeByStatus(allVendas, VendaStatus.CONCLUIDO.ordinal()));
	}

	private List<Venda> removeByStatus(List<Venda> allVendas, int status) {
		
		allVendas.removeIf(venda ->
							venda.getStatus().ordinal() == status);
				
		return allVendas;
	}

	public VendaDto getOne(Long id) {
		return vendaAssembler.toModel(getById(id));
	}
	
	public VendaDto save(VendaRequestDto vendaRequest, HttpServletResponse response) {
		
		checkItens(vendaRequest.getItens());
			
		Venda venda = new Venda(vendaRequest.getCliente(), VendaStatus.PENDENTE);
		Venda vendaSaved = vendas.save(venda);
				
		// Updating quantity and saving ItemCompra list
		List<ItemVenda> itens = itemService.saveItemVendaList(vendaRequest.getItens(), vendaSaved);
		
		// Get user details and send receipt by email
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		sendEmail(user.getUsername(), itens);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, vendaSaved.getId()));
		
		return vendaAssembler.toModel(vendaSaved);
	}

	public VendaDto update(Long id) {
				
		Venda vendaSaved = getById(id);
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userEmail = user.getUsername();
		
		if (!vendaSaved.getCliente().getEmail().equals(userEmail)) {
			throw new ClienteNotSameException();
		}
		
		if (vendaSaved.getStatus().ordinal() == 1) {
			throw new VendaAlreadyReceivedException();
		}
		
		vendaSaved.setStatus(VendaStatus.CONCLUIDO);
		
		return vendaAssembler.toModel(vendas.save(vendaSaved));
	}
	
	private Venda getById(Long id) {
		Optional<Venda> vendaSaved = vendas.findById(id);
		
		if (vendaSaved.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return vendaSaved.get();
	}
	
	private void checkItens(List<ItemVenda> itens) {
		for (ItemVenda itemVenda : itens) {
			
			Long itemId = itemVenda.getProduto().getId();
			
			if (!produtoService.produtoExists(itemId)) {
				throw new ProdutoNotFoundException();			
			}
			
			if (!estoqueService.estoqueExists(itemId)) {
				throw new EstoqueNotFoundException();				
			}
			
			if ((estoqueService.getById(itemId).getQuantity() - itemVenda.getQuantity()) < 0) {
				throw new EstoqueNotEnoughException();
			}
		}
		
	}
	
	private List<Venda> filterByCliente(ClienteFilter filter) {

		List<Cliente> allClientes = clientes.filter(filter);

		List<Venda> allVendas = new ArrayList<>();
		
		for (Cliente cliente : allClientes) {
			allVendas.addAll(vendas.findByClienteId(cliente.getId()));
		}
		
		allVendas.sort(Comparator.comparing(Venda::getId));
		
		return allVendas;
	}
	
	private List<Venda> checkEmptyList(List<Venda> list) {

		if (list.isEmpty()) {
			throw new VendaNotFoundException();
		}
		
		return list;
	}
	
	private void sendEmail(String email, List<ItemVenda> itens) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		Usuario usuarioSaved = usuarios.findByEmail(email).get();
		
		// Writing email message
		String emailText = "";
		BigDecimal total = BigDecimal.ZERO;
		emailText = emailText.concat("Olá, " + usuarioSaved.getCliente().getName() + "!\n" +
						"\nSegue recibo dos itens comprados em nossa loja para sua comodidade!");
		
		for (ItemVenda item : itens) { 		
			emailText = emailText.concat("\n\nProduto: " + produtoService.getOne(item.getProduto().getId()).getName()  +
										 "\nQuantidade: " + item.getQuantity() +
										 "\nValor:  R$ " + item.getPrice());
			total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		
		emailText = emailText.concat("\n\nTotal: R$ " + total);
		emailText = emailText.concat("\n\nVolte sempre!");		
		
		// Setting email
		simpleMailMessage.setFrom("imobgft@gmail.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Recibo da compra realizada por " + usuarioSaved.getCliente().getName() +  "!");
		simpleMailMessage.setText(emailText);
		
		// Sending email
		mailSender.send(simpleMailMessage);	
	}

}
