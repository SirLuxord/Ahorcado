package dad.ahorcado.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GsonSample {
    private static final File DATA_DIR = new File(System.getProperty("user.home"), ".AhorcadoApp");
    //private static final File WORDS_FILE = new File (DATA_DIR, "words.txt");
    private static final File SCORES_FILE = new File (DATA_DIR, "scores.json");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void savePersonas(List<Puntuacion> puntuaciones) throws IOException {
        String json = gson.toJson(puntuaciones);
        Files.writeString(SCORES_FILE.toPath(), json);
    }



}
