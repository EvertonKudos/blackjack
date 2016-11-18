package br.com.murah.blackjack.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaralhoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1566208651172407379L;
	List<CartaBean> listaCartas = new ArrayList<CartaBean>();

	public List<CartaBean> getListaCartas() {
		return listaCartas;
	}

	public void setListaCartas(List<CartaBean> listaCartas) {
		this.listaCartas = listaCartas;
	}

}
