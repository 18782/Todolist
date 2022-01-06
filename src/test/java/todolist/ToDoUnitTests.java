package todolist;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
import todolist.entity.TodoTask;
import todolist.repository.TodoTaskRepository;
import todolist.service.TodoTaskService;

@SpringBootTest
class ToDoUnitTests {
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class TodoServiceTest {

		@MockBean
		TodoTaskRepository todoTaskRepository;

		@Autowired
		private TodoTaskService todoTaskService;

		@Before
		public void setUp() {
			Mockito.when(todoTaskRepository.findAll())
					.thenReturn(IntStream.range(0, 10)
							.mapToObj(i -> new TodoTask((long) i, "work" + i, "2021011" + i, "2021011" + i, 0))
							.collect(Collectors.toList()));

		}

		@Test
		public void testCount() {
			Assert.assertEquals(10, todoTaskService.getAllTodoTasks(0, 5, "id", "asc").getList().size());
		}

	}

}
