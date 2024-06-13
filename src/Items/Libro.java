package Items;

import Items.Autor;

public class Libro {
    int id;
    String titulo;
    String genero;
    Autor autor;
    public static int idMayor =1;

    public Libro(int id, String titulo, String genero, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
