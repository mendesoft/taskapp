package xyz.mendesoft;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.mendesoft.presentation.SystemTaskFx;

@SpringBootApplication
public class TaskappApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TaskappApplication.class, args);
		Application.launch(SystemTaskFx.class, args);
	}

}
