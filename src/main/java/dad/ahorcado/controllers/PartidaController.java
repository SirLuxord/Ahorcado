package dad.ahorcado.controllers;

import dad.ahorcado.model.ManejoFicheros;
import dad.ahorcado.model.Puntuacion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.Objects;
import java.util.ResourceBundle;

public class PartidaController implements Initializable {

    private final RootController rootController;

    private final StringProperty nombreJugador = new SimpleStringProperty();
    private final IntegerProperty puntuacion = new SimpleIntegerProperty();
    private static final StringProperty palabrasecreta = new SimpleStringProperty();
    private final StringProperty letrasAdivinadas = new SimpleStringProperty();
    private int contador;
    private static String palabraOriginal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //bindings
        nameLabel.textProperty().bind(nombreJugador);
        scoreLabel.textProperty().bind(puntuacion.asString());
        secretWordLabel.textProperty().bind(palabrasecreta);
        guessedLetterLabel.textProperty().bind(letrasAdivinadas);
        letterButton.disableProperty().bind(guessLetterTextField.textProperty().isEmpty());
        solveButton.disableProperty().bind(guessLetterTextField.textProperty().isEmpty());
    }



    public PartidaController(RootController rootController) {
        this.rootController = rootController;
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
    private Label scoreLabel;

    @FXML
    private Label secretWordLabel;

    @FXML
    private ImageView playImageView;

    @FXML
    private BorderPane root;

    @FXML
    private Button solveButton;

    @FXML
    private Button letterButton;

    @FXML
    void onLetterAction(ActionEvent event) {
        if (guessLetterTextField.getText().length() > 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AhorcadoApp");
            alert.setHeaderText("Este campo no pude contener más de un carácter a no " +
                    "ser que se quiera resolver la palabra.");
            alert.setContentText("Porfavor introduzca solo una letra.");
            alert.show();
        } else {
            if (!desencriptarLetra()){
                contador++;
                cambiarImagen();
            }
            guessLetterTextField.clear();
        }
    }

    @FXML
    void onSolveAction(ActionEvent event) {

        desencriptarPalabra();
        UsuarioController usuarioController = rootController.getUsuarioController();
        Puntuacion puntuacion1 = new Puntuacion(nombreJugador.get(),puntuacion.get());
        ManejoFicheros.puntuacionesProperty().add(puntuacion1);
        guessLetterTextField.clear();
        rootController.partidaTab.setContent(usuarioController.getRoot());
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public void setPalabraOriginal(String palabraOriginal) {
        this.palabraOriginal = palabraOriginal;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void setNombreJugador(String nombre) {
        nombreJugador.set(nombre);
    }

    public void setPuntuacion(int puntos) {
        puntuacion.set(puntos);
    }

    public static void encriptarPalabra(){
        StringBuilder palabraEncriptada = new StringBuilder();
        for (int i = 0; i < palabraOriginal.length(); i++){
            char c = palabraOriginal.charAt(i);

            // Verifica si el carácter es una letra
            if (Character.isLetter(c)) {
                palabraEncriptada.append('_'); // Reemplaza la letra por "_"
            } else {
                palabraEncriptada.append(c); // Deja el carácter no alfabético sin cambios
            }
        }

        palabrasecreta.set(palabraEncriptada.toString());
    }

    private Boolean desencriptarLetra() {
        Boolean encontrada = false;
        char letra = getLetter();
        StringBuilder palabraEncriptada = new StringBuilder(palabrasecreta.get());
        String palabra = palabraOriginal.toLowerCase();
        String letrasActuales = letrasAdivinadas.get();

        if (letrasActuales == null) {
            letrasActuales = ""; // Inicializa como vacío si es null para evitar null pointer
        }

        if (letra != 'ñ' && letra != 'n') {
            palabra = quitarAcentos(palabra);
        }

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra){
                encontrada = true;
                if (palabraEncriptada.charAt(i) == '_'){
                    puntuacion.setValue(puntuacion.get() + 1);
                }
                palabraEncriptada.setCharAt(i, palabraOriginal.charAt(i));
            }
        }

        if (!letrasActuales.isEmpty() && !letrasActuales.contains(String.valueOf(letra))) {
            letrasAdivinadas.set(letrasAdivinadas.get() + ", " + letra);
        } else if (letrasActuales.isEmpty()) {
            letrasAdivinadas.set(String.valueOf(letra));
        }

        palabrasecreta.set(palabraEncriptada.toString());

        return encontrada;
    }

    private void desencriptarPalabra(){
        String palabra = palabraOriginal.toLowerCase();
        palabra = quitarAcentos(palabra);

        if (guessLetterTextField.getText().replace("ñ", "n").equals(palabra)){
            palabrasecreta.set(palabraOriginal);
        } else {
            contador++;
        }
    }


    private char getLetter() {
        String input = guessLetterTextField.getText().toLowerCase();
        Character letra = null;

        // Verifica que el campo no esté vacío
        if (!input.isEmpty()) {

            letra = input.charAt(0);

            return letra;
        } else {
            return letra;
        }
    }

    private static String quitarAcentos(String texto) {
        // Normaliza el texto a la forma NFD (decomposed form), que separa los acentos de las letras
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Elimina los caracteres de marca de acento (diacríticos) usando una expresión regular
        return textoNormalizado.replaceAll("\\p{M}", "");
    }

    private void cambiarImagen(){
        // Ruta relativa en el classpath
        String imagePath = "/images/hangman/" + contador + ".png";

        // Obtener la URL de la imagen
        URL imageUrl = getClass().getResource(imagePath);

        if (imageUrl != null) {
            Image newImage = new Image(imageUrl.toExternalForm());
            playImageView.setImage(newImage);
        } else {
            System.err.println("Imagen no encontrada en la ruta: " + imagePath);
        }
    }
}
