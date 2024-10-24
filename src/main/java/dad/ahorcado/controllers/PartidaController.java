package dad.ahorcado.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartidaController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public PartidaController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField guessLetterTextField;

    @FXML
    private Label guessedLetterLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView playImageView;

    @FXML
    private BorderPane root;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label secretWordLabel;

    @FXML
    void onLetterAction(ActionEvent event) {

    }

    @FXML
    void onSolveAction(ActionEvent event) {

    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
