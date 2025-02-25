package com.auth;

public class Usuario {
	private static final int NUMERO_MAXIMO_INTENTOS = 2;
	private String nombre;
	private String apellidos;
	private String email;
	protected int intentos;
	protected Credencial credencial;

	public Usuario(String nombre, String apellidos, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.credencial = new Credencial(nombre, apellidos, password);
		this.intentos = 0;
	}

	public Usuario(String nombre, String apellidos, String email, String password) {
		this(nombre, apellidos, password);
		this.email = email;
	}

	public boolean esCuentaBloqueada() {
		return intentos >= NUMERO_MAXIMO_INTENTOS;
	}

	public void setCredencial(String nombre, String apellidos, String password) {
		this.credencial = new Credencial(nombre,apellidos,password);
	}

	public boolean modificarPassword(String oldPass, String newPass, String newPassVerif) {
		boolean modificada = false;
		if (this.credencial.comprobarPassword(oldPass) && !newPass.equals(oldPass) && newPass.equals(newPassVerif)) {
			this.credencial.setPassword(newPass);
			modificada = true;
		}
		return modificada;
	}

	public boolean esPasswordSegura() {
		return this.credencial.esPasswordSegura();
	}

	public boolean hacerLogin(String username, String password) {
		boolean loginRealizado = false;
		if (credencial.getUsername().equals(username) && credencial.comprobarPassword(password)) {
			loginRealizado = true;
			intentos = 0;
		}
		else {
			intentos++;
		}
		return loginRealizado;
	}
}
