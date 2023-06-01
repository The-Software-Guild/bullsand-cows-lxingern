package com.wileyedge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wileyedge.dao.GameDao;
import com.wileyedge.exceptions.GameNotFoundException;
import com.wileyedge.model.Game;

class GameServiceImplTest {
	
	GameDao dao;
	GameService gameService;
	
	@BeforeEach
	void setUp() {
		dao = mock(GameDao.class);
		gameService = new GameServiceImpl(dao);
	}

	@Test
	void createNewGameReturnsUniqueGameId() {
		List<Game> games = new ArrayList<>();
		games.add(new Game(1, "2538", false));
		games.add(new Game(2, "7328", false));
		when(dao.getAllGames()).thenReturn(games);
		
		int newGameId = gameService.createNewGame();
		
		assertEquals(3, newGameId);
	}
	
	@Test
	void createNewGameReturnsCorrectGameIdIfNoGamesExisting() {
		List<Game> games = new ArrayList<>();
		when(dao.getAllGames()).thenReturn(games);
		
		int newGameId = gameService.createNewGame();
		
		assertEquals(1, newGameId);
	}
	
	@Test
	void getAllGamesSanitisedReturnsGamesWithInfoHiddenCorrectly() {
		Game game1 = new Game(1, "2538", false);
		Game game2 = new Game(2, "7328", false);
		Game game3 = new Game(3, "2943", true);
		List<Game> allGames = new ArrayList<>(Arrays.asList(game1, game2, game3));
		when(dao.getAllGames()).thenReturn(allGames);
		
		List<Game> sanitisedList = gameService.getAllGamesSanitised();
		
		boolean sanitisedCorrectly = true;
		for (Game game : sanitisedList) {
			if (game.isOngoing() && !game.getAnswer().equals("hidden")) sanitisedCorrectly = false;
			if (!game.isOngoing() && game.getAnswer().equals("hidden")) sanitisedCorrectly = false;
		}
		assertTrue(sanitisedCorrectly);
	}
	
	@Test
	void getGameByIdSanitisedReturnsFinishedGameWithAnswer() {
		Game game = new Game(1, "2538", false);
		when(dao.getGameById(1)).thenReturn(game);
		
		Game returnedGame = gameService.getGameByIdSanitised(1);
		
		assertEquals("2538", returnedGame.getAnswer());
	}
	
	@Test
	void getGameByIdSanitisedReturnsOngoingGameWithAnswerHiddenCorrectly() {
		Game game = new Game(1, "2538", true);
		when(dao.getGameById(1)).thenReturn(game);
		
		Game returnedGame = gameService.getGameByIdSanitised(1);
		
		assertEquals("hidden", returnedGame.getAnswer());
	}
	
	@Test
	void getGameByIdSanitisedThrowsGameNotFoundExceptionIfNoMatchingGame() {
		when(dao.getGameById(2)).thenReturn(null);
		
		assertThrows(GameNotFoundException.class, () -> {
			gameService.getGameByIdSanitised(1);
		});
	}
	
	
	@Test
	void updateGameStatusIfGuessIsCorrectUpdatesStatusCorrectly() {
		gameService.updateGameStatusIfGuessIsCorrect("e:4p:0", 1);
		
		verify(dao).updateGameStatus(1);
	}
	
	@Test
	void updateGameServiceIfGuessIsCorrectDoesNotUpdateStatusIfAnswerIsIncorrect() {	
		gameService.updateGameStatusIfGuessIsCorrect("e:3p:0", 1);
		
		verify(dao, never()).updateGameStatus(1);
	}
}
