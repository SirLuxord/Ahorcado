package dad.ahorcado.controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dad.ahorcado.model.ManejoFicheros.añadirPalabraLista;
import static dad.ahorcado.model.ManejoFicheros.eliminarPalabraLista;

public class PalabrasController implements Initializable {

    // model

    private static final ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
    private static final StringProperty selectedpalabra = new SimpleStringProperty();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // bindings

        wordsListView.itemsProperty().bind(palabras);
        selectedpalabra.bind(wordsListView.getSelectionModel().selectedItemProperty());
        removeButton.disableProperty().bind(selectedpalabra.isNull());
        addButton.disableProperty().bind(wordsTextField.textProperty().isEmpty());

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
    private ListView<String> wordsListView;

    @FXML
    private TextField wordsTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    void onAddButton(ActionEvent event) {
        palabras.add(wordsTextField.getText());
        añadirPalabraLista(wordsTextField.getText());
        wordsTextField.clear();
    }

    @FXML
    void onRemoveButton(ActionEvent event) {
        palabras.remove(selectedpalabra.get());
        eliminarPalabraLista(selectedpalabra.get());
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    public static ObservableList<String> getPalabras() {
        return palabras.get();
    }

    public ListProperty<String> palabrasProperty() {
        return palabras;
    }
}
