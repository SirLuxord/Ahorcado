package dad.ahorcado;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dad.ahorcado.controllers.RootController;
import dad.ahorcado.model.ManejoFicheros;
import dad.ahorcado.model.Puntuacion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static dad.ahorcado.model.ManejoFicheros.*;

public class AhorcadoApp extends Application {

    private RootController rootController = new RootController();

    @Override
    public void init() throws Exception {

        ManejoFicheros.crearListaPalabras();
        ManejoFicheros.cargarPuntuaciones();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(rootController.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ahorcado");
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ManejoFicheros.añadirPalabrasArchivo();
        ManejoFicheros.savePersonas(ManejoFicheros.puntuacionesProperty());
    }
}
