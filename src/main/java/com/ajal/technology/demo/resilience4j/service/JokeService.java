package com.ajal.technology.demo.resilience4j.service;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ajal.technology.demo.resilience4j.model.Joke;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JokeService {

	private final RestTemplate restTemplate;

	public JokeService() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(2000); // 2 segundos
		factory.setReadTimeout(2000); // 2 segundos
		this.restTemplate = new RestTemplate(factory);
	}
	
	@CircuitBreaker(name = "jokeService", fallbackMethod = "fallbackJoke")
	@Retry(name = "jokeService")	
	public Joke getRandomJoke() {
        String url = "http://10.0.01.10/random_joke";
		return restTemplate.getForObject(url, Joke.class);
	}
	
	public Joke fallbackJoke(Throwable t) {
		Joke joke = new Joke();
		joke.setId(0);
		joke.setType("fallback");
		joke.setSetup("No se pudo obtener el chiste.");
		joke.setPunchline("El servicio externo está caído.");
		return joke;
	}
}