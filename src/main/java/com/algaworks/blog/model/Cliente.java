package com.algaworks.blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cliente {

	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

	@NotNull
	@Size(min = 3, max = 40)
	private String sobrenome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}