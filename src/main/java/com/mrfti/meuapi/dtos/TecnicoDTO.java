package com.mrfti.meuapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.mrfti.meuapi.domain.Tecnico;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Aqui na CLasse Tecnico DTO podemos remover um atributo abaixo para não ser
	// retornando em JSON na resposta http. Boas praticas de programação
	private Integer id;
	
	
	@NotEmpty(message = "O campo Nome é obrigatório!")
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CpF é obrigatório!")
	private String cpf;
	@NotEmpty(message = "O campo Telefone é obrigatório!")
	private String telefone;

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico obj) { // gerou construtores using fields e modificou com o obj
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	// metodos de getters e Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
