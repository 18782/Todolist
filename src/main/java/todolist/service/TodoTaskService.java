package todolist.service;

import todolist.todotask.TodoTaskDto;
import todolist.todotask.TodoTaskResponse;

public interface TodoTaskService {
    TodoTaskDto createTodoTask(TodoTaskDto TodoTaskDto);

    TodoTaskResponse getAllTodoTasks(int pageNo, int pageSize, String sortBy, String sortDir);

    TodoTaskDto getTodoTaskById(long id);

    TodoTaskDto updateTodoTask(TodoTaskDto TodoTaskDto, long id);

    void deleteTodoTaskById(long id);
}
