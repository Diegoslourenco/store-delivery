package com.gft.delivery.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DeliveryResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String description;
		
		if (ex.getCause() == null) {
			description = ex.toString();
		}
		else {
			description = ex.getCause().toString();
		}
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorsList(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
		
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String message = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		String message = messageSource.getMessage("resource.not-permitted-operation", null, LocaleContextHolder.getLocale());
		String description = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ ProdutoNameNotUniqueException.class })
	public ResponseEntity<Object> handleProdutoNameNotUniqueException(ProdutoNameNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("produto.name-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ FornecedorCnpjNotUniqueException.class })
	public ResponseEntity<Object> handleFornecedorCnpjNotUniqueException(FornecedorCnpjNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("fornecedor.cnpj-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ FornecedorNameNotUniqueException.class })
	public ResponseEntity<Object> handleFornecedorNameNotUniqueException(FornecedorNameNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("fornecedor.name-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ FornecedorPhoneNotUniqueException.class })
	public ResponseEntity<Object> handleFornecedorPhoneNotUniqueException(FornecedorPhoneNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("fornecedor.phone-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ ClienteEmailNotUniqueException.class })
	public ResponseEntity<Object> handleClienteEmailNotUniqueException(ClienteEmailNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("cliente.email-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ ClienteCpfNotUniqueException.class })
	public ResponseEntity<Object> handleClienteCpfNotUniqueException(ClienteCpfNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("cliente.cpf-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ FornecedorEmailNotUniqueException.class })
	public ResponseEntity<Object> handleFornecedorEmailNotUniqueException(FornecedorEmailNotUniqueException ex, WebRequest request) {
		
		String message = messageSource.getMessage("fornecedor.email-not-unique", null, LocaleContextHolder.getLocale());
		String description = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(message, description));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	

	
	private List<Error> createErrorsList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String description = fieldError.toString();
			
			errors.add(new Error(message, description));
		}
	
		return errors;
	}

}
