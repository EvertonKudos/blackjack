package br.com.murah.blackjack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.murah.blackjack.bean.BaralhoBean;
import br.com.murah.blackjack.bean.CartaBean;
import br.com.murah.blackjack.controle.BaralhoControle;
import br.com.murah.blackjack.exceptions.FimBaralhoException;

public class TesteMontagemBaralho {

	private BaralhoControle baralhoControle;

	private final static int totalNaipe = 13;

	@Before
	public void init() {
		baralhoControle = new BaralhoControle();

	}

	@Test
	public void testeBaralhoBlackJack() {
		BaralhoBean baralho = baralhoControle.prepararBaralho(BaralhoControle.BLACKJACK);
		Assert.assertEquals(baralho.getListaCartas().size(), (totalNaipe * 4));
		Map<Integer, Integer> contador = contarCartas(baralho);
		Assert.assertEquals(totalNaipe, contador.get(CartaBean.PAUS).intValue());
		Assert.assertEquals(totalNaipe, contador.get(CartaBean.COPAS).intValue());
		Assert.assertEquals(totalNaipe, contador.get(CartaBean.OUROS).intValue());
		Assert.assertEquals(totalNaipe, contador.get(CartaBean.ESPADAS).intValue());
	}

	@Test
	public void testePegarCarta() {
		Map<Integer, Integer> contadorCarta = mapContador();
		BaralhoBean baralho = baralhoControle.prepararBaralho(BaralhoControle.BLACKJACK);
		List<CartaBean> listaCartas = baralho.getListaCartas();

		Assert.assertEquals(baralho.getListaCartas().size(), (totalNaipe * 4));

		CartaBean carta;

		for (int i = baralho.getListaCartas().size(); i > 0; i--) {
			try {
				carta = baralhoControle.pegarCarta(baralho);
				Assert.assertNotNull(carta);
				contadorCarta.put(carta.getNaipe(), contadorCarta.get(carta.getNaipe()) + 1);
			} catch (FimBaralhoException e) {
				Assert.fail();
			}
		}

		Assert.assertEquals(totalNaipe, contadorCarta.get(CartaBean.PAUS).intValue());
		Assert.assertEquals(totalNaipe, contadorCarta.get(CartaBean.COPAS).intValue());
		Assert.assertEquals(totalNaipe, contadorCarta.get(CartaBean.OUROS).intValue());
		Assert.assertEquals(totalNaipe, contadorCarta.get(CartaBean.ESPADAS).intValue());
		Assert.assertEquals(0, baralho.getListaCartas().size());
		Assert.assertEquals(listaCartas, baralho.getListaCartas());

		try {
			carta = baralhoControle.pegarCarta(baralho);
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private Map<Integer, Integer> contarCartas(BaralhoBean baralho) {
		Map<Integer, Integer> contadorCarta = mapContador();
		for (CartaBean carta : baralho.getListaCartas()) {
			int valor = contadorCarta.get(carta.getNaipe());
			valor++;
			contadorCarta.put(carta.getNaipe(), valor);
		}
		return contadorCarta;

	}

	private Map<Integer, Integer> mapContador() {
		Map<Integer, Integer> contadorCarta = new HashMap<Integer, Integer>();
		contadorCarta.put(Integer.valueOf(CartaBean.COPAS), 0);
		contadorCarta.put(Integer.valueOf(CartaBean.ESPADAS), 0);
		contadorCarta.put(Integer.valueOf(CartaBean.OUROS), 0);
		contadorCarta.put(Integer.valueOf(CartaBean.PAUS), 0);
		return contadorCarta;
	}

	// @Test
	// public void testeBaralhoPAciencia() {
	// BaralhoBean baralho = baralhoControle
	// .prepararBaralho(BaralhoControle.PACIENCIA);
	// Assert.assertEquals(baralho.getListaCartas().size(), (13 * 8));
	// }
}
