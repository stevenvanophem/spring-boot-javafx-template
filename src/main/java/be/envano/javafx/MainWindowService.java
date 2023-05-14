package be.envano.javafx;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@Service
public class MainWindowService {

	@EventListener(JavaFXApplication.StageReadyEvent.class)
	public void onStageReady(JavaFXApplication.StageReadyEvent event) {
		final Stage stage = event.stage();

		Label helloWorldLabel = new Label("Hello World!");

		VBox vbox = new VBox();
		vbox.getChildren().add(helloWorldLabel);

		Scene scene = new Scene(vbox, 640, 480);

		stage.setScene(scene);
		stage.setTitle("Lifetracker");
		stage.show();
	}

}
