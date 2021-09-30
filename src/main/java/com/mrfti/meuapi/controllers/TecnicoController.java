package com.mrfti.meuapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mrfti.meuapi.domain.Tecnico;
import com.mrfti.meuapi.dtos.TecnicoDTO;
import com.mrfti.meuapi.services.TecnicoService;

@CrossOrigin("*") // permita nossa API ser consumida por diversas fontes
@RestController // Recebe requisições HTTP
@RequestMapping(value = "/tecnicos") // endpoint inicial para tecnico
public class TecnicoController {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}") // {id}vai receber um path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) { // Seria falha de segurança usar a classe
																			// Tecnico, com o
		// DTO podemos omitir certos atributos que poderiam colocar em risto ja que em
		// Tecnico o acesso é total. Ver Classe TecnicoDTO
		TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);

	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<TecnicoDTO> listaDTO = service.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);

	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri(); // Uri pega o id do registro criado
		return ResponseEntity.created(uri).build(); 
	}
	
	
	//Atualiza
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj  = new TecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	//Deleta tecnico
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
