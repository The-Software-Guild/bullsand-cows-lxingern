package com.wileyedge.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.wileyedge.exceptions.GameEndedException;
import com.wileyedge.exceptions.GameNotFoundException;
import com.wileyedge.exceptions.InvalidGuessException;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

	@ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Map<String, String>> handleJdbcException(DataAccessException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Could not connect to database.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler({InvalidGuessException.class, GameNotFoundException.class, GameEndedException.class})
    public final ResponseEntity<Map<String, String>> handleInvalidInputException(Exception ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
