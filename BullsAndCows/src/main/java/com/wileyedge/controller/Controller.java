package com.wileyedge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.model.Game;
import com.wileyedge.model.Round;
import com.wileyedge.service.GameService;
import com.wileyedge.service.RoundService;

@RestController
public class Controller {

	@Autowired
	GameService gameService;
	
	@Autowired
	RoundService roundService;
	
	@PostMapping("/begin")
	public ResponseEntity<Map<String, String>> startGame() {
		int newGameId = gameService.createNewGame();	
		
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("gameId", String.valueOf(newGameId));
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
	}
	
	@PostMapping("/guess")
	public Round makeGuess(@RequestBody Round round) {
		roundService.checkGuess(round);
		
		gameService.updateGameStatusIfGuessIsCorrect(round.getResult(), round.getGameId());
		
		return round;
	}
	
	@GetMapping("/game")
	public List<Game> getAllGames() {
		return gameService.getAllGamesSanitised();
	}
	
	@GetMapping("/game/{gameId}")
	public Game getGameById(@PathVariable("gameId") int gameId) {
		return gameService.getGameByIdSanitised(gameId);
	}
	
	@GetMapping("/rounds/{gameId}")
	public List<Round> getRoundsForGame(@PathVariable("gameId") int gameId) {
		return roundService.getRoundsForGameSorted(gameId);
	}
}
