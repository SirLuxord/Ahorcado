package dad.ahorcado.controllers;

import dad.ahorcado.model.ManejoFicheros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dad.ahorcado.model.ManejoFicheros.elegirPalabra;


public class UsuarioController implements Initializable {
    private final RootController rootController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Modificar el constructor para recibir RootController
    public UsuarioController(RootController rootController) {
        this.rootController = rootController;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UsuarioView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField nameTextField;

    @FXML
    void onPlayAction(ActionEvent event) {

        ManejoFicheros.getPalabrasArchivo().clear();
        ManejoFicheros.getPalabrasArchivo().addAll(PalabrasController.getPalabras());

        if (ManejoFicheros.getPalabrasArchivo().isEmpty()){
            alertListaVacia();
        } else {
            iniciarPartida();
        }
    }

    @FXML
    private BorderPane root;

    public RootController getRootController() {
        return rootController;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public void alertListaVacia(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("AhorcadoApp");
        alert.setHeaderText("La lista de palabras esta vacía.");
        alert.setContentText("Porfavor introduzca palabras para poder jugar.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Cambiar a la pestaña deseada
                rootController.getRoot().getSelectionModel().select(rootController.getPalabrasTab());
            }
        });
    }

    public void iniciarPartida(){
        PartidaController partidaController = rootController.getPartidaController();
        if (!nameTextField.getText().isEmpty()) {
            partidaController.setNombreJugador(nameTextField.getText());
        } else {
            partidaController.setNombreJugador("Anónimo");
        }
        partidaController.setPalabraOriginal(elegirPalabra());
        partidaController.setPuntuacion(0);
        partidaController.setContador(1);
        PartidaController.encriptarPalabra();

        // Pasamos la interfaz a la de la partida
        rootController.partidaTab.setContent(partidaController.getRoot());
    }

}
