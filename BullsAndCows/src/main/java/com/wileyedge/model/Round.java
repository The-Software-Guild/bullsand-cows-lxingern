package com.wileyedge.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {
	private int roundId;
	private int gameId;
	private String guess;
	private LocalDateTime timestamp;
	private String result;

	public Round() {	
	}
	
 	public Round(int roundId, int gameId, String guess, LocalDateTime timestamp, String result) {
		this.roundId = roundId;
		this.gameId = gameId;
		this.guess = guess;
		this.timestamp = timestamp;
		this.result = result;
	}

	public int getRoundId() {
		return roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public int getGameId() {
		return gameId;
	}

	public String getGuess() {
		return guess;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setNewTimestamp() {
		this.timestamp = LocalDateTime.now();
	}

	public String getResult() {
		return result;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, guess, result, roundId, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Round other = (Round) obj;
		return gameId == other.gameId && Objects.equals(guess, other.guess) && Objects.equals(result, other.result)
				&& roundId == other.roundId && Objects.equals(timestamp, other.timestamp);
	}

}
