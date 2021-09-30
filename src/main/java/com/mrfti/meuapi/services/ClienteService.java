package com.mrfti.meuapi.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrfti.meuapi.domain.Cliente;
import com.mrfti.meuapi.domain.Pessoa;
import com.mrfti.meuapi.dtos.ClienteDTO;
import com.mrfti.meuapi.repositories.ClienteRepository;
import com.mrfti.meuapi.repositories.PessoaRepository;
import com.mrfti.meuapi.services.exceptions.DataIntegratyViolationException;
import com.mrfti.meuapi.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrato ! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findALL() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));

	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) { // verifica se ha cliente em OS, senao gera erro de integridade referencial no
										// BD
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);

	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		// Validar CPF, Verifica se CPF inserido já existe na base de dados
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setCpf(objDTO.getCpf());

		return repository.save(oldObj);

	}

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
