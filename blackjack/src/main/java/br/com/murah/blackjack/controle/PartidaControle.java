package br.com.murah.blackjack.controle;

import java.util.Iterator;

import br.com.murah.blackjack.bean.BaralhoBean;
import br.com.murah.blackjack.bean.CartaBean;
import br.com.murah.blackjack.bean.JogadorBean;
import br.com.murah.blackjack.bean.MaoBean;
import br.com.murah.blackjack.bean.MesaBean;
import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.dao.BancoDadosFake;
import br.com.murah.blackjack.exceptions.AcessarMesaException;

public class PartidaControle {

	BaralhoControle baralhoControle = new BaralhoControle();
	BlackJackControle blackJackControle = new BlackJackControle();
	FinanceiroControle financeiroControle = new FinanceiroControle();

	// Esse já está pronto
	public MesaBean iniciarMesa(UsuarioBean usuario) {
		// UsuarioBean usuario =
		// BancoDadosFake.instance().getUsuario(idUsuario);

		JogadorBean jogador = new JogadorBean();
		jogador.setUsuario(usuario);
		BancoDadosFake.instance().addOrUpdate(jogador);

		MesaBean mesaBean = new MesaBean();
		mesaBean.getListaJogador().add(jogador);
		BancoDadosFake.instance().addOrUpdate(mesaBean);

		return mesaBean;
	}

	// TODO
	// Preparar o baralho na mesa
	// ativa todos os jogadores
	// zerar as maos e apostas dos jogadores
	// distribuir 2 cartas para cada mão dos jogadores
	// pegar 2 cartas para a mão da mesa
	// definir o jogador que iniciará o turno
	// Validar ao menos 1 jogador na mesa
	public void iniciarPartida(MesaBean mesa) throws AcessarMesaException {
		mesa.setBaralho(baralhoControle.prepararBaralho(BaralhoControle.BLACKJACK));
		for(int i=0;i< mesa.getListaJogador().size();i++){
			JogadorBean jogador = new JogadorBean();
			mesa.getListaJogador().get(i).setAtivo(true);
			mesa.getListaJogador().get(i).setMao(null);
			
			BaralhoBean baralho = new BaralhoBean();
			Iterator<CartaBean> iterator = baralho.getListaCartas().iterator();
			if (iterator.hasNext()) {
				mesa.getListaJogador().get(i).setMao(new MaoBean().getListaCarta().get(i))
				iterator.remove();
			}
			
			
			
		}
	}

	// TODO
	// Adicionar o Usuario como novo jogador na mesa
	// inicializar mão do jogador como inativo
	// Validar se o usuario já esta ná mesa e retornar throw caso já esteja
	public void entrarPartida(MesaBean mesa, UsuarioBean usuario) throws AcessarMesaException {

	}

	// TODO
	// realiza o debito do valor da aposta no usuario
	// realizar o credito no valor da aposta da mão do jogador
	// validar se a mão do jogador está ativa
	// validar se a aposta pode ser concluida
	// retornar true se sucesso na aposta ou false se houve falha
	public boolean fazerAposta(JogadorBean jogador, Long aposta) {

		return true;
	}

	// Desabilita o jogador
	// remove as fichas da mão do jogador
	// remove as cartas da mao do jogador
	public void sairPartida(MesaBean mesa, JogadorBean jogador) {

	}
	
	public void comprarCarta(MesaBean mesa, JogadorBean jogador){
		
	}

}
