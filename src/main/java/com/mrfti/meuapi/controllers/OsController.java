package com.mrfti.meuapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mrfti.meuapi.dtos.OSDTO;
import com.mrfti.meuapi.services.OsService;

@CrossOrigin("*") // permita nossa API ser consumida por diversas fontes
@RestController
@RequestMapping(value = "/os" )
public class OsController {

	@Autowired
	private OsService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
		OSDTO obj = new OSDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll() {
		List<OSDTO> lista = service.findAll().stream()
				.map(obj -> new OSDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO obj ) {
		obj = new OSDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping // este nao precisa usar o {/id} pois esta usando o fromDTo no service 
	public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO obj) {
		obj = new OSDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
	
	// o delete não foi implementado na aula para os , mas caso haja necessidade podemos implementar.
	
	
}
