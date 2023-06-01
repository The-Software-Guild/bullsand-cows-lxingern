package com.wileyedge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wileyedge.dao.GameDao;
import com.wileyedge.exceptions.GameNotFoundException;
import com.wileyedge.model.Game;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;
	
	public GameServiceImpl(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	@Override
	public int createNewGame() throws DataAccessException {
		String answer = generateAnswer();
		
		int nextId;
		List<Game> allGames = gameDao.getAllGames();
		if (allGames.size() == 0) {
			nextId = 1;
		} else {
			Game gameWithHighestId = allGames.stream().max((g1, g2) -> g1.getGameId() - g2.getGameId()).get();
			nextId = gameWithHighestId.getGameId() + 1;
		}
		
		Game newGame = new Game(nextId, answer);
		
		gameDao.saveGame(newGame);		

		return newGame.getGameId();
	}

	@Override
	public List<Game> getAllGamesSanitised() throws DataAccessException {
		List<Game> games = gameDao.getAllGames();
		List<Game> sanitisedList = new ArrayList<>();
		games.stream().forEach((g) -> {
			if (g.isOngoing()) {
				sanitisedList.add(new Game(g.getGameId(), "hidden", g.isOngoing()));
			} else {
				sanitisedList.add(g);
			}
		});
		
		return sanitisedList;
	}

	@Override
	public Game getGameByIdSanitised(int gameId) throws GameNotFoundException, DataAccessException {
		Game game = gameDao.getGameById(gameId);
		if (game == null) throw new GameNotFoundException();
		
		if (game.isOngoing()) return new Game(game.getGameId(), "hidden", game.isOngoing());
		
		return game;
	}

	@Override
	public void updateGameStatusIfGuessIsCorrect(String result, int gameId) {
		if (result.charAt(2) == '4') {
			gameDao.updateGameStatus(gameId);
		}
	}

	private String generateAnswer() {
		Random rng = new Random();
		int digit1 = rng.nextInt(9) + 1;
		
		int digit2 = -1;
		while (digit2 < 0 || digit2 == digit1) {
			digit2 = rng.nextInt(10);
		}
		
		int digit3 = -1;
		while (digit3 < 0 || digit3 == digit2 || digit3 == digit1) {
			digit3 = rng.nextInt(10);
		}
		
		int digit4 = -1;
		while (digit4 < 0 || digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
			digit4 = rng.nextInt(10);
		}
		
		return String.valueOf(digit1) + String.valueOf(digit2) + String.valueOf(digit3) + String.valueOf(digit4);
	}
	
}
