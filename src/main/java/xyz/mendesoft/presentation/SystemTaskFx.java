package xyz.mendesoft.presentation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import xyz.mendesoft.TaskappApplication;

import java.io.IOException;

public class SystemTaskFx extends Application {

//    public static void main(String[] args) {
//        launch(args);
//    }

    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init() throws Exception {
        this.applicationContext = new SpringApplicationBuilder(TaskappApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TaskappApplication.class.getResource("/template/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();

    }
}
