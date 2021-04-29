package br.com.bmo.taskmanagerlight.shared.utils.validation;

public class Violation {
	
	private final String message;
	private final String attributeName;
	
	public Violation(String message, String attributeName) {
		this.message = message;
		this.attributeName = attributeName;
	}

	public String getMessage() {
		return message;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
}
