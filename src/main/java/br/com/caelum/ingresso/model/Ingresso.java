package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.ingresso.model.descontos.TipoDeDesconto;

@Entity
public class Ingresso {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Sessao sessao;
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private TipoDeDesconto tipoDeDesconto;

	@ManyToOne
	private Lugar lugar;

	/**
	 * @deprecated for hibernate
	 */
	public Ingresso() {
	}

	public Ingresso(Sessao sessao, TipoDeDesconto tipoDeDesconto, Lugar lugar) {
		super();
		this.sessao = sessao;
		this.tipoDeDesconto = tipoDeDesconto;
		this.preco = tipoDeDesconto.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar;
	}

	public TipoDeDesconto getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(TipoDeDesconto tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

}
