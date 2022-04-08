package models;


import enums.Categoria;
import enums.Tipo;

public class Pokemon {
	
	
//	 número, nombre, tipo, altura, peso, categoría y habilidad
	private int numero;
	private String nombre ;
	private Tipo tipo;
	private double altura;
	private double peso;
	private Categoria categoria;
	private String habilidad;
	public int getNumero() {
		return numero;
	}
	public String getNombre() {
		return nombre;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public double getAltura() {
		return altura;
	}
	public double getPeso() {
		return peso;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public String getHabilidad() {
		return habilidad;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
	public Pokemon(int numero, String nombre, Tipo tipo, double altura, double peso, Categoria categoria,
			String habilidad) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.tipo = tipo;
		this.altura = altura;
		this.peso = peso;
		this.categoria = categoria;
		this.habilidad = habilidad;
	}
	
	
	
	
	

}
