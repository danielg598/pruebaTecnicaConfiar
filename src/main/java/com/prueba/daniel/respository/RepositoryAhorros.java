package com.prueba.daniel.respository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prueba.daniel.model.Ahorros;
//import com.prueba.daniel.model.Clientes;

@Repository
public class RepositoryAhorros {

	private JdbcTemplate jdbc;

	public RepositoryAhorros(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public Ahorros save(Ahorros cuenta) {
		String sql = "INSERT INTO ahorros (numero_cuenta, nit_cliente, saldo_inicial) VALUES(?, ?, ?)";
		jdbc.update(sql, cuenta.getId(),cuenta.getId_cliente(),cuenta.getSaldo());
		return cuenta;
	}
	
	public Boolean existCuenta(String id) {
		String sql = "SELECT COUNT(*) FROM ahorros WHERE nit_cliente=?";
		
		Integer count = jdbc.queryForObject(sql, Integer.class, id);
		
		return count != null && count > 0;
	}
	
	public Long getSaldoById(String idCuenta) {
	    String sql = "SELECT saldo_inicial FROM ahorros WHERE numero_cuenta = ?";
	    return jdbc.queryForObject(sql, Long.class, idCuenta);
	}
	
	public void updateSaldo(String idCuenta, Long nuevoSaldo) {
	    String sql = "UPDATE ahorros SET saldo_inicial = ? WHERE numero_cuenta = ?";
	    jdbc.update(sql, nuevoSaldo, idCuenta);
	}
	
	
	public Ahorros mapRowToAhorros(ResultSet rs) throws SQLException{
		Ahorros a = new Ahorros();
		a.setId(rs.getString("numero_cuenta"));
		a.setId_cliente(rs.getString("nit_cliente"));
		a.setSaldo(rs.getLong("saldo_inicial"));
		return a;
	}
	
	
}
