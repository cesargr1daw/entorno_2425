package com.auth;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CredencialTest {
	private Credencial credencial;
	private Credencial credencialInsegura;
	private Credencial credencialCortoNombre;
	private Credencial credencialCortoApellidos;
	private Credencial credencialCortoNombreApellidos;

	@BeforeEach
	public void setUp() {
		Credencial.secuencia = 100;
		credencial = new Credencial("Juan", "Perez", "password123");
		credencialInsegura = new Credencial("Ana", "Lopez", "12345");
		credencialCortoNombre = new Credencial("Ana", "Lopez", "password123");
		credencialCortoApellidos = new Credencial("Ana", "Lo", "password123");
		credencialCortoNombreApellidos = new Credencial("An", "Lo", "password123");
	}

	@Test
	public void testGenerarUsername() {
		assertEquals("analop102", credencialCortoNombre.getUsername());
		assertEquals("analo103", credencialCortoApellidos.getUsername());
		assertEquals("anlo104", credencialCortoNombreApellidos.getUsername());
	}

	@Test
	public void testComprobarPassword() {
		assertTrue(credencial.comprobarPassword("password123"));
		assertFalse(credencial.comprobarPassword("incorrecta"));
	}

	@Test
	public void testEsPasswordSegura() {
		assertTrue(credencial.esPasswordSegura());
		assertFalse(credencialInsegura.esPasswordSegura());
	}


	@Test
	public void testSetPassword() {
		credencial.setPassword("nuevaPassword123");
		assertTrue(credencial.comprobarPassword("nuevaPassword123"));
		assertFalse(credencial.comprobarPassword("password123"));
	}

	@Test
	public void testGetUsername() {
		assertEquals("juaper100", credencial.getUsername());
	}
}
