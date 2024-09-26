package com.prueba.daniel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.daniel.model.Ahorros;
import com.prueba.daniel.model.Clientes;
import com.prueba.daniel.service.Services;

@RestController
@RequestMapping("/api")
public class GlobalController {
	
	private Services service;

	public GlobalController(Services service) {
		this.service = service;
	}
	
	@PostMapping("/postCliente")
	public ResponseEntity<String> addCity(@RequestBody Clientes cliente) {
	    try {
	        Clientes add = service.saveCliente(cliente);
	        return ResponseEntity.status(HttpStatus.CREATED)
	                             .body("Cliente agregado exitosamente: " + add.toString());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("Error al agregar el cliente: " + e.getMessage());
	    }
	}
	
	@PostMapping("/postCuenta")
	public ResponseEntity<String> addCuenta(@RequestBody Ahorros cuenta) {
	    return service.saveCuenta(cuenta);
	}
	
	@PutMapping("/updateSaldo/{idCuenta}")
	public ResponseEntity<String> updateSaldo(
	        @PathVariable String idCuenta,
	        @RequestParam(required = false) Long cr,
	        @RequestParam(required = false) Long db) {
	    return service.updateSaldo(idCuenta, cr, db);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<List<Clientes>> getClienteById(@PathVariable Long id) {
	    List<Clientes> clientes = service.getClienteById(id);
	    return ResponseEntity.ok(clientes);
	}
	
	
	

}
