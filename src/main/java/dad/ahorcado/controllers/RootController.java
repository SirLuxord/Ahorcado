package dad.ahorcado.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    //Controllers

    private final PartidaController partidaController = new PartidaController();
    private final PalabrasController palabrasController = new PalabrasController();
    private final PuntuacionController puntuacionController = new PuntuacionController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        palabrasTab.setContent(palabrasController.getRoot());
        puntuacionesTab.setContent(puntuacionController.getRoot());
    }



    public RootController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField nameTextField;

    @FXML
    private Tab palabrasTab;

    @FXML
    private Tab partidaTab;

    @FXML
    private Tab puntuacionesTab;

    @FXML
    private TabPane root;

    @FXML
    void onPlayAction(ActionEvent event) {
        partidaTab.setContent(partidaController.getRoot());
    }

    public TabPane getRoot() {
        return root;
    }

}
