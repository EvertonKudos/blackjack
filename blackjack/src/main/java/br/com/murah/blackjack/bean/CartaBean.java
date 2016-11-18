package br.com.murah.blackjack.bean;

import java.io.Serializable;

public class CartaBean implements Comparable<CartaBean>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365672645231250875L;
	public final static int PAUS = 1;
	public final static int COPAS = 2;
	public final static int ESPADAS = 3;
	public final static int OUROS = 4;

	private int naipe;
	private Integer numero;

	public CartaBean(int naipe, int numero) {
		this.naipe = naipe;
		this.numero = numero;
	}

	public int getNaipe() {
		return naipe;
	}

	public Integer getNumero() {
		return numero;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaBean other = (CartaBean) obj;
		if (naipe != other.naipe)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public int compareTo(CartaBean o) {
		if (this.naipe < o.naipe)
			return -1;
		else if (this.naipe > o.naipe)
			return 1;
		else {
			if (this.numero < o.numero)
				return -1;
			else if (this.numero > o.numero)
				return 1;
		}
		return 0;
	}

}
