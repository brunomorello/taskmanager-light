package br.com.bmo.taskmanagerlight.shared.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskmanagerTestUtils {

	public static String toJsonStr(final Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
