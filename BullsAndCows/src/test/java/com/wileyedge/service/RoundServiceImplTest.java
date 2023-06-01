package com.wileyedge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wileyedge.dao.GameDao;
import com.wileyedge.dao.RoundDao;
import com.wileyedge.exceptions.GameEndedException;
import com.wileyedge.exceptions.GameNotFoundException;
import com.wileyedge.exceptions.InvalidGuessException;
import com.wileyedge.model.Game;
import com.wileyedge.model.Round;

class RoundServiceImplTest {

	GameDao gameDao;
	RoundDao roundDao;
	RoundService roundService;
	
	@BeforeEach
	void setUp() throws Exception {
		gameDao = mock(GameDao.class);
		roundDao = mock(RoundDao.class);
		roundService = new RoundServiceImpl(gameDao, roundDao);
	}
	
	@Test
	void checkGuessThrowsInvalidGuessExceptionIfGuessIsNotOfLength4() {
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("37942");
		
		assertThrows(InvalidGuessException.class, () -> roundService.checkGuess(round));
	}

	@Test
	void checkGuessThrowsInvalidGuessExceptionIfGuessContainsNonNumericCharacters() {
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("asdd");
		
		assertThrows(InvalidGuessException.class, () -> roundService.checkGuess(round));
	}
	
	@Test
	void checkGuessThrowsInvalidGuessExceptionIfGuessContainsNonUniqueDigits() {
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("2428");
		
		assertThrows(InvalidGuessException.class, () -> roundService.checkGuess(round));
	}
	
	@Test
	void checkGuessThrowsGameNotFoundExceptionIfNoGameMatchingId() {
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("2468");
		when(gameDao.getGameById(round.getGameId())).thenThrow(GameNotFoundException.class);
		
		assertThrows(GameNotFoundException.class, () -> roundService.checkGuess(round));
	}
	
	@Test
	void checkGuessThrowsGameEndedExceptionIfGameMatchingIdHasAlreadyEnded() {
		Game game = new Game(1, "2538", false);
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("2468");
		when(gameDao.getGameById(round.getGameId())).thenReturn(game);
		
		assertThrows(GameEndedException.class, () -> roundService.checkGuess(round));
	}
	
	@Test
	void checkGuessCorrectlyUpdatesResult() {
		Game game = new Game(1, "2548", true);
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("2468");
		when(gameDao.getGameById(round.getGameId())).thenReturn(game);
		
		roundService.checkGuess(round);
		
		assertEquals("e:2p:1", round.getResult());
	}
	
	@Test
	void checkGuessCorrectlyUpdatesRoundIdWithUniqueId() {
		Game game = new Game(1, "2548", true);
		Round round1 = new Round(1, 1, "4268", LocalDateTime.parse("2023-05-31 17:02:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:1p:2");
		Round round2 = new Round();
		round2.setGameId(1);
		round2.setGuess("2468");
		List<Round> rounds = new ArrayList<>();
		rounds.add(round1);
		when(gameDao.getGameById(game.getGameId())).thenReturn(game);
		when(roundDao.getRoundsForGame(game.getGameId())).thenReturn(rounds);
		
		roundService.checkGuess(round2);
		
		assertEquals(2, round2.getRoundId());
	}
	
	@Test
	void checkGuessCorrectlyUpdatesRoundIdIfNoRoundsExisting() {
		Game game = new Game(1, "2548", true);
		Round round = new Round();
		round.setGameId(1);
		round.setGuess("2468");
		List<Round> rounds = new ArrayList<>();
		rounds.add(round);
		when(gameDao.getGameById(game.getGameId())).thenReturn(game);
		when(roundDao.getRoundsForGame(game.getGameId())).thenReturn(rounds);
		
		roundService.checkGuess(round);
		
		assertEquals(1, round.getRoundId());
	}
	
	@Test
	void getRoundsForGameSortedReturnsRoundsSortedCorrectly() {
		Game game = new Game(1, "2538", false);
		Round round1 = new Round(1, 1, "4268", LocalDateTime.parse("2023-05-31 17:02:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:1p:1");
		Round round2 = new Round(2, 1, "2738", LocalDateTime.parse("2023-05-31 17:04:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:3p:0");
		Round round3 = new Round(3, 1, "2538", LocalDateTime.parse("2023-05-31 17:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e:4p:0");
		List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2, round3));
		when(gameDao.getGameById(1)).thenReturn(game);
		when(roundDao.getRoundsForGame(1)).thenReturn(rounds);
		
		List<Round> sortedRounds = roundService.getRoundsForGameSorted(1);
		
		assertEquals(round1, sortedRounds.get(0));
		assertEquals(round3, sortedRounds.get(2));
	}
	
	@Test
	void getRoundsForGameSortedReturnsEmptyListIfNoRounds() {
		Game game = new Game(1, "2538", true);
		when(gameDao.getGameById(1)).thenReturn(game);
		when(roundDao.getRoundsForGame(1)).thenReturn(new ArrayList<>());
		
		List<Round> rounds = roundService.getRoundsForGameSorted(1);
		
		assertEquals(0, rounds.size());
	}
	
	@Test
	void getRoundsForGameSortedThrowsGameNotFoundExceptionIfNoMatchingGame() {
		when(gameDao.getGameById(2)).thenReturn(null);
		
		assertThrows(GameNotFoundException.class, () -> {
			roundService.getRoundsForGameSorted(2);
		});
	}
}
