package br.com.bmo.taskmanagerlight.api.shared.domain;

import java.util.Arrays;
import java.util.List;

public enum Status {

	BACKLOG {
		@Override
		public List<Status> nextStatus() {
			return Arrays.asList(DOING, DONE);
		}

		@Override
		public List<Status> previousStatus() {
			return null;
		}

		@Override
		public boolean isValidTransitionTo(Status status) {
			return nextStatus().contains(status);
		}

	}, DOING {
		@Override
		public List<Status> nextStatus() {
			return Arrays.asList(PENDING, DONE);
		}

		@Override
		public List<Status> previousStatus() {
			return Arrays.asList(BACKLOG);
		}

		@Override
		public boolean isValidTransitionTo(Status status) {
			if (previousStatus().contains(status) || nextStatus().contains(status))
				return true;
			return false;
		}

	}, PENDING {
		@Override
		public List<Status> nextStatus() {
			return Arrays.asList(DOING, DONE);
		}

		@Override
		public List<Status> previousStatus() {
			return Arrays.asList(BACKLOG);
		}

		@Override
		public boolean isValidTransitionTo(Status status) {
			if (previousStatus().contains(status) || nextStatus().contains(status))
				return true;
			return false;
		}

	}, DONE {
		@Override
		public List<Status> nextStatus() {
			return null;
		}

		@Override
		public List<Status> previousStatus() {
			return Arrays.asList(DOING, BACKLOG);
		}

		@Override
		public boolean isValidTransitionTo(Status status) {
			// TODO Auto-generated method stub
			return false;
		}

	};

	public abstract List<Status> nextStatus(); 

	public abstract List<Status> previousStatus();
	
	public abstract boolean isValidTransitionTo(Status status);
	
}
