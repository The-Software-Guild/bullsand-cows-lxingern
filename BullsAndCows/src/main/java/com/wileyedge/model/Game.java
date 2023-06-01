package com.wileyedge.model;

import java.util.Objects;

public class Game {
	
	private int gameId;
	private String answer;
	private boolean ongoing;
	
	public Game(int gameId, String answer) {
		this.gameId = gameId;
		this.answer = answer;
		this.ongoing = true;
	}

	public Game(int gameId, String answer, boolean ongoing) {
		this.gameId = gameId;
		this.answer = answer;
		this.ongoing = ongoing;
	}
	
	public int getGameId() {
		return gameId;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, gameId, ongoing);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(answer, other.answer) && gameId == other.gameId && ongoing == other.ongoing;
	}

}
