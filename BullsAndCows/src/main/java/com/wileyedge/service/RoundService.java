package com.wileyedge.service;

import java.util.List;

import com.wileyedge.model.Round;

public interface RoundService {

	public void checkGuess(Round round);
	public List<Round> getRoundsForGameSorted(int gameId);
	
}
