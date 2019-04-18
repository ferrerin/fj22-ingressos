package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {

		Sala sala = new Sala("Sala Cara", BigDecimal.TEN);
		Filme filme = new Filme("Uau", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Lugar lugar = new Lugar("A", 1);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, lugar);

		BigDecimal precoEsperado = new BigDecimal("20.00");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());

	}

}
