package br.com.murah.blackjack.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import br.com.murah.blackjack.bean.BaralhoBean;
import br.com.murah.blackjack.bean.CartaBean;
import br.com.murah.blackjack.exceptions.FimBaralhoException;

public class BaralhoControle {

	public final static int BLACKJACK = 1;
	public final static int PACIENCIA = 2;
	public final static int POKER = 3;

	public static void main(String[] args) {
		// BaralhoControle controle = new BaralhoControle();
		// BaralhoBean baralho = controle.prepararBaralho(BLACKJACK);
		// int i = 0;
		// {
		// CartaBean pegarCarta = controle.pegarCarta(baralho);
		// }
		// while (i < 3) {
		// try {
		// System.out.println(pegarCarta.getNumero() + " - " +
		// pegarCarta.getNaipe());
		// } catch (BaralhoSemFimException e) {
		// System.out.println("Acabou o baralho");
		// baralho = controle.prepararBaralho(BLACKJACK);
		// i++;
		// }
		// }
		// BaralhoBean baralho = controle.prepararBaralho(BLACKJACK);
		// System.out.println("HAHAHAHAHA");
		// BaralhoBean baralho = new BaralhoBean();
		// List<CartaBean> listaCartas = baralho.getListaCartas();
		// baralho.getListaCartas().addAll(controle.gerarBaralho(CartaBean.PAUS));
		//
		// controle.embaralhar(baralho);
		//
		// for (CartaBean car : listaCartas)
		// System.out.println(car.getNumero());
		// List<Integer> lista = new ArrayList<Integer>();
		// lista.addAll(Arrays.asList(new Integer[] { 5, 6, 8, 7, 1, 9, 10, 8,
		// 589, 46546968 }));
		//
		// Iterator<Integer> iterator = lista.iterator();
		//
		// while (iterator.hasNext()) {
		// Integer interno = iterator.next();
		// System.out.println("exibir " + interno);
		// iterator.remove();
		// System.out.println("tamanho " + lista.size());
		// }
		// for (Integer numero : lista) {
		// System.out.println(" olha no for " + numero);
		// lista.add(28);
		// if (numero % 2 == 0)
		// lista.remove(numero);
		// }
		// System.out.println("antes " + numero);
		// continue;
		// else if (numero % 9 == 0) {
		// System.out.println("terminei " + numero);
		// break;
		// }
		// System.out.println("depois " + numero);
		// }

	}

	public BaralhoBean prepararBaralho(int tipoBaralho) {

		BaralhoBean baralho = null;
		switch (tipoBaralho) {
		case BLACKJACK:
			baralho = montarBaralhoPadrao();
			break;
		case PACIENCIA:
			baralho = montarBaralhoPaciencia();
			break;
		case POKER:
			baralho = montarBaralhoPadrao();
			break;
		default:
			break;
		}

		embaralhar(baralho);

		return baralho;
	}

	private BaralhoBean montarBaralhoPaciencia() {
		BaralhoBean baralho = new BaralhoBean();

		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));
		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));

		return baralho;
	}

	private void embaralhar(BaralhoBean baralho) {
		CartaBean[] vetor = new CartaBean[baralho.getListaCartas().size()];
		vetor = baralho.getListaCartas().toArray(vetor);

		int seed = vetor.length - 1;
		Random random = new Random();
		for (int i = vetor.length * vetor.length; i > 0; i--) {
			int a = (random.nextInt(seed));
			int b = (random.nextInt(seed));
			CartaBean temp = vetor[a];
			vetor[a] = vetor[b];
			vetor[b] = temp;
		}
		baralho.getListaCartas().clear();
		baralho.getListaCartas().addAll(Arrays.asList(vetor));
	}

	private BaralhoBean montarBaralhoPadrao() {
		BaralhoBean baralho = new BaralhoBean();

		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.ESPADAS));

		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.PAUS));

		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.COPAS));

		baralho.getListaCartas().addAll(gerarBaralho(CartaBean.OUROS));
		return baralho;
	}

	private List<CartaBean> gerarBaralho(int naipe) {
		List<CartaBean> arrayList = new ArrayList<CartaBean>();
		for (int i = 1; i < 14; i++) {
			arrayList.add(new CartaBean(naipe, i));
		}
		return arrayList;
	}

	// Pega a carta do topo do baralho
	// Caso não tenha mais cartas para pegar deve estourar uma exception
	public CartaBean pegarCarta(BaralhoBean baralho) throws FimBaralhoException {
		Iterator<CartaBean> iterator = baralho.getListaCartas().iterator();
		if (iterator.hasNext()) {
			CartaBean next = iterator.next();
			iterator.remove();
			return next;
		} else
			throw new FimBaralhoException();
	}

}
