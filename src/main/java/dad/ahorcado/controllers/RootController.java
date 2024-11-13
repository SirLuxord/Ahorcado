package dad.ahorcado.controllers;

import dad.ahorcado.model.ManejoFicheros;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RootController implements Initializable {

    //Controllers

    private final PalabrasController palabrasController = new PalabrasController();
    private final PuntuacionController puntuacionController = new PuntuacionController();
    private UsuarioController usuarioController;
    private PartidaController partidaController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioController = new UsuarioController(this);
        partidaController = new PartidaController(this);
        palabrasTab.setContent(palabrasController.getRoot());
        puntuacionesTab.setContent(puntuacionController.getRoot());
        partidaTab.setContent(usuarioController.getRoot());
        ManejoFicheros.crearListaPalabras();
        System.out.println(ManejoFicheros.getPalabrasArchivo());
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
    private Tab palabrasTab;

    @FXML
    Tab partidaTab;

    @FXML
    private Tab puntuacionesTab;

    @FXML
    private TabPane root;

    public TabPane getRoot() {
        return root;
    }

    public void setRoot(TabPane root) {
        this.root = root;
    }

    public PartidaController getPartidaController() {
        return partidaController;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public Tab getPalabrasTab() {
        return palabrasTab;
    }


}
