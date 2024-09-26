package com.prueba.daniel.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.prueba.daniel.controller.City;
//import com.prueba.daniel.controller.PathVariable;
import com.prueba.daniel.model.Clientes;

@Repository
public class RepositoryClientes {
	
	private JdbcTemplate jdbc;

	public RepositoryClientes(@Qualifier("jdbcConfig") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public Clientes save(Clientes cliente) {
		String sql = "INSERT INTO clientes (nit, nombre, fecha_ingreso) VALUES(?, ?, ?)";
		jdbc.update(sql, cliente.getId(),cliente.getNombre(),cliente.getFecha());
		return cliente;
	}
	
	public List<Clientes> clientById(Long id) {
	    String sql = "SELECT * FROM clientes WHERE nit = CAST(? AS VARCHAR)";
	    return jdbc.query(sql, ps -> ps.setLong(1, id), (rs, rowNum) -> mapRowToCliente(rs));
	}
	
	public Boolean existClient(String id) {
		String sql = "SELECT COUNT(*) FROM clientes WHERE nit=?";
		
		Integer count = jdbc.queryForObject(sql, Integer.class, id);
		
		return count != null && count > 0;
	}
	
	public Clientes mapRowToCliente(ResultSet rs) throws SQLException{
		Clientes a = new Clientes();
		a.setId(rs.getString("nit"));
		a.setNombre(rs.getString("nombre"));
		a.setFecha(rs.getTimestamp("fecha_ingreso"));
		return a;
	}

}
