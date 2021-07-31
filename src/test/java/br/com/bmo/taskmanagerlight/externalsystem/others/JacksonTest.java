package br.com.bmo.taskmanagerlight.externalsystem.others;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

class JacksonTest {

	@Test
	void testJsonNode() throws JsonMappingException, JsonProcessingException {
		
		String rawJson = "{}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(rawJson);
		((ObjectNode) jsonNode).put("attr1", "1");
		
		List<Integer> list = new ArrayList<>();
		Map<String, List<Integer>> listAttr = new HashMap<>();
		listAttr.put("list", list);
		
		System.out.println(jsonNode);
		fail("Not yet implemented");
	}

}
