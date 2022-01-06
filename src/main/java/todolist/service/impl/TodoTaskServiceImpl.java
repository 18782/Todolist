package todolist.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import todolist.entity.TodoTask;
import todolist.exception.ResourceNotFoundException;
import todolist.repository.TodoTaskRepository;
import todolist.service.TodoTaskService;
import todolist.todotask.TodoTaskDto;
import todolist.todotask.TodoTaskResponse;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {

    private TodoTaskRepository todoTaskRepository;

    private ModelMapper mapper;

    public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository, ModelMapper mapper) {
          this.todoTaskRepository = todoTaskRepository;
          this.mapper = mapper;
    }

    @Override
    public TodoTaskDto createTodoTask(TodoTaskDto postDto) {

        // convert DTO to entity
        TodoTask post = mapToEntity(postDto);
        TodoTask newTodoTask = todoTaskRepository.save(post);

        // convert entity to DTO
        TodoTaskDto postResponse = mapToDTO(newTodoTask);
        return postResponse;
    }

    @Override
    public TodoTaskResponse getAllTodoTasks(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<TodoTask> todoTasks = todoTaskRepository.findAll(pageable);

        // get content for page object
        List<TodoTask> listOfTodoTasks = todoTasks.getContent();

        List<TodoTaskDto> list= listOfTodoTasks.stream().map(todoTask -> mapToDTO(todoTask)).collect(Collectors.toList());

        TodoTaskResponse postResponse = new TodoTaskResponse();
        postResponse.setList(list);
        postResponse.setPageNo(todoTasks.getNumber());
        postResponse.setPageSize(todoTasks.getSize());
        postResponse.setTotalElements(todoTasks.getTotalElements());
        postResponse.setTotalPages(todoTasks.getTotalPages());
        postResponse.setLast(todoTasks.isLast());

        return postResponse;
    }

    @Override
    public TodoTaskDto getTodoTaskById(long id) {
        TodoTask todoTask = todoTaskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));
        return mapToDTO(todoTask);
    }

    @Override
    public TodoTaskDto updateTodoTask(TodoTaskDto todoTaskDto, long id) {
        // get post by id from the database
        TodoTask todoTask = todoTaskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));

        todoTask.setWorkName(todoTaskDto.getWorkName());
        todoTask.setStartDate(todoTaskDto.getStartDate());
        todoTask.setEndDate(todoTaskDto.getEndDate());
        todoTask.setStatus(todoTaskDto.getStatus());

        TodoTask updatedTodoTask = todoTaskRepository.save(todoTask);
        return mapToDTO(updatedTodoTask);
    }

    @Override
    public void deleteTodoTaskById(long id) {
        // get post by id from the database
        TodoTask post = todoTaskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));
        todoTaskRepository.delete(post);
    }

    // convert Entity into DTO
    private TodoTaskDto mapToDTO(TodoTask todoTask){
        TodoTaskDto todoTaskDto = mapper.map(todoTask, TodoTaskDto.class);
        return todoTaskDto;
    }

    // convert DTO to entity
    private TodoTask mapToEntity(TodoTaskDto todoTaskDto){
        TodoTask todoTask = mapper.map(todoTaskDto, TodoTask.class);
        return todoTask;
    }
}
