package dad.ahorcado.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PuntuacionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public PuntuacionController(GridPane root) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntuacionView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private GridPane root;

    @FXML
    private ListView<?> scoreListView;

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

}
