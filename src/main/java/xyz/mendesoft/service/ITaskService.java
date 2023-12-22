package xyz.mendesoft.service;

import xyz.mendesoft.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> listTasks();
    Task listByID(Integer idTask);
    void saveTask(Task task);

    void deleteByID(Task task);

}
