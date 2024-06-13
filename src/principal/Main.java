package principal;

import windows.accionesSQLWindow;
import Items.*;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Autor> listaAutores = new ArrayList<>();
    public static ArrayList<Libro> listaLibros = new ArrayList<>();

    public static void main(String[] args) {
        new accionesSQLWindow();
    }
    public static Autor getAutorById(int id){
        int i = 0;
        while (i<listaAutores.size()){
            if (listaAutores.get(i).getId() == id) return listaAutores.get(i);
            i++;
        }
        return null;
    }
    public static boolean autorExists(Autor autor){
        int i = 0;
        while (i<listaAutores.size()){
            if (listaAutores.get(i) == autor) return true;
            i++;
        }
        return false;
    }
    public static boolean libroExists(Libro libro){
        int i = 0;
        while (i<listaLibros.size()){
            if (listaLibros.get(i) == libro) return true;
            i++;
        }
        return false;
    }
    public static Libro getLibroById(int id){
        int i = 0;
        while (i<listaLibros.size()){
            if (listaLibros.get(i).getId() == id) return listaLibros.get(i);
            i++;
        }
        return null;
    }
    public static int biggerAutorId(){
        int idMayor = listaAutores.get(0).getId();
        for(int i =1; i<listaAutores.size(); i++){
            if(listaAutores.get(i).getId() > idMayor) idMayor = listaAutores.get(i).getId();
        }
        return idMayor;
    }
    public static int biggerLibroId(){
        int idMayor = listaLibros.get(0).getId();
        for(int i =1; i<listaLibros.size(); i++){
            if(listaLibros.get(i).getId() > idMayor) idMayor = listaLibros.get(i).getId();
        }
        return idMayor;
    }

}