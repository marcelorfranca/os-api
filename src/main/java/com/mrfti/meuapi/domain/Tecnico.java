package com.mrfti.meuapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore // impede de ocorre loop de dados devido a serialização, ocorrendo erro no POSTMAN de expected;
	@OneToMany(mappedBy = "tecnico") // um tecnico para muitas OS e mapeado pelo tecnico la na class OS
	private List<OS> list = new ArrayList<>();
	
	public Tecnico() {
		super();

	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

	
	
}
