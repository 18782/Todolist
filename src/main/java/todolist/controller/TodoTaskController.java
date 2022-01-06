package todolist.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todolist.service.TodoTaskService;
import todolist.todotask.TodoTaskDto;
import todolist.todotask.TodoTaskResponse;
import todolist.utils.AppConstants;

@RestController
@RequestMapping()
public class TodoTaskController {

    private TodoTaskService todotaskService;

    public TodoTaskController(TodoTaskService todotaskService) {
        this.todotaskService = todotaskService;
    }

    // create blog todotask rest api
    @PostMapping("/api/todotask")
    public ResponseEntity<TodoTaskDto> createTodoTask(@Valid @RequestBody TodoTaskDto todotaskDto){
        return new ResponseEntity<>(todotaskService.createTodoTask(todotaskDto), HttpStatus.CREATED);
    }

    // get all todotasks rest api
    @GetMapping("/api/todolist")
    public TodoTaskResponse getAllTodoTasks(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return todotaskService.getAllTodoTasks(pageNo, pageSize, sortBy, sortDir);
    }

    // get todotask by id
    @GetMapping(value = "/api/todotask/{id}")
    public ResponseEntity<TodoTaskDto> getTodoTaskByIdV1(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(todotaskService.getTodoTaskById(id));
    }

    // update todotask by id rest api
    @PutMapping("/api/todotask/{id}")
    public ResponseEntity<TodoTaskDto> updateTodoTask(@Valid @RequestBody TodoTaskDto todotaskDto, @PathVariable(name = "id") long id){

       TodoTaskDto todotaskResponse = todotaskService.updateTodoTask(todotaskDto, id);

       return new ResponseEntity<>(todotaskResponse, HttpStatus.OK);
    }

    // delete todotask rest api
    @DeleteMapping("/api/todotask/{id}")
    public ResponseEntity<String> deleteTodoTask(@PathVariable(name = "id") long id){

        todotaskService.deleteTodoTaskById(id);

        return new ResponseEntity<>("TodoTask entity deleted successfully.", HttpStatus.OK);
    }
}
