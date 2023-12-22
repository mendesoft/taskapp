package xyz.mendesoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.mendesoft.model.Task;
import xyz.mendesoft.repository.ITaskRepo;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService implements ITaskService{

    private final ITaskRepo repo;

    @Override
    public List<Task> listTasks() {
        return repo.findAll();
    }

    @Override
    public Task listByID(Integer idTask) {
        return repo.findById(idTask).orElse(null);
    }

    @Override
    public void saveTask(Task task) {
        repo.save(task);
    }

    @Override
    public void deleteByID(Task task) {
        repo.delete(task);
    }
}
