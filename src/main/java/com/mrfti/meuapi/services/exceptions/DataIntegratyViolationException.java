package com.mrfti.meuapi.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Apos estender a classe Runtime add Serial

	public DataIntegratyViolationException(String message, Throwable cause) { // using e fields somente para estes dois construtores
		super(message, cause);
	
	}

	public DataIntegratyViolationException(String message) {
		super(message);
	
	}

}
