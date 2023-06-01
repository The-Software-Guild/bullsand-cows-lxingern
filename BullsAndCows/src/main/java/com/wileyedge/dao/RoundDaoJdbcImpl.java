package com.wileyedge.dao;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wileyedge.model.Round;
import com.wileyedge.service.RoundRowMapper;

@Repository
public class RoundDaoJdbcImpl implements RoundDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveRound(Round round) throws DataAccessException {
		jdbcTemplate.update("INSERT INTO rounds (round_id, game_id, guess, timestamp, result) VALUES (?, ?, ?, ?, ?)", 
				round.getRoundId(), 
				round.getGameId(), 
				round.getGuess(),
				round.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
				round.getResult()
		);
	}

	@Override
	public List<Round> getRoundsForGame(int gameId) throws DataAccessException {
		return jdbcTemplate.query("SELECT * FROM rounds WHERE game_id = ?", new RoundRowMapper(), gameId);
	}

	@Override
	public void deleteAllRounds() throws DataAccessException {
		jdbcTemplate.update("DELETE FROM rounds");
	}

}
