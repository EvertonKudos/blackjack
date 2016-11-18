package br.com.murah.blackjack;

import java.util.Iterator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import br.com.murah.blackjack.bean.CartaBean;
import br.com.murah.blackjack.bean.JogadorBean;
import br.com.murah.blackjack.bean.MaoBean;
import br.com.murah.blackjack.bean.MesaBean;
import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.controle.BlackJackControle;
import br.com.murah.blackjack.controle.FinanceiroControle;
import br.com.murah.blackjack.controle.PartidaControle;
import br.com.murah.blackjack.exceptions.AcessarMesaException;
import junit.framework.Assert;

public class TesteExecucaoPartida {
	private PartidaControle partidaControle;
	private FinanceiroControle financeiroControle;
	private BlackJackControle controle;

	@Before
	public void init() {
		partidaControle = new PartidaControle();
		financeiroControle = new FinanceiroControle();
		controle = new BlackJackControle();
	}

	@Test
	public void testeAposta() {
		UsuarioBean usuario = new UsuarioBean();
		JogadorBean jogador = new JogadorBean();
		jogador.setUsuario(usuario);
		jogador.setMao(new MaoBean());
		Assert.assertFalse(partidaControle.fazerAposta(jogador, 100l));
		jogador.getMao().setAtivo(true);
		jogador.getMao().setAposta(0);
		Assert.assertFalse(partidaControle.fazerAposta(jogador, 100l));
		Assert.assertEquals(jogador.getMao().getAposta(), 0);
		financeiroControle.creditarUsuario(usuario, 100l);

		partidaControle.fazerAposta(jogador, -50l);
		Assert.assertFalse(partidaControle.fazerAposta(jogador, -50l));
		Assert.assertEquals(jogador.getMao().getAposta(), 0);
		Assert.assertEquals(jogador.getUsuario().getSaldo(), 100);

		Assert.assertTrue(partidaControle.fazerAposta(jogador, 50l));
		Assert.assertEquals(jogador.getMao().getAposta(), 50);
		Assert.assertEquals(jogador.getUsuario().getSaldo(), 50);

		Assert.assertFalse(partidaControle.fazerAposta(jogador, 0l));

		Assert.assertTrue(partidaControle.fazerAposta(jogador, 50l));
		Assert.assertEquals(jogador.getMao().getAposta(), 100);
		Assert.assertEquals(jogador.getUsuario().getSaldo(), 0);

		Assert.assertFalse(partidaControle.fazerAposta(jogador, 1l));

	}

	@Test
	public void testeContarMao() {

		MaoBean mao = new MaoBean();
		prepararMao(mao, new int[] { 1, 10 });
		Assert.assertEquals(controle.contarMao(mao), -1);

		prepararMao(mao, new int[] { 11, 1 });
		Assert.assertEquals(controle.contarMao(mao), -1);

		prepararMao(mao, new int[] { 12, 1 });
		Assert.assertEquals(controle.contarMao(mao), -1);

		prepararMao(mao, new int[] { 12, 12 });
		Assert.assertEquals(controle.contarMao(mao), 20);

		prepararMao(mao, new int[] { 12, 12 });
		Assert.assertEquals(controle.contarMao(mao), 20);

		prepararMao(mao, new int[] { 12, 12, 1 });
		Assert.assertEquals(controle.contarMao(mao), 21);

		prepararMao(mao, new int[] { 1, 11, 12 });
		Assert.assertEquals(controle.contarMao(mao), 21);

		prepararMao(mao, new int[] { 1, 1, 1 });
		Assert.assertEquals(controle.contarMao(mao), 3);

		prepararMao(mao, new int[] { 11, 12, 11, 10 });
		Assert.assertEquals(controle.contarMao(mao), 40);

		prepararMao(mao, new int[] { 9, 1 });
		Assert.assertEquals(controle.contarMao(mao), 10);

		prepararMao(mao, new int[] { 10, 5 });
		Assert.assertEquals(controle.contarMao(mao), 15);

		prepararMao(mao, new int[] { 12, 1 });
		Assert.assertEquals(controle.contarMao(mao), -1);
	}

	private void prepararMao(MaoBean mao, int[] numeros) {
		mao.getListaCarta().clear();
		for (int i : numeros) {
			mao.getListaCarta().add(new CartaBean(new Random().nextInt(4), i));
		}
	}

