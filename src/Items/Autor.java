package Items;

public class Autor {
    int id;
    String nombre;
    String apellido;
    public static int idMayor =1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Autor(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return String.format("%s %s", nombre, apellido);
    }

    public String writeOnFile() {
        return String.format("%s %s", nombre, apellido);
    }
}
