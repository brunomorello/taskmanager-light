package br.com.bmo.taskmanagerlight.api.shared.utils;

public interface Mapper<Source, Target> {
	Target map(Source source);
}
