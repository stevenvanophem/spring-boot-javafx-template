package be.envano;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.envano.javafx.JavaFXApplication;
import javafx.application.Application;

@SpringBootApplication
public class YourApplication {

	public static void main(String[] args) {
		Application.launch(JavaFXApplication.class, args);
	}

}