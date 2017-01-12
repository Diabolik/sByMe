/*
 * *******************************************************************************
 *   Copyright 2017 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.exception;

public enum EventsErrorCode {

	// Users
	USER_NOT_FOUND(001, "USER_NOT_FOUND", "The user is not found."), 
	USER_FOUND_EMAIL(002, "USER_FOUND_EMAIL", "This email is already used. Please Verify"), 
	USER_FOUND_NICKNAME(003, "USER_FOUND_NICKNAME", "This nickname is already used. Please Verify"),
	
	LOCATION_USERS_NOT_MATCH(004, "LOCATION_USERS_NOT_MATCH", "The request could not be executed. There is a issue with the user"),
	LOCATION_USERS_NOT_EXISTS(005, "LOCATION_USERS_NOT_EXISTS", "Could not save the Location because the User could not be found");

	private int errorType;
	private String label;
	private String description;

	private EventsErrorCode(int errorType, String label, String description) {
		this.errorType = errorType;
		this.label = label;
		this.description = description;
	}

	public int getType() {
		return errorType;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
