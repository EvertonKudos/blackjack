package br.com.murah.blackjack.controle;

import br.com.murah.blackjack.bean.MaoBean;
import br.com.murah.blackjack.bean.MesaBean;

public class BlackJackControle {

	// Considerando as regras do blackjack
	// Receber uma mão
	// realizar a soma do valor das cartas na mao
	// considerar as cartas J,Q,K como 10
	// considerar a carta A como 1
	// caso a mão com duas cartas tenha um As e um 10,J,Q,K deve ser retornado
	// -1
	// para computar um blackjack
	// lembrando que blackjack é quando a um As e uma carta que vale 10
	// caso contrario a contagem pode chegar a até 21, acima disso o jogador
	// perdeu automaticamente
	public int contarMao(MaoBean mao) {
		return 0;
	}

	// realizar o pagamento dos jogadores
	// ou realizar o recolhimento das apostas da mesa
	public void finalizarPagamento(MesaBean mesa) {

	}

	// Toda parte de distribuição de cartas contidos no metodo iniciarPartida da
	// classe PartidaControle
	// deve ser transferido para cá
	// a classe BlackJackControle irá gerencias todas os métodos relacionados ao
	// jogo de BlackJack
	public void iniciarPartida(MesaBean mesa) {

	}

	// Realizar a contagem da mão para validar se o usuario pode pegar mais uma
	// carta, deve retornar false caso o jogador tenha um BlackJack ou sua
	// contagem é >=21
	public boolean podeComprarCarta(MaoBean mao) {

		return false;
	}
}
