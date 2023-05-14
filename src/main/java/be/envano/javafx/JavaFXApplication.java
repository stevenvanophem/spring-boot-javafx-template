package be.envano.javafx;

import java.util.Objects;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import be.envano.YourApplication;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() {
		ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
			applicationContext.registerBean(Application.class, () -> JavaFXApplication.this);
			applicationContext.registerBean(Parameters.class, this::getParameters);
			applicationContext.registerBean(HostServices.class, this::getHostServices);
		};

		this.context = new SpringApplicationBuilder()
				.sources(YourApplication.class)
				.initializers(initializer)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage primaryStage) {
		StageReadyEvent event = new StageReadyEvent(primaryStage);
		this.context.publishEvent(event);
	}

	@Override
	public void stop() {
		this.context.close();
		Platform.exit();
	}

	public record StageReadyEvent(Stage stage) {

		public StageReadyEvent {
			Objects.requireNonNull(stage, "stage must not be null");
		}

	}

}
