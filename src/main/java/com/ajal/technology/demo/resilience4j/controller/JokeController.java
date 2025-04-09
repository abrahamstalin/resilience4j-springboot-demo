package com.ajal.technology.demo.resilience4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajal.technology.demo.resilience4j.model.Joke;
import com.ajal.technology.demo.resilience4j.service.JokeService;

@RestController
public class JokeController {

	private final JokeService jokeService;

	public JokeController(JokeService jokeService) {
		this.jokeService = jokeService;
	}

	@GetMapping("/joke")
	public Joke getJoke() {
		return jokeService.getRandomJoke();
	}
}