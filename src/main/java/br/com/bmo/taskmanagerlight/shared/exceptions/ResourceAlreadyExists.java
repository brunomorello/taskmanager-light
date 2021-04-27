package br.com.bmo.taskmanagerlight.shared.exceptions;

public class ResourceAlreadyExists extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExists(String message) {
		super(message);
	}
}
