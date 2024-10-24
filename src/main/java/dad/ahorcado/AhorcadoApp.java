package dad.ahorcado;

import dad.ahorcado.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {
    private RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(rootController.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ahorcado");
        primaryStage.show();

    }
}
