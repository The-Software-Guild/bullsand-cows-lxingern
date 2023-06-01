package com.wileyedge.dao;
import java.util.List;

import com.wileyedge.model.Game;

public interface GameDao {

	List<Game> getAllGames();
	void saveGame(Game game);
	Game getGameById(int gameId);
	void updateGameStatus(int gameId);
	void deleteAllGames();
	
}
