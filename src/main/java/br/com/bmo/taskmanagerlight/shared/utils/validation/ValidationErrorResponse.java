package br.com.bmo.taskmanagerlight.shared.utils.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

	private List<Violation> violations = new ArrayList<>();

	public List<Violation> getViolations() {
		return violations;
	}
	
	public void addViolation(Violation v) {
		this.violations.add(v);
	}
	
}
