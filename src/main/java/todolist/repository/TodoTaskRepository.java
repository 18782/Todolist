package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todolist.entity.TodoTask;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> {

}
