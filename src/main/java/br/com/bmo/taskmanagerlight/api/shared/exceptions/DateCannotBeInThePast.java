package br.com.bmo.taskmanagerlight.api.shared.exceptions;

public class DateCannotBeInThePast extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public DateCannotBeInThePast(String msg) {
		super(msg);
	}

}
