package dad.ahorcado.model;

import java.util.ArrayList;

public class Palabras {
    ArrayList<String> palabras = new ArrayList<>();

    public Palabras(ArrayList<String> palabras) {
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void addpalabra(String palabra){
        palabras.add(palabra);
    }
}
