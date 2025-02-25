package com.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

	private Usuario usuario;
	private Usuario usuarioConEmail;

	@BeforeEach
	public void setUp() {
		Credencial.secuencia = 100;
		usuario = new Usuario("Juan", "Perez", "password123");
		usuarioConEmail = new Usuario("Maria", "Lopez", "maria.lopez@example.com", "password456");
	}

	@Test
	public void testLoginExitosoYFallido() {
		assertTrue(usuario.hacerLogin("juaper100", "password123"));
		assertFalse(usuario.hacerLogin("juaper100", "wrongPassword"));
	}

	@Test
	public void testCuentaBloqueadaTrasIntentosFallidos() {
		usuario.hacerLogin("juaper100", "wrongPassword");
		usuario.hacerLogin("juaper100", "wrongPassword");
		assertTrue(usuario.esCuentaBloqueada());
	}

	@Test
	public void testLoginTrasCuentaBloqueada() {
		usuario.hacerLogin("juaper100", "wrongPassword");
		usuario.hacerLogin("juaper100", "wrongPassword");
		assertTrue(usuario.esCuentaBloqueada());
	}

	@Test
	public void testLoginTrasRecuperarCuenta() {
		usuario.hacerLogin("juaper100", "wrongPassword");
		assertEquals(1, usuario.intentos);
		usuario.hacerLogin("juaper100", "wrongPassword");
		assertEquals(2, usuario.intentos);
		assertTrue(usuario.esCuentaBloqueada());

		assertTrue(usuario.hacerLogin("juaper100", "password123"));
		assertEquals(0, usuario.intentos);
		assertFalse(usuario.esCuentaBloqueada());
	}



	@Test
	public void testModificarPassword() {
		assertTrue(usuario.modificarPassword("password123", "newPassword456", "newPassword456"));
		assertTrue(usuario.hacerLogin("juaper100", "newPassword456"));

		assertFalse(usuario.modificarPassword("password123", "password123", "password123"));
		assertFalse(usuario.modificarPassword("password123", "newPassword456", "differentPassword"));
	}

	@Test
	public void testEsPasswordSegura() {
		assertTrue(usuario.esPasswordSegura());

		assertTrue(usuario.modificarPassword("password123", "1234", "1234"));
		assertFalse(usuario.esPasswordSegura());
	}

	@Test
	public void testCuentaBloqueadaPorIntentos() {
		usuario.hacerLogin("juaper100", "wrongPassword");
		usuario.hacerLogin("juaper100", "wrongPassword");
		assertTrue(usuario.esCuentaBloqueada());
	}

	@BeforeEach
	public void setUp2 () {
		usuario = new Usuario("Juan", "Perez", "password123");
	}

	@Test
	public void testSetCredencial() {
		String usernameAnterior=(String)usuarioConEmail.credencial.getUsername();
		usuarioConEmail.setCredencial("Ana", "Guerra", "@Ana22Guerra");
		String nuevoUsername=(String)usuarioConEmail.credencial.getUsername();
		assertFalse(usernameAnterior.equals(nuevoUsername));


	}
}
