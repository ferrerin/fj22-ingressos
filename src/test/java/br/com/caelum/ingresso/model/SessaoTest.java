package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.ingresso.model.descontos.TipoDeDesconto;
import org.junit.Assert;
import org.junit.Test;


public class SessaoTest {

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme () {
		
		Sala sala = new Sala ("Eldorado - IMax", new BigDecimal("22.5"));
		Filme filme = new Filme("Hey", Duration.ofMinutes(90),"Drama", new BigDecimal("12"));
		
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
		
	}

	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2eA3estaolivres (){

		Lugar a1 = new Lugar ("A",1);
		Lugar a2 = new Lugar ("A",2);
		Lugar a3 = new Lugar ("A",3);

		Filme filme = new Filme("Titanic", Duration.ofMinutes(120),"Drama",BigDecimal.TEN);

		Sala sala = new Sala("Sala 1", BigDecimal.ONE);

		Sessao sessao = new Sessao(LocalTime.now(), sala, filme);

		Ingresso ingresso = new Ingresso(sessao, TipoDeDesconto.BANCO, a1);

		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());

		sessao.setIngressos(ingressos);

		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));

	}
}
