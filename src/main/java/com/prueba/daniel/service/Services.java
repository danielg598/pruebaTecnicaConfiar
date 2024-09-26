package com.prueba.daniel.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.daniel.model.Ahorros;
import com.prueba.daniel.model.Clientes;
import com.prueba.daniel.respository.RepositoryAhorros;
import com.prueba.daniel.respository.RepositoryClientes;

@Service
public class Services {
	
	private RepositoryClientes rep1;
	private RepositoryAhorros rep2;

	public Services(RepositoryClientes rep1, RepositoryAhorros rep2) {
		this.rep1 = rep1;
		this.rep2 = rep2;
	}
	
	
	public Clientes saveCliente(Clientes cliente) {
		return rep1.save(cliente);
	}
	
	public ResponseEntity<String> saveCuenta(Ahorros cuenta) {
	    
	    if (!rep1.existClient(cuenta.getId_cliente())) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con NIT " + cuenta.getId_cliente() + " no existe.");
	    }

	    
	    if (rep2.existCuenta(cuenta.getId_cliente())) {
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body("La cuenta de ahorros ya existe para el cliente con NIT " + cuenta.getId_cliente());
	    }

	    
	    Ahorros savedCuenta = rep2.save(cuenta);
	    return ResponseEntity.ok("Cuenta de ahorros guardada con éxito: " + savedCuenta);
	}
	
	public ResponseEntity<String> updateSaldo(String idCuenta, Long cr, Long db) {
	    
	    if (!rep2.existCuenta(idCuenta)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta con ID " + idCuenta + " no existe.");
	    }

	    
	    Long saldoActual = rep2.getSaldoById(idCuenta);
	    Long nuevoSaldo = saldoActual;

	    
	    if (cr != null) {
	        nuevoSaldo += cr;  
	    }
	    
	    if (db != null) {
	        nuevoSaldo -= db;  
	        if (nuevoSaldo < 0) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede restar, el saldo no puede ser negativo.");
	        }
	    }

	    
	    rep2.updateSaldo(idCuenta, nuevoSaldo);

	    return ResponseEntity.ok("Saldo actualizado a: " + nuevoSaldo);
	}
	
	public List<Clientes> getClienteById(Long id) {
	    return rep1.clientById(id);
	}
	

}
