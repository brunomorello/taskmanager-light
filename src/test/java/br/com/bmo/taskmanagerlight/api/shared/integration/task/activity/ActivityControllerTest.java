package br.com.bmo.taskmanagerlight.api.shared.integration.task.activity;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.bmo.taskmanagerlight.api.task.activity.ActivityDTOInput;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Category;
import br.com.bmo.taskmanagerlight.shared.domain.task.Status;
import br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils;
import br.com.bmo.taskmanagerlight.task.category.CategoryForm;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class ActivityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TestEntityManager em;
	
	private Activity TODO;
	
	private Category ACTIVITY_CATEGORY;
	
	private static String BASE_URI = "/api/task/activity/";
	
	@BeforeEach
	void setup() {
		ACTIVITY_CATEGORY = em.find(Category.class, Long.valueOf(1L));
		TODO = new Activity("Fix the table", "", ACTIVITY_CATEGORY);
		TODO.setDueDate(LocalDateTime.parse("2021-05-20T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
		em.persist(TODO);
	}
	
	@Test
	void shouldReturn200ToFindAnActivityByID() throws Exception {
		mockMvc.perform(get(BASE_URI + TODO.getId()))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", equalTo("Fix the table")))
			.andExpect(jsonPath("$.details", equalTo("")))
			.andExpect(jsonPath("$.dueDate", equalTo("2021-05-20T10:00:00")))
			.andExpect(jsonPath("$.status", equalTo("BACKLOG")));
	}
	
	@Test
	void shouldReturn201ToCreateAnActivity() throws Exception {
		CategoryForm categoryForm = new CategoryForm(ACTIVITY_CATEGORY.getName(), ACTIVITY_CATEGORY.getId().toString());
		ActivityDTOInput activity = new ActivityDTOInput("Title", "Details", "2021-05-10T09:00:00", categoryForm);
		String payload = TaskmanagerTestUtils.toJsonStr(activity);
		
		mockMvc.perform(
				post(BASE_URI).content(payload).contentType(MediaType.APPLICATION_JSON))
			.andDo(log())
			.andExpect(status().isCreated());
	}
	
	@Test
	void shouldReturn200ToUpdateAnActivity() throws Exception {
		CategoryForm categoryForm = new CategoryForm(ACTIVITY_CATEGORY.getName(), ACTIVITY_CATEGORY.getId().toString());
		ActivityDTOInput activity = new ActivityDTOInput(TODO.getId().toString(), "Fix kitchen table", "asap", Status.DOING.toString(), "2021-05-10T20:00:00", categoryForm);
		
		String payload = TaskmanagerTestUtils.toJsonStr(activity);
		
		mockMvc.perform(
				put(BASE_URI + TODO.getId())
					.content(payload)
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", equalTo(activity.getTitle())))
			.andExpect(jsonPath("$.details", equalTo(activity.getDetails())))
			.andExpect(jsonPath("$.status", equalTo(Status.DOING.toString())));
	}
	
	@Test
	void shouldReturn200AndActivityList() throws Exception {
		mockMvc.perform(get(BASE_URI))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].title", equalTo(TODO.getTitle())));
	}
	
}
