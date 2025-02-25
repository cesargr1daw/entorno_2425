package com.auth;

public class Credencial {
	protected String username;
	protected String password;
	protected static int secuencia = 100;

	public Credencial(String nombre, String apellidos, String password) {
		super();
		this.username = generarUsername(nombre, apellidos);
		this.secuencia++;
		this.password = password;
	}

	public static String generarUsername(String nombre, String apellidos) {
		String usernameGenerado = "";
		if (nombre.length() > 3) {
			usernameGenerado += nombre.toLowerCase().substring(0, 3);
		} else {
			usernameGenerado += nombre.toLowerCase();
		}
		if (apellidos.length() > 3) {
			usernameGenerado += apellidos.toLowerCase().substring(0, 3);
		} else {
			usernameGenerado += apellidos.toLowerCase();
		}
		return usernameGenerado + secuencia;
	}

	public boolean comprobarPassword(String otraPassword) {
		return this.password.equals(otraPassword);
	}

	public boolean esPasswordSegura() {
		boolean tieneLetra=false;
		boolean tieneNumero=false;
		if(this.password.length() >= 8) {
			for(int i=0;i<password.length();i++) {
				if(Character.isDigit(password.charAt(i))) {
					tieneNumero=true;
				}else if(Character.isAlphabetic(password.charAt(i))) {
					tieneLetra=true;
				}
			}
		}
		return tieneLetra&&tieneNumero; 
	}

	public void setPassword(String newpass) {
		this.password = newpass;
	}

	public String getUsername() {
		return this.username;
	}
}