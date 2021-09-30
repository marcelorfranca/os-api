package com.mrfti.meuapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mrfti.meuapi.services.DBService;

@Configuration // Classe será identificada pelo Application.Properties
@Profile("dev") // Sempre que estiver ativo ele vai chamar o metodo abaixo
public class DevConfig {
	
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}") // pega o valor desta chave la do arquivo de properties
	private String ddl;
	
	@Bean // ira chamar a classe DBService com a instancia abaixo.
	public boolean instanciaDB() {
		
		if(ddl.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
		
		
	}
	
}
