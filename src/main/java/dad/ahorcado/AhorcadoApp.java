package dad.ahorcado;

import com.google.gson.Gson;
import dad.ahorcado.controllers.RootController;
import dad.ahorcado.model.Puntuacion;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class AhorcadoApp extends Application {

    private RootController rootController = new RootController();



    @Override
    public void init() throws Exception {


    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(rootController.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ahorcado");
        primaryStage.show();

    }
}
