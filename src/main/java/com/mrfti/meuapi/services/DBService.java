package com.mrfti.meuapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrfti.meuapi.domain.Cliente;
import com.mrfti.meuapi.domain.OS;
import com.mrfti.meuapi.domain.Tecnico;
import com.mrfti.meuapi.domain.enuns.Prioridade;
import com.mrfti.meuapi.domain.enuns.Status;
import com.mrfti.meuapi.repositories.ClienteRepository;
import com.mrfti.meuapi.repositories.OSRepository;
import com.mrfti.meuapi.repositories.TecnicoRepository;

@Service // poderá ser chamado para executar testes sendo injetado pelo spring por onde for chamado
public class DBService {

	@Autowired // injetor
	private TecnicoRepository tecnicoRepository; // vem das Interfaces criadas camada repositorio
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	
	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Jujubela da Silva", "609.866.580-06", "(77)97777-7777");
		Tecnico t2 = new Tecnico(null, "Petanha Gomes", "641.760.040-88", "(81)77577-7345");
		Cliente c1 = new Cliente(null, "Betinha da Silva", "602.898.590-26", "(77)97767-7977");
		OS os1 = new OS(null, Prioridade.ALTA, "Ordem de Serviço", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1); // o t1 precisa receber a lista das OSs
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
