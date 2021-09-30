package com.mrfti.meuapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mrfti.meuapi.services.DBService;

@Configuration // Classe será identificada pelo Application.Properties
@Profile("test") // Sempre que estiver ativo ele vai chamar o metodo abaixo
public class TesteConfig {
	
	
	@Autowired
	private DBService dbService;
	
	@Bean // ira chamar a classe DBService com a instancia abaixo.
	public void instanciaDB() {
		this.dbService.instanciaDB();
		
	}
	
}
