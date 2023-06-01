package com.wileyedge.exceptions;

public class GameEndedException extends RuntimeException {

	public GameEndedException() {
		super("Game with that ID has already ended.");
	}
	
}
