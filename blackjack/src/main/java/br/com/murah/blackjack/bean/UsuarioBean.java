package br.com.murah.blackjack.bean;

import java.io.Serializable;

import br.com.murah.blackjack.bean.interfaces.BeanInterface;

public class UsuarioBean implements Serializable, BeanInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9173347762715309049L;
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private long saldo = 0;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		if (saldo == null)
			this.saldo = 0;
		else
			this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
