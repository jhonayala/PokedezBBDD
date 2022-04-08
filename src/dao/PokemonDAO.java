package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import enums.Categoria;
import enums.Tipo;
import models.Pokemon;

public class PokemonDAO {
	final String DB_URL = "jdbc:mysql://localhost/pokedezbbdd";
	final String USER = "pokedezbbdd";
	final String PASS = "1234";
	private Tipo tipo;
	private Categoria categoria;

//	private int numero;
//	private String nombre ;
//	private Tipo tipo;
//	private double altura;
//	private double peso;
//	private Categoria categoria;
//	private String habilidad;

	public Pokemon first() {
		final String QUERY = "SELECT numero, nombre, tipo, altura, peso, categoria, habilidad" + "FROM pokemon limit 1";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = (stmt).executeQuery(QUERY);
			while (rs.next()) {
				int numero = rs.getInt("numero");
				String nombre = rs.getString("nombre");
				tipo = Tipo.valueOf(rs.getString("tipo"));
				double peso = rs.getDouble("peso");
				double altura = rs.getDouble("altura");
				categoria = Categoria.valueOf(rs.getString("categoria"));
				String habilidad = rs.getString("habilidad");

				Pokemon a = new Pokemon(numero, nombre, tipo, altura, peso, categoria, habilidad);
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Pokemon> getAll() {
		final String QUERY = "SELECT numero, nombre, tipo, altura, peso, categoria, habilidad"
				+ " FROM pokemon order by numero ;";
		var pokemons = new ArrayList<Pokemon>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				int numero = rs.getInt("numero");
				String nombre = rs.getString("nombre");
				tipo = Tipo.valueOf(rs.getString("tipo"));
				double peso = rs.getDouble("peso");
				double altura = rs.getDouble("altura");
				categoria = Categoria.valueOf(rs.getString("categoria"));
				String habilidad = rs.getString("habilidad");
				Pokemon a = new Pokemon(numero, nombre, tipo, altura, peso, categoria, habilidad);
				pokemons.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pokemons;
	}

	public void insert(Pokemon a) {
		final String INSERT = "INSERT INTO Pokemon " + "VALUES ('" + a.getNumero() + "','" + a.getNombre() + "','"
				+ String.valueOf(a.getTipo()) + "','" + a.getAltura() + "','" + a.getPeso() + "','"
				+ String.valueOf(a.getCategoria()) + "','" + a.getHabilidad() +

				"');";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Pokemon a) {
		final String DELETE = "delete from Pokemon where numero = " + a.getNumero();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Pokemon a) {
		final String UPDATE = "UPDATE pokemon\r\n" + "SET\r\n"  + "tipo = '" + String.valueOf(a.getTipo()) + "',\r\n"
				+ "altura = '" + a.getAltura() + "',\r\n" + "peso =" + a.getPeso()
				+ ",\r\n" + "categoria = '" + String.valueOf(a.getCategoria()) + "',\r\n" + "habilidad = '"
				+ a.getHabilidad() + "' where numero = "+a.getNumero()+" ;";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean compNum(int a) {
		boolean exist = false;
		final String QUERY = "SELECT numero" + " FROM pokemon order by numero";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				int numero = rs.getInt("numero");

				if (numero == a) {
					exist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public boolean compNom(String a) {
		boolean exist = false;
		final String QUERY = "SELECT  nombre" + " FROM pokemon order by numero";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				String nombre = rs.getString("nombre");

				if (a.equals(nombre)) {
					exist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
}
