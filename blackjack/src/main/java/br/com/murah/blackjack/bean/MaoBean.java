package br.com.murah.blackjack.bean;

import java.io.Serializable;
import java.util.List;

import br.com.murah.blackjack.bean.interfaces.BeanInterface;

public class MaoBean implements Serializable, BeanInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5045434175176163856L;
	private Long id;
	private List<CartaBean> listaCarta;
	private long aposta;
	private boolean ativo;

	public List<CartaBean> getListaCarta() {
		return listaCarta;
	}

	public void setListaCarta(List<CartaBean> listaCarta) {
		this.listaCarta = listaCarta;
	}

	public long getAposta() {
		return aposta;
	}

	public void setAposta(long aposta) {
		this.aposta = aposta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
