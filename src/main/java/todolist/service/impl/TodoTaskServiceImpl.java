package todolist.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import todolist.entity.TodoTask;
import todolist.exception.APIException;
import todolist.exception.ResourceNotFoundException;
import todolist.repository.TodoTaskRepository;
import todolist.service.TodoTaskService;
import todolist.todotask.TodoTaskDto;
import todolist.todotask.TodoTaskResponse;
import todolist.utils.AppConstants;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {
	@Autowired
	private TodoTaskRepository todoTaskRepository;

	public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository) {
		this.todoTaskRepository = todoTaskRepository;
	}

	@Override
	public TodoTaskDto createTodoTask(TodoTaskDto todoTaskDto) {

		// convert DTO to entity
		TodoTask todoTask = mapToEntity(todoTaskDto);
		TodoTask newTodoTask = todoTaskRepository.save(todoTask);

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

		// get content for TodoTask object
		List<TodoTask> listOfTodoTasks = todoTasks.getContent();

		List<TodoTaskDto> list = listOfTodoTasks.stream().map(todoTask -> mapToDTO(todoTask))
				.collect(Collectors.toList());

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
		TodoTask todoTask = todoTaskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));
		return mapToDTO(todoTask);
	}

	@Override
	public TodoTaskDto updateTodoTask(TodoTaskDto todoTaskDto, long id) {
		// get post by id from the database
		TodoTask todoTask = todoTaskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));

		todoTask.setWorkName(todoTaskDto.getWorkName());
		todoTask.setStartDate(todoTaskDto.getStartDate());
		todoTask.setEndDate(todoTaskDto.getEndDate());
		// status
		String statusName = StringUtils.capitalize(todoTaskDto.getStatus());
		int status;
		switch (statusName) {
		case AppConstants.TODOTASK_STATUS_NAME_PLANNING:
			status = AppConstants.TODOTASK_STATUS_PLANNING;
			break;
		case AppConstants.TODOTASK_STATUS_NAME_COMPLETE:
			status = AppConstants.TODOTASK_STATUS_COMPLETE;
			break;
		case AppConstants.TODOTASK_STATUS_NAME_DOING:
			status = AppConstants.TODOTASK_STATUS_DOING;
			break;
		default:
			throw new APIException(HttpStatus.BAD_REQUEST, "Wrong status name");
		}
		todoTask.setStatus(status);

		TodoTask updatedTodoTask = todoTaskRepository.save(todoTask);
		return mapToDTO(updatedTodoTask);
	}

	@Override
	public void deleteTodoTaskById(long id) {
		// get post by id from the database
		TodoTask post = todoTaskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TodoTask", "id", id));
		todoTaskRepository.delete(post);
	}

	// convert Entity into DTO
	private TodoTaskDto mapToDTO(TodoTask todoTask) {
		TodoTaskDto todoTaskDto = new TodoTaskDto();
		todoTaskDto.setId(todoTask.getId());
		todoTaskDto.setWorkName(todoTask.getWorkName());
		todoTaskDto.setStartDate(todoTask.getStartDate());
		todoTaskDto.setEndDate(todoTask.getEndDate());
		// status
		int status = todoTask.getStatus();
		String statusName;
		switch (status) {
		case AppConstants.TODOTASK_STATUS_PLANNING:
			statusName = AppConstants.TODOTASK_STATUS_NAME_PLANNING;
			break;
		case AppConstants.TODOTASK_STATUS_COMPLETE:
			statusName = AppConstants.TODOTASK_STATUS_NAME_COMPLETE;
			break;
		case AppConstants.TODOTASK_STATUS_DOING:
			statusName = AppConstants.TODOTASK_STATUS_NAME_DOING;
			break;
		default:
			throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "Bad data");
		}
		todoTaskDto.setStatus(statusName);
		return todoTaskDto;
	}

	// convert DTO to entity
	private TodoTask mapToEntity(TodoTaskDto todoTaskDto) {
		TodoTask todoTask = new TodoTask();
		todoTask.setId(todoTaskDto.getId());
		todoTask.setWorkName(todoTaskDto.getWorkName());
		todoTask.setStartDate(todoTaskDto.getStartDate());
		todoTask.setEndDate(todoTaskDto.getEndDate());
		// status
		String statusName = StringUtils.capitalize(todoTaskDto.getStatus());
		int status;
		switch (statusName) {
		case AppConstants.TODOTASK_STATUS_NAME_PLANNING:
			status = AppConstants.TODOTASK_STATUS_PLANNING;
			break;
		case AppConstants.TODOTASK_STATUS_NAME_COMPLETE:
			status = AppConstants.TODOTASK_STATUS_COMPLETE;
			break;
		case AppConstants.TODOTASK_STATUS_NAME_DOING:
			status = AppConstants.TODOTASK_STATUS_DOING;
			break;
		default:
			throw new APIException(HttpStatus.BAD_REQUEST, "Wrong status name");
		}
		todoTask.setStatus(status);
		return todoTask;
	}
}
