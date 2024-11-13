package dad.ahorcado.model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Palabras {

    private final ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());

    public Palabras() {
    }

    public ObservableList<String> getPalabras() {
        return palabras.get();
    }

    public ListProperty<String> palabrasProperty() {
        return palabras;
    }

    public void setPalabras(ObservableList<String> palabras) {
        this.palabras.set(palabras);
    }

    public void elegirPalabra(){

    }
}
