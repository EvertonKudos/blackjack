package br.com.murah.blackjack.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.murah.blackjack.bean.interfaces.BeanInterface;

public class MesaBean implements Serializable, BeanInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5758995027662369385L;
	private Long id;
	private BaralhoBean baralho;
	private List<JogadorBean> listaJogador = new ArrayList<JogadorBean>();
	private MaoBean mao;
	private Long totalAposta;
	private JogadorBean turno;

	public BaralhoBean getBaralho() {
		return baralho;
	}

	public void setBaralho(BaralhoBean baralho) {
		this.baralho = baralho;
	}

	public Integer getTotalJogadorAtivo() {
		Integer contador = 0;
		for (JogadorBean jogador : listaJogador)
			if (jogador.getAtivo())
				contador++;
		return contador;
	}

	public List<JogadorBean> getListaJogador() {
		return listaJogador;
	}

	public void setListaJogador(List<JogadorBean> listaJogador) {
		this.listaJogador = listaJogador;
	}

	public MaoBean getMao() {
		return mao;
	}

	public void setMao(MaoBean mao) {
		this.mao = mao;
	}

	public Long getTotalAposta() {
		return totalAposta;
	}

	public void setTotalAposta(Long totalAposta) {
		this.totalAposta = totalAposta;
	}

	public JogadorBean getTurno() {
		return turno;
	}

	public void setTurno(JogadorBean turno) {
		this.turno = turno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
