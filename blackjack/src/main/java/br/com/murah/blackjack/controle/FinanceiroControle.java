package br.com.murah.blackjack.controle;

import br.com.murah.blackjack.bean.UsuarioBean;
import br.com.murah.blackjack.exceptions.UsuarioSaldoException;

public class FinanceiroControle {

	// TODO
	// Realizad o desconto do saldo do usuario
	// caso não seja possível retirar o valor solicitado será necessário lançar
	// a exception
	public Long debitarUsuario(UsuarioBean usuario, Long valor) throws UsuarioSaldoException {

		return 0l;
	}

	// TODO
	// Apenas adiciona ao saldo do usuario o valor atual + o valor passado
	public Long creditarUsuario(UsuarioBean usuario, Long valor) {

		return 0l;
	}
}
