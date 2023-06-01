package com.wileyedge.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import com.wileyedge.model.Round;

public class RoundRowMapper implements RowMapper<Round> {

	@Override
	public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
		int roundId = rs.getInt(1);
		int gameId = rs.getInt(2);
		String guess = rs.getString(3);
		LocalDateTime timestamp = LocalDateTime.parse(rs.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String result = rs.getString(5);
		
		return new Round(roundId, gameId, guess, timestamp, result);
	}

}