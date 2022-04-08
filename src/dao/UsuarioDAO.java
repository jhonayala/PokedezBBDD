package dao;

//CTRL + SHIFT + O
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Usuario;

public class UsuarioDAO {
	final String DB_URL = "jdbc:mysql://localhost/pokedezbbdd";
	final String USER = "pokedezbbdd";
	final String PASS = "1234";

	public void consulta() {
		final String QUERY = "SELECT Username, Password FROM usuarios";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.print("Username: " + rs.getString("username"));
				System.out.println(", Password: " + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(Usuario usuario) {
		final String QUERY = "SELECT Username, Password FROM usuarios " + "where username = '" + usuario.getUsername()
				+ "' and " + "password = '" + usuario.getPassword() + "'";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void register(Usuario usuario) {
		final String INSERT = "INSERT INTO usuarios " + " VALUES ('" + usuario.getUsername() + "','"
				+ usuario.getPassword() + "');";
		try {

			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean compUsername(String u) {
		boolean exist = false;
		final String QUERY = "SELECT Username, Password FROM usuarios";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				String nombre = rs.getString("Username");
				if (u.equals(nombre)) {
					exist = true;

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return exist;
	}
}
