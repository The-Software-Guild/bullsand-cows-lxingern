package com.wileyedge.service;

import java.util.List;

import com.wileyedge.model.Game;

public interface GameService {
	
	public int createNewGame();
	public List<Game> getAllGamesSanitised();
	public Game getGameByIdSanitised(int gameId);
	public void updateGameStatusIfGuessIsCorrect(String result, int gameId);

}
