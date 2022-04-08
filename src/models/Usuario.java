package models;

public class Usuario {
	
	private String username;
	private String password;
	

	

	

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Usuario(String username, String password) {
		super();
		
		this.username = username;
		this.password = password;
	}

	

}
