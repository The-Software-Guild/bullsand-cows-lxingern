package com.wileyedge.exceptions;

public class GameNotFoundException extends RuntimeException {

	public GameNotFoundException() {
		super("Game with that ID not found.");
	}
	
}
