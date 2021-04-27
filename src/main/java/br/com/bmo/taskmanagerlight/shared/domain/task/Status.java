package br.com.bmo.taskmanagerlight.shared.domain.task;

import java.util.Arrays;
import java.util.List;

import br.com.bmo.taskmanagerlight.shared.exceptions.InvalidStatusTransition;

public enum Status {

	BACKLOG {
		@Override
		public List<Status> allowedStatusTransition() {
			return Arrays.asList(DOING, DONE);
		}

	}, DOING {
		@Override
		public List<Status> allowedStatusTransition() {
			return Arrays.asList(BACKLOG, PENDING, DONE);
		}

	}, PENDING {
		@Override
		public List<Status> allowedStatusTransition() {
			return Arrays.asList(BACKLOG, DOING, DONE);
		}
	}, DONE {
		@Override
		public List<Status> allowedStatusTransition() {
			return Arrays.asList(BACKLOG, DOING);
		}
	};

	public abstract List<Status> allowedStatusTransition(); 

	public Status isValidTransitionTo(Status status) throws InvalidStatusTransition {
		if (!allowedStatusTransition().contains(status))
			throw new InvalidStatusTransition("Cannot set status to ".concat(status.toString()));
		return status;
	}
	
}
