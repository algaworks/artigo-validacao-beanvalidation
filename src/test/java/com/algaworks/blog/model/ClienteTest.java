package com.algaworks.blog.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Validator validator;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
	
	@Test
	public void naoDeveAceitarSobrenomeCurto() {
		Cliente cliente = new Cliente();
		cliente.setNome("Ana");
		cliente.setSobrenome("S.");

		Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
		
		assertThat(restricoes, hasSize(1));
		assertThat(getNomePrimeiraPropriedade(restricoes), is("sobrenome"));
	}

	private String getNomePrimeiraPropriedade(
			Set<ConstraintViolation<Cliente>> restricoes) {
		return restricoes.iterator().next().getPropertyPath().iterator().next().getName();
	}
	
	@Test
	public void naoDeveAceitarClienteSemNomeESobrenome() {
		Cliente cliente = new Cliente();
		
		Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
		
		assertThat(restricoes, Matchers.hasSize(2));
	}
	
	@Test
	public void devePassarNaValidacaoComNomeESobrenomeInformados() {
		Cliente cliente = new Cliente();
		cliente.setNome("Ana");
		cliente.setSobrenome("Silva");

		Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
		
		assertThat(restricoes, empty());
	}
	
}