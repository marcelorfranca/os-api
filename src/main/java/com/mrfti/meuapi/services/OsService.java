package com.mrfti.meuapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrfti.meuapi.domain.Cliente;
import com.mrfti.meuapi.domain.OS;
import com.mrfti.meuapi.domain.Tecnico;
import com.mrfti.meuapi.domain.enuns.Prioridade;
import com.mrfti.meuapi.domain.enuns.Status;
import com.mrfti.meuapi.dtos.OSDTO;
import com.mrfti.meuapi.repositories.OSRepository;
import com.mrfti.meuapi.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private TecnicoService tecnicoService; // usado no fromDTO neste mesmo service

	@Autowired
	private ClienteService clienteService; // usado no fromDTO neste mesmo service

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("OS não encontrada! Id " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}

	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);

	}

	
	
	private OS fromDTO(OSDTO obj) { // funciona para salvar e atualizar caso ele ja encontre o id informado em update
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod())); // add getCod para front end
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod())); // add getCod para front end

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) { // Verifica se status for finalizado(2) ele lança dt de fechamento
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		

		return repository.save(newObj);

	}


}
