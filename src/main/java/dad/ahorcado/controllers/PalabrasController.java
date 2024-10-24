package dad.ahorcado.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PalabrasController implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public PalabrasController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PalabrasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private GridPane root;

    @FXML
    private ListView<?> wordsListView;

    @FXML
    private TextField wordsTextField;

    @FXML
    void onAddButton(ActionEvent event) {

    }

    @FXML
    void onRemoveButton(ActionEvent event) {

    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }
}
