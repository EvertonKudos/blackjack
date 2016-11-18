package br.com.murah.blackjack;

import org.junit.Before;
import org.junit.Test;

import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.controle.FinanceiroControle;
import br.com.murah.blackjack.exceptions.UsuarioSaldoException;
import junit.framework.Assert;

public class TesteSaldoUsuario {

	private FinanceiroControle controle;

	@Before
	public void init() {
		controle = new FinanceiroControle();
	}

	@Test
	public void testeSaldoCredito() {
		UsuarioBean usuario = new UsuarioBean();
		Assert.assertEquals(usuario.getSaldo(), 0l);
		Long creditarUsuario = controle.creditarUsuario(usuario, -10l);
		Assert.assertEquals(usuario.getSaldo(), 0l);
		creditarUsuario = controle.creditarUsuario(usuario, 1000l);
		Assert.assertEquals(creditarUsuario.longValue(), 1000l);
		creditarUsuario = controle.creditarUsuario(usuario, 500l);
		Assert.assertEquals(creditarUsuario.longValue(), 1500l);
		Assert.assertEquals(usuario.getSaldo(), 1500l);
	}

	@Test
	public void testeSaldoDebito() {
		UsuarioBean usuario = new UsuarioBean();
		Assert.assertEquals(usuario.getSaldo(), 0l);
		Long creditarUsuario;
		try {
			creditarUsuario = controle.debitarUsuario(usuario, -10l);
			Assert.fail();
		} catch (UsuarioSaldoException e) {
		}

		try {
			creditarUsuario = controle.debitarUsuario(usuario, 1l);
			Assert.fail();
		} catch (UsuarioSaldoException e) {
			controle.creditarUsuario(usuario, 100l);
		}

		try {
			creditarUsuario = controle.debitarUsuario(usuario, 50l);
			Assert.assertEquals(creditarUsuario.longValue(), 50l);
			Assert.assertEquals(usuario.getSaldo(), 50l);
			creditarUsuario = controle.debitarUsuario(usuario, 50l);
			Assert.assertEquals(creditarUsuario.longValue(), 0l);
			Assert.assertEquals(usuario.getSaldo(), 0l);
		} catch (UsuarioSaldoException e) {
			Assert.fail();
		}

		try {
			controle.creditarUsuario(usuario, 100l);
			creditarUsuario = controle.debitarUsuario(usuario, 101l);
			Assert.fail();
		} catch (UsuarioSaldoException e) {
		}
	}
}
