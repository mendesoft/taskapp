package xyz.mendesoft.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.mendesoft.model.Task;
import xyz.mendesoft.service.TaskService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView <Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> idTaskColumn;

    @FXML
    private TableColumn<Task, String> nameTaskColumn;


    @FXML
    private TableColumn<Task, String> responsibleColumn;


    @FXML
    private TableColumn<Task, String> statusColumn;

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private TextField txtTask;

    @FXML
    private TextField txtResponsible;

    @FXML
    private TextField txtStatus;

    private Integer idTaskInternal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumn();
        listTasks();
    }

    private void configureColumn(){
        idTaskColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        responsibleColumn.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

    }


    private void listTasks() {
        taskList.clear();
        taskList.addAll(taskService.listTasks());
        taskTable.setItems(taskList);
    }

    public void addTask(){
        if (txtTask.getText().isEmpty()) {
            showMessage("Validation error", "You must provide a task");
            txtTask.requestFocus();
            return;
        }else {
            var task = new Task();
            recolectForm(task);
            task.setIdTask(null);
            taskService.saveTask(task);
            showMessage("Information", "Added Task!");
            cleanForm();
            listTasks();
        }

    }

    private void showMessage(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void recolectForm(Task task) {
        if(idTaskInternal != null)
            task.setIdTask(idTaskInternal);

        task.setName(txtTask.getText());
        task.setResponsible(txtResponsible.getText());
        task.setStatus(txtStatus.getText());
    }

    public void cleanForm(){
        idTaskInternal = null;
        txtTask.clear();
        txtResponsible.clear();
        txtStatus.clear();

    }

    public void loadTaskForm(){
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            idTaskInternal = task.getIdTask();
            txtTask.setText(task.getName());
            txtResponsible.setText(task.getResponsible());
            txtStatus.setText(task.getStatus());
        }
    }

    public void updateTask(){
        if (idTaskInternal == null) {
            showMessage("Information", "You must select a task");
            return;
        }

        if (txtTask.getText().isEmpty()){
            showMessage("Validation error", "You must provide a task");
            txtTask.requestFocus();
            return;
        }

        var task = new Task();
        recolectForm(task);
        taskService.saveTask(task);
        showMessage("Information", "Updated task!");
        cleanForm();
        listTasks();

    }

    public void deleteTask(){
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            LOGGER.info("deleted register!");
            taskService.deleteByID(task);
            showMessage("Information", "deleted task : " + task.getIdTask());
            cleanForm();
            listTasks();
        }

        else {
            showMessage("Error", "No task has been selected");
        }
    }

}
