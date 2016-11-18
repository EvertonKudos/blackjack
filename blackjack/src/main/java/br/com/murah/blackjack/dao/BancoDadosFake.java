package br.com.murah.blackjack.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.murah.blackjack.bean.JogadorBean;
import br.com.murah.blackjack.bean.MaoBean;
import br.com.murah.blackjack.bean.MesaBean;
import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.bean.interfaces.BeanInterface;

public class BancoDadosFake {

	private static BancoDadosFake instance;

	private Map<Long, MesaBean> mapMesa = new HashMap<Long, MesaBean>();
	private Map<Long, UsuarioBean> mapUsuario = new HashMap<Long, UsuarioBean>();
	private Map<Long, JogadorBean> mapJogador = new HashMap<Long, JogadorBean>();
	private Map<Long, MaoBean> mapMao = new HashMap<Long, MaoBean>();

	public MesaBean getMesa(Long id) {
		return mapMesa.get(id);
	}

	public UsuarioBean getUsuario(Long id) {
		return mapUsuario.get(id);
	}

	public JogadorBean getJogador(Long id) {
		return mapJogador.get(id);
	}

	public MaoBean getMao(Long id) {
		return mapMao.get(id);
	}

	public Long addOrUpdate(BeanInterface bean) {
		@SuppressWarnings("unchecked")
		Map<Long, BeanInterface> mapa = (Map<Long, BeanInterface>) getMapByClass(bean.getClass());
		if (bean.getId() == null)
			bean.setId(getNextId(mapa.keySet()));
		mapa.put(bean.getId(), bean);
		return bean.getId();
	}

	private Long getNextId(Set<Long> set) {
		Long maxId = 0l;
		for (Long id : set)
			if (maxId < id)
				maxId = id;
		return ++maxId;
	}

	private Map<Long, ?> getMapByClass(Class<? extends BeanInterface> classe) {
		if (classe.equals(MaoBean.class)) {
			return mapMao;
		} else if (classe.equals(MesaBean.class)) {
			return mapMesa;
		} else if (classe.equals(UsuarioBean.class)) {
			return mapUsuario;
		} else if (classe.equals(JogadorBean.class)) {
			return mapJogador;
		}
		return null;
	}

	public static BancoDadosFake instance() {
		if (instance == null)
			instance = new BancoDadosFake();
		return instance;
	}
}
