package com.wileyedge.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wileyedge.model.Game;
import com.wileyedge.model.Round;

@SpringBootTest
class RoundDaoJdbcImplTest {

	@Autowired
	GameDao gameDao;
	
	@Autowired
	RoundDao roundDao;
	
	@BeforeEach
	void setUp() throws Exception {
		roundDao.deleteAllRounds();
		gameDao.deleteAllGames();
	}

	@Test
	void saveRoundIsSuccessful() {
		Game game = new Game(3, "2943", true);
		gameDao.saveGame(game);
		Round round = new Round(1, 3, "4268", LocalDateTime.parse("2023-05-31 17:02:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:0p:2");
		List<Round> roundsBefore = roundDao.getRoundsForGame(3);
		int noOfRoundsBefore = roundsBefore.size();
		assertFalse(roundsBefore.contains(round));
		
		roundDao.saveRound(round);
		
		List<Round> roundsAfter = roundDao.getRoundsForGame(3);
		int noOfRoundsAfter = roundsAfter.size();
		assertEquals(noOfRoundsBefore + 1, noOfRoundsAfter);
		assertTrue(roundsAfter.contains(round));
	}

	
	@Test
	void getRoundsForGameReturnsRounds() {
		Game game1 = new Game(1, "2538", false);
		gameDao.saveGame(game1);
		Round round1 = new Round(1, 1, "4268", LocalDateTime.parse("2023-05-31 17:02:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:0p:2");
		roundDao.saveRound(round1);
		Round round2 = new Round(2, 1, "2538", LocalDateTime.parse("2023-05-31 17:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:4p:0");
		roundDao.saveRound(round2);
		Game game2 = new Game(2, "7328", true);
		gameDao.saveGame(game2);
		Round round3 = new Round(1, 2, "4268", LocalDateTime.parse("2023-05-31 17:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:0p:2");
		roundDao.saveRound(round3);
		
		List<Round> rounds = roundDao.getRoundsForGame(1);
		
		assertEquals(2, rounds.size());
		assertTrue(rounds.contains(round1));
		assertTrue(rounds.contains(round2));
		assertFalse(rounds.contains(round3));
	}
	
	@Test
	void getRoundsForGameReturnsEmptyListIfNoRounds() {
		Game game2 = new Game(2, "7328", true);
		gameDao.saveGame(game2);
		
		List<Round> rounds = roundDao.getRoundsForGame(2);
		
		assertEquals(0, rounds.size());
	}
}
