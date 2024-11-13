package dad.ahorcado.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dad.ahorcado.controllers.PalabrasController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ManejoFicheros {
    private static final File DATA_DIR = new File(System.getProperty("user.home"), ".AhorcadoApp");
    private static final File WORDS_FILE = new File (DATA_DIR, "words.txt");
    private static final File SCORES_FILE = new File (DATA_DIR, "scores.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final ListProperty<Puntuacion> puntuaciones = new SimpleListProperty<>(FXCollections.observableArrayList());
    private static final ArrayList<String> palabrasArchivo = new ArrayList<>();

    public ManejoFicheros() {
    }

    public static void savePersonas(ListProperty<Puntuacion> puntuaciones) throws IOException {

        if (!DATA_DIR.exists()) {
            DATA_DIR.mkdir();
        }
        String json = gson.toJson(new ArrayList<>(puntuaciones));
        Files.writeString(SCORES_FILE.toPath(), json);
    }

    public void añadirPuntuacion(Puntuacion puntuacion){
        puntuaciones.add(puntuacion);
    }

    public static void añadirPalabrasArchivo() {
        if (!DATA_DIR.exists()) {
            DATA_DIR.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WORDS_FILE, false))) {
            for (String palabra : palabrasArchivo) {
                writer.write(palabra);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

//    // Pendiente de cambios
//
//    public static void borrarPalabras(String palabra){
//        try (BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
//
//            String linea;
//            while ((linea = reader.readLine()) != null) {
//                // Solo escribir las líneas que no contienen la palabra a eliminar
//                if (!linea.trim().equals(palabra)) {
//                    writer.write(linea);
//                    writer.newLine();
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error al leer o escribir en el archivo: " + e.getMessage());
//        }
//
//      if (WORDS_FILE.delete()) {
//          tempFile.renameTo(WORDS_FILE);
//      } else {
//         System.out.println("No se pudo eliminar el archivo original.");
//      }
//    }


    public static void crearListaPalabras() {
        try (BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.replace(" ", "").replace("\n", "");
                palabrasArchivo.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void añadirPalabraLista(String palabra){
        palabrasArchivo.add(palabra);
    }

    public static void eliminarPalabraLista(String palabra){
        palabrasArchivo.remove(palabra);
    }

    public static String elegirPalabra(){
        Random random = new Random();
        int indiceAleatorio = random.nextInt(palabrasArchivo.size());
        String palabraAleatoria = palabrasArchivo.get(indiceAleatorio);
        palabrasArchivo.remove(indiceAleatorio);
        return palabraAleatoria;
    }

    public static void cargarPuntuaciones(){
        if (SCORES_FILE.exists()) {
            try (Reader reader = new FileReader(SCORES_FILE)) {
                List<Puntuacion> listaPuntuaciones = Arrays.asList(gson.fromJson(reader, Puntuacion[].class));
                ManejoFicheros.puntuacionesProperty().setAll(listaPuntuaciones);
            } catch (IOException e) {
                System.out.println("Error al cargar puntuaciones: " + e.getMessage());
            }
        }
    }


    public static ListProperty<Puntuacion> getPuntuaciones() {
        return (ListProperty<Puntuacion>) puntuaciones.get();
    }

    public static ListProperty<Puntuacion> puntuacionesProperty() {
        return puntuaciones;
    }

    public static ArrayList<String> getPalabrasArchivo() {
        return palabrasArchivo;
    }



}
