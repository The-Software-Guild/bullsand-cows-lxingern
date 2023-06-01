package com.wileyedge.dao;

import java.util.List;

import com.wileyedge.model.Round;

public interface RoundDao {

	void saveRound(Round round);
	List<Round> getRoundsForGame(int gameId);
	void deleteAllRounds();
	
}
