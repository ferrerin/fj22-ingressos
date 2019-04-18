package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public enum TipoDeDesconto {
	
	INTEIRA(new SemDesconto()),
	BANCO(new DescontoParaBanco()),
	ESTUDANTE(new DescontoParaEstudante());
	
	private Desconto desconto;
	
	TipoDeDesconto (Desconto desconto) {
		this.desconto = desconto;
	}
	
	public Desconto getDesconto() {
		return desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return this.desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao() {
		return this.desconto.getDescricao();
	}
}
