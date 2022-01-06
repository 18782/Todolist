package todolist;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import todolist.controller.TodoTaskController;
import todolist.service.impl.TodoTaskServiceImpl;
import todolist.todotask.TodoTaskDto;
import todolist.utils.AppConstants;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoTaskController.class)
public class TodoTaskRestControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TodoTaskServiceImpl todoTaskService;

	@Test
	public void testFindAll() throws Exception {
		List<TodoTaskDto> allTodoTaskDtos = IntStream.range(0, 10)
				.mapToObj(i -> new TodoTaskDto((long) i, "work" + i, "2021011" + i, "2021011" + i, "Planning"))
				.collect(Collectors.toList());

		given(todoTaskService.getAllTodoTasks(Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER)
				, Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE)
				, AppConstants.DEFAULT_SORT_BY
				, AppConstants.DEFAULT_SORT_DIRECTION).getList()).willReturn(allTodoTaskDtos);

		mvc.perform(get("/api/todolist").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[0].id", is(0)))
				.andExpect(jsonPath("$[0].title", is("title-0")))
				.andExpect(jsonPath("$[0].detail", is("detail-0")));
	}
}