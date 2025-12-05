/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Storage;

/**
 *
 * @author Matthew Hernandez
 */



import Model.assignment;
import Model.child;
import Model.Gift;
import Model.user;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;



public class DataStore {
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Rutas de los archivos JSON
   private final Path usuariosPath = Paths.get(System.getProperty("user.dir"), "data", "usuarios.json");
private final Path regalosPath = Paths.get(System.getProperty("user.dir"), "data", "regalos.json");
private final Path ninosPath = Paths.get(System.getProperty("user.dir"), "data", "ninos.json");
private final Path asignacionesPath = Paths.get(System.getProperty("user.dir"), "data", "asignaciones.json");

    // Listas en memoria
    private List<user> usuarios = new ArrayList<>();
    private List<Gift> regalos = new ArrayList<>();
    private List<child> ninos = new ArrayList<>();
    private List<assignment> asignaciones = new ArrayList<>();

    // Método para cargar todos los archivos
    public void loadAll() throws IOException {
        usuarios = readList(usuariosPath, new TypeToken<List<user>>(){}.getType());
        regalos = readList(regalosPath, new TypeToken<List<Gift>>(){}.getType());
        ninos = readList(ninosPath, new TypeToken<List<child>>(){}.getType());
        asignaciones = readList(asignacionesPath, new TypeToken<List<assignment>>(){}.getType());
    }

    // Método para guardar todos los archivos
    public void saveAll() throws IOException {
        writeList(usuariosPath, usuarios);
        writeList(regalosPath, regalos);
        writeList(ninosPath, ninos);
        writeList(asignacionesPath, asignaciones);
    }

    // Métodos auxiliares para leer y escribir listas
    private <T> List<T> readList(Path path, Type type) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.writeString(path, "[]"); // Inicializa vacío
        }
        try (Reader r = Files.newBufferedReader(path)) {
            List<T> list = gson.fromJson(r, type);
            return list != null ? list : new ArrayList<>();
        }
    }

    private <T> void writeList(Path path, List<T> list) throws IOException {
    try (Writer writer = Files.newBufferedWriter(path)) {
        gson.toJson(list, writer);


        }
    }

    // Getters para acceder a las listas
    public List<user> getUsuarios() { return usuarios; }
    public List<Gift> getRegalos() { return regalos; }
    public List<child> getNinos() { return ninos; }
    public List<assignment> getAsignaciones() { return asignaciones; }

    public boolean regaloExiste(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
