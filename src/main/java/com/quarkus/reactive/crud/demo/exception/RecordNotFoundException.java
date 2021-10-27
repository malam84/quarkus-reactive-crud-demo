package com.quarkus.reactive.crud.demo.exception;

public class RecordNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String msg) {
		super(msg);
	}
}
