package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;

@Component
public class OmdbClient {

	public Optional<DetalhesDoFilme> request(String nomeDoFilme) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			nomeDoFilme = nomeDoFilme.replace(" ", "+");
			String url = "http://omdb-fj22.herokuapp.com/movie?title=" + nomeDoFilme;

			DetalhesDoFilme detalhes = restTemplate.getForObject(url, DetalhesDoFilme.class);

			Optional<DetalhesDoFilme> optional = Optional.ofNullable(detalhes);

			return optional;
		} catch (RestClientException e) {
			return Optional.empty();
		}

	}
}
