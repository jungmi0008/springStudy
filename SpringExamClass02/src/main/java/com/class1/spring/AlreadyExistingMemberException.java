package com.class1.spring;

public class AlreadyExistingMemberException extends RuntimeException {
	public AlreadyExistingMemberException(String message) {
		super(message);
	}
}
