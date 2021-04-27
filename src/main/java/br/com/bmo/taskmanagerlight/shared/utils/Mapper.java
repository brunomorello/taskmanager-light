package br.com.bmo.taskmanagerlight.shared.utils;

public interface Mapper<Source, Target> {
	Target map(Source source);
}