	@Test
	public void testePodeComprarCarta() {
		MaoBean mao = new MaoBean();

		prepararMao(mao, new int[] { 9, 1 });
		Assert.assertTrue(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 5, 1, 3, 2, 9 });
		Assert.assertTrue(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 9, 2, 11 });
		Assert.assertFalse(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 12, 12, 1 });
		Assert.assertFalse(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 1, 12 });
		Assert.assertFalse(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 1, 10 });
		Assert.assertFalse(controle.podeComprarCarta(mao));

		prepararMao(mao, new int[] { 10, 10, 5 });
		Assert.assertFalse(controle.podeComprarCarta(mao));

	}

	@Test
	public void testeFinalizarPagamento() {
		MesaBean mesa = new MesaBean();
		mesa.setMao(new MaoBean());

		// 25
		mesa.getListaJogador().add(gerarJogador(new UsuarioBean(), new int[] { 10, 10, 5 }));
		// 21
		mesa.getListaJogador().add(gerarJogador(new UsuarioBean(), new int[] { 10, 11, 1 }));
		// -1
		mesa.getListaJogador().add(gerarJogador(new UsuarioBean(), new int[] { 12, 1 }));
		// 10
		mesa.getListaJogador().add(gerarJogador(new UsuarioBean(), new int[] { 9, 1 }));
		// 19
		mesa.getListaJogador().add(gerarJogador(new UsuarioBean(), new int[] { 10, 9 }));

		// 25
		resetApostaJogador(mesa, new long[] { 10, 8, 2, 20, 1 });
		prepararMao(mesa.getMao(), new int[] { 10, 10, 5 });
		controle.finalizarPagamento(mesa);
		assertSaldoJogador(mesa, new long[] { 15, 12, 3, 30, 1 });

		// 19
		resetApostaJogador(mesa, new long[] { 10, 8, 2, 20, 1 });
		prepararMao(mesa.getMao(), new int[] { 10, 4, 5 });
		controle.finalizarPagamento(mesa);
		assertSaldoJogador(mesa, new long[] { 0, 12, 3, 0, 1 });

		// -1
		resetApostaJogador(mesa, new long[] { 10, 8, 2, 20, 1 });
		prepararMao(mesa.getMao(), new int[] { 12, 1 });
		controle.finalizarPagamento(mesa);
		assertSaldoJogador(mesa, new long[] { 0, 0, 3, 0, 0 });

		// 21
		resetApostaJogador(mesa, new long[] { 10, 8, 2, 20, 1 });
		prepararMao(mesa.getMao(), new int[] { 12, 10, 1 });
		controle.finalizarPagamento(mesa);
		assertSaldoJogador(mesa, new long[] { 0, 12, 3, 0, 0 });

		// 9
		resetApostaJogador(mesa, new long[] { 10, 8, 2, 20, 1 });
		prepararMao(mesa.getMao(), new int[] { 8, 1 });
		controle.finalizarPagamento(mesa);
		assertSaldoJogador(mesa, new long[] { 0, 12, 3, 30, 1 });

	}

	private void resetApostaJogador(MesaBean mesa, long[] apostaFinal) {
		Iterator<JogadorBean> it = mesa.getListaJogador().iterator();
		JogadorBean jogador;
		for (long aposta : apostaFinal) {
			jogador = it.next();
			jogador.getUsuario().setSaldo(0l);
			jogador.getMao().setAposta(aposta);
		}
	}

	private void assertSaldoJogador(MesaBean mesa, long[] saldoFinal) {
		Iterator<JogadorBean> it = mesa.getListaJogador().iterator();
		JogadorBean jogador;
		for (long saldo : saldoFinal) {
			jogador = it.next();
			Assert.assertEquals(jogador.getUsuario().getSaldo(), saldo);
		}

	}

	private JogadorBean gerarJogador(UsuarioBean usuario, int[] cartas) {
		JogadorBean jogador = new JogadorBean();

		jogador.setMao(new MaoBean());
		jogador.setUsuario(usuario);
		jogador.setAtivo(true);
		prepararMao(jogador.getMao(), cartas);
		return jogador;
	}
}
