package br.com.murah.blackjack;

import org.junit.Before;
import org.junit.Test;

import br.com.murah.blackjack.bean.JogadorBean;
import br.com.murah.blackjack.bean.MaoBean;
import br.com.murah.blackjack.bean.MesaBean;
import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.controle.BlackJackControle;
import br.com.murah.blackjack.controle.FinanceiroControle;
import br.com.murah.blackjack.controle.PartidaControle;
import br.com.murah.blackjack.exceptions.AcessarMesaException;
import junit.framework.Assert;

public class TesteInicilizacaoMesa {

	private final static int totalNaipe = 13;

	private FinanceiroControle financeiroControle;
	private PartidaControle partidaControle;

	@Before
	public void init() {
		financeiroControle = new FinanceiroControle();
		partidaControle = new PartidaControle();
	}

	@Test
	public void testeIniciarMesa() {
		UsuarioBean usuario = new UsuarioBean();
		MesaBean mesa = partidaControle.iniciarMesa(usuario);
		Assert.assertEquals(mesa.getListaJogador().iterator().next().getUsuario(), usuario);
	}

	@Test
	public void testeIniciarPartida() throws AcessarMesaException {
		UsuarioBean usuario = new UsuarioBean();
		MesaBean mesa = partidaControle.iniciarMesa(usuario);
		financeiroControle.creditarUsuario(usuario, 1000l);
		BlackJackControle blackJackControle = new BlackJackControle();
		blackJackControle.iniciarPartida(mesa);
		Assert.assertEquals(mesa.getBaralho().getListaCartas().size(),
				(totalNaipe * 4 - 2 - 2 * mesa.getListaJogador().size()));
		for (JogadorBean j : mesa.getListaJogador()) {
			Assert.assertEquals(j.getMao().getAposta(), 0);
			Assert.assertEquals(j.getMao().getListaCarta().size(), 2);
			Assert.assertTrue(j.getMao().getAtivo());
		}
		Assert.assertEquals(mesa.getMao().getListaCarta().size(), 2);
		mesa.getListaJogador().clear();
		blackJackControle.iniciarPartida(mesa);
		Assert.fail();
	}

	@Test
	public void testeEntrarPartida() {
		MesaBean mesa = new MesaBean();

		try {
			partidaControle.entrarPartida(mesa, new UsuarioBean());
			partidaControle.entrarPartida(mesa, new UsuarioBean());
			partidaControle.entrarPartida(mesa, new UsuarioBean());
			partidaControle.entrarPartida(mesa, new UsuarioBean());

			for (JogadorBean jogador : mesa.getListaJogador()) {
				int i = 0;
				Assert.assertNotNull(jogador.getUsuario());
				Assert.assertFalse(jogador.getAtivo());
				Assert.assertNotSame(mesa.getListaJogador().get(i), jogador);
				i++;
			}

		} catch (AcessarMesaException e) {
			Assert.fail();
		}

		try {

			JogadorBean jogador = gerarJogador(new UsuarioBean(), false);
			mesa.getListaJogador().add(jogador);

		
			partidaControle.entrarPartida(mesa, new UsuarioBean());
			partidaControle.entrarPartida(mesa, new UsuarioBean());
			partidaControle.entrarPartida(mesa, jogador.getUsuario());
			partidaControle.entrarPartida(mesa, new UsuarioBean());

			for (JogadorBean jog : mesa.getListaJogador()) {
				int i = 0;
				Assert.assertNotNull(jog.getUsuario());
				Assert.assertFalse(jog.getAtivo());
				Assert.assertNotSame(mesa.getListaJogador().get(i), jog);
				i++;
			}

			Assert.fail();
		} catch (AcessarMesaException e) {
		}

	}

	private JogadorBean gerarJogador(UsuarioBean usuario, boolean ativo) {
		JogadorBean jogador = new JogadorBean();

		jogador.setUsuario(usuario);
		jogador.setMao(new MaoBean());
		jogador.getMao().setAtivo(ativo);

		return jogador;
	}

}
