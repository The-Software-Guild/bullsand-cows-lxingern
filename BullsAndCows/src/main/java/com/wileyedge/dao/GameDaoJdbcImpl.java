package com.wileyedge.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wileyedge.model.Game;
import com.wileyedge.service.GameRowMapper;

@Repository
public class GameDaoJdbcImpl implements GameDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Game> getAllGames() throws DataAccessException {
		return jdbcTemplate.query("SELECT * FROM games", new GameRowMapper());
	}

	@Override
	public void saveGame(Game game) throws DataAccessException {
		jdbcTemplate.update("INSERT INTO games (game_id, answer, ongoing) VALUES (?, ?, ?)", game.getGameId(), game.getAnswer(), game.isOngoing());	
	}

	@Override
	public Game getGameById(int gameId) throws DataAccessException {
		List<Game> game = jdbcTemplate.query("SELECT * FROM games WHERE game_id = ?", new GameRowMapper(), gameId);
		if (game.size() == 0) return null;
		
		return game.get(0);
	}

	@Override
	public void updateGameStatus(int gameId) throws DataAccessException {
		jdbcTemplate.update("UPDATE games SET ongoing = 0 WHERE game_id = ?", gameId);
	}
	
	public void deleteAllGames() {
		jdbcTemplate.update("DELETE FROM games");
	}

	public void deleteAllRounds() {
		jdbcTemplate.update("DELETE FROM rounds");
	}
}
