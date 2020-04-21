package com.example.EjemploRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.EjemploRest.entities.Oferta;
import com.example.EjemploRest.bs.OfertasBs;

@CrossOrigin(
	origins="*",
	allowedHeaders="*",
	methods= {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.PUT,
		RequestMethod.DELETE
	}
)
@RestController
public class OfertasController {

	@Autowired
	private OfertasBs ofertasBusinessLogic;

	@GetMapping(path="ofertas", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Oferta>> getOfertas() {
		List<Oferta> ofertas = ofertasBusinessLogic.getOfertas();
		return new ResponseEntity<List<Oferta>>(ofertas, HttpStatus.OK);
	}

	@GetMapping(path="ofertas/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Oferta> getOferta(@PathVariable("id") int id) {
		Oferta oferta = ofertasBusinessLogic.getOferta(id);
		return new ResponseEntity<Oferta>(oferta, HttpStatus.OK);
	}

	@PostMapping(path="ofertas", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Oferta> createOferta(@RequestBody Oferta ofertaNueva) {
		Oferta oferta = ofertasBusinessLogic.insertOferta(ofertaNueva);
		return new ResponseEntity<Oferta>(oferta, HttpStatus.CREATED);
	}

	@PutMapping(path="ofertas/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Oferta> createOferta(@RequestBody Oferta ofertaActualizada, @PathVariable("id") int id) {
		ofertaActualizada.setId(id);
		Oferta oferta = ofertasBusinessLogic.updateOferta(ofertaActualizada);
		return new ResponseEntity<Oferta>(oferta, HttpStatus.OK);
	}

	@DeleteMapping(path="ofertas/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deleteOferta(@PathVariable("id") int id) {
		ofertasBusinessLogic.deleteOferta(id);
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}
}