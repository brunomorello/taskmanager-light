package br.com.bmo.taskmanagerlight.shared.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

public class TaskmanagerTestUtils {
	
	public static final String DESCRIPTION = "Activity Task";
	public static final String DETAILS = "Activity test";
	public static final Category STUDY_CATEGORY = new Category("Study");

	public static String toJsonStr(final Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
