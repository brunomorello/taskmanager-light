package br.com.bmo.taskmanagerlight.api.shared.exceptions;

public class InvalidStatusTransition extends IllegalAccessException {

	private static final long serialVersionUID = 1L;

	public InvalidStatusTransition(String msg) {
		super(msg);
	}
}
