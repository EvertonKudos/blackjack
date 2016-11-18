package br.com.murah.blackjack.bean;

import java.io.Serializable;

import br.com.murah.blackjack.bean.interfaces.BeanInterface;

public class JogadorBean implements Serializable, BeanInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8908155389770356019L;
	private Long id;
	private UsuarioBean usuario;
	private MaoBean mao;
	private Boolean ativo = false;

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public MaoBean getMao() {
		return mao;
	}

	public void setMao(MaoBean mao) {
		this.mao = mao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	
}
