package com.mrfti.meuapi.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrfti.meuapi.domain.Pessoa;
import com.mrfti.meuapi.domain.Tecnico;
import com.mrfti.meuapi.dtos.TecnicoDTO;
import com.mrfti.meuapi.repositories.PessoaRepository;
import com.mrfti.meuapi.repositories.TecnicoRepository;
import com.mrfti.meuapi.services.exceptions.DataIntegratyViolationException;
import com.mrfti.meuapi.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired // o spring que gerencia. e destroy a variavel
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // porque optional? Pode ou nao encontrar o id na base de dados
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Tecnico.class.getName())); // se for nulo ele lança
																								// uma exceção

	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		// Validar CPF, Verifica se CPF inserido já existe na base de dados 
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());
		
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj =  findById(id);
		if(obj.getList().size() > 0) { // verifica se ha tecnico em OS, senao gera erro de integridade referencial no BD
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	//Busca pessoa pelo CPF. Método usado na criacao e na atualização
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

	

}
