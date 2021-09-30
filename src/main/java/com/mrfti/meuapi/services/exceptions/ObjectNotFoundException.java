package com.mrfti.meuapi.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Apos estender a classe Runtime add Serial

	public ObjectNotFoundException(String message, Throwable cause) { // using e fields somente para estes dois construtores
		super(message, cause);
	
	}

	public ObjectNotFoundException(String message) {
		super(message);
	
	}
	
	

}
