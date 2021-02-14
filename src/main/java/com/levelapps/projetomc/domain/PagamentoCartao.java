package com.levelapps.projetomc.domain;

import javax.persistence.Entity;

import com.levelapps.projetomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoCartao() {

	}

	public PagamentoCartao(Integer id, EstadoPagamento estadoPagemento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagemento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
