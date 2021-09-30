package com.mrfti.meuapi.domain.enuns;

public enum Status {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), FINALIZADO(2, "FINALIZADO");

	private Integer cod;
	private String descricao;

	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// Método verifica se Status retornada está dentro do enum acima
	public static Status toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Status x : Status.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido! " + cod);
	}

}
