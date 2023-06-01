package com.wileyedge.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wileyedge.model.Game;

@SpringBootTest
class GameDaoJdbcImplTest {

	@Autowired
	GameDao gameDao;
	
	@Autowired
	RoundDao roundDao;
	
	@BeforeEach
	void setUp() {
		roundDao.deleteAllRounds();
		gameDao.deleteAllGames();
	}
	
	@Test
	void getAllGamesReturnsAllGames() {
		Game game1 = new Game(1, "2538", false);
		Game game2 = new Game(2, "7328", false);
		Game game3 = new Game(3, "2943", true);
		gameDao.saveGame(game1);
		gameDao.saveGame(game2);
		gameDao.saveGame(game3);
		
		List<Game> allGames = gameDao.getAllGames();
		
		assertEquals(3, allGames.size());
		assertTrue(allGames.contains(game1));
		assertTrue(allGames.contains(game2));
		assertTrue(allGames.contains(game3));
	}
	
	@Test
	void getAllGamesReturnsEmptyListIfNoGames() {
		List<Game> allGames = gameDao.getAllGames();
		
		assertEquals(0, allGames.size());
	}
	
	@Test
	void saveGameIsSuccessful() {
		Game game = new Game(3, "2943", true);
		List<Game> gamesBefore = gameDao.getAllGames();
		int noOfGamesBefore = gamesBefore.size();
		assertFalse(gamesBefore.contains(game));
		
		gameDao.saveGame(game);
		
		List<Game> gamesAfter = gameDao.getAllGames();
		int noOfGamesAfter = gamesAfter.size();
		assertEquals(noOfGamesBefore + 1, noOfGamesAfter);
		assertTrue(gamesAfter.contains(game));
	}
	
	@Test
	void getGameByIdReturnsMatchingGame() {
		Game game1 = new Game(1, "2538", false);
		Game game2 = new Game(2, "7328", false);
		gameDao.saveGame(game1);
		gameDao.saveGame(game2);
		
		Game game = gameDao.getGameById(2);
		
		assertEquals(game2, game);
	}
	
	@Test
	void getGameByIdReturnsNullIfNoMatchingGame() {
		Game game1 = new Game(1, "2538", false);
		Game game2 = new Game(2, "7328", false);
		gameDao.saveGame(game1);
		gameDao.saveGame(game2);
		
		Game game = gameDao.getGameById(3);
		
		assertNull(game);
	}
	
	@Test
	void updateGameStatusIsSuccessful() {
		Game game = new Game(3, "2943", true);
		gameDao.saveGame(game);
		
		gameDao.updateGameStatus(3);
		
		Game updatedGame = gameDao.getGameById(3);
		assertFalse(updatedGame.isOngoing());
	}
	
}
