package xyz.mendesoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.mendesoft.model.Task;

public interface ITaskRepo extends JpaRepository<Task, Integer> {
}
