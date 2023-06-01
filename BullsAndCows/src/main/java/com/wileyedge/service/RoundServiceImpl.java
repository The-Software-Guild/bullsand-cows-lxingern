package com.wileyedge.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wileyedge.dao.GameDao;
import com.wileyedge.dao.RoundDao;
import com.wileyedge.exceptions.GameEndedException;
import com.wileyedge.exceptions.GameNotFoundException;
import com.wileyedge.exceptions.InvalidGuessException;
import com.wileyedge.model.Game;
import com.wileyedge.model.Round;

@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	GameDao gameDao;
	
	@Autowired
	RoundDao roundDao;
	
	public RoundServiceImpl(GameDao gameDao, RoundDao roundDao) {
		this.gameDao = gameDao;
		this.roundDao = roundDao;
	}
	
	@Override
	public void checkGuess(Round round) throws InvalidGuessException, GameNotFoundException, GameEndedException, DataAccessException {
		String guess = round.getGuess();
		validateGuess(guess);
		
		Game game = gameDao.getGameById(round.getGameId());
		if (game == null) throw new GameNotFoundException();

		if (!game.isOngoing()) throw new GameEndedException();

		String answer = game.getAnswer();

		String result = compareAnswers(round.getGuess(), answer);
		round.setResult(result);
		createNewRound(round);
	}

	@Override
	public List<Round> getRoundsForGameSorted(int gameId) {
		Game game = gameDao.getGameById(gameId);
		if (game == null) throw new GameNotFoundException();
		
		List<Round> rounds = roundDao.getRoundsForGame(gameId);
		rounds.sort((r1, r2) -> r1.getTimestamp().compareTo(r2.getTimestamp()));
		return rounds;
	}
	
	private boolean createNewRound(Round round) throws DataAccessException {
		round.setNewTimestamp();

		int nextId;
		List<Round> allRoundsForGame = roundDao.getRoundsForGame(round.getGameId());
		if (allRoundsForGame.size() == 0) {
			nextId = 1;
		} else {
			Round roundWithHighestId = allRoundsForGame.stream().max((r1, r2) -> r1.getRoundId() - r2.getRoundId()).get();
			nextId = roundWithHighestId.getRoundId() + 1;
		}
		round.setRoundId(nextId);
		
		roundDao.saveRound(round);

		return true;
	}
	
	private void validateGuess(String guess) throws InvalidGuessException {
		int length = guess.length();
		if (length != 4) throw new InvalidGuessException("Guess must be a 4-digit number.");
		
		for (int i = 0; i < 4; i++) {
			if (guess.charAt(i) > 57 || guess.charAt(i) < 48) throw new InvalidGuessException("Guess must be composed of numbers.");
		}
		
		Set<String> uniqueDigits = new HashSet<>(Arrays.asList(guess.split("")));
		if (uniqueDigits.size() != 4) throw new InvalidGuessException("Each digit in the guess must be unique.");
	}
	
	private String compareAnswers(String guess, String answer) {
		int exactMatches = 0;
		int partialMatches = 0;
		
		for (int i = 0; i < 4; i++) {
			char digit = guess.charAt(i);
			if (answer.charAt(i) == digit) exactMatches++;
			for (int j = 0; j < 4; j++) {
				if (j != i) {
					if (answer.charAt(j) == digit) partialMatches++;
				}
			}
		}
		return "e:" + exactMatches + "p:" + partialMatches;
	}

}
