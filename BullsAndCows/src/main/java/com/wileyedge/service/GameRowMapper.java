package com.wileyedge.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wileyedge.model.Game;

public class GameRowMapper implements RowMapper<Game> {

	@Override
	public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
		int gameId = rs.getInt(1);
		String answer = rs.getString(2);
		boolean ongoing = rs.getBoolean(3);
		
		return new Game(gameId, answer, ongoing);
	}

}
