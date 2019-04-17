package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class OmdbClient {

	public <T>Optional<T> request(String nomeDoFilme, Class<T> tipoClasse) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			nomeDoFilme = nomeDoFilme.replace(" ", "+");
			String url = "http://omdb-fj22.herokuapp.com/movie?title=" + nomeDoFilme;

			T resultado = restTemplate.getForObject(url, tipoClasse);

			Optional<T> optional = Optional.ofNullable(resultado);

			return optional;
		} catch (RestClientException e) {
			return Optional.empty();
		}

	}

}
