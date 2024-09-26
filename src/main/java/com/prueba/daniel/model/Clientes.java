package com.prueba.daniel.model;

import java.sql.Timestamp;

public class Clientes {
	
	private String id;
	private String nombre;
	private Timestamp fecha;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	

}
