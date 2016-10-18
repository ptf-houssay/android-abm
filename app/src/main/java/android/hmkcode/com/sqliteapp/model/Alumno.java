package android.hmkcode.com.sqliteapp.model;

public class Alumno {

    private int id;
    private String nombre;
    private int edad;

    public Alumno(){}

    public Alumno(String nombre, int edad) {
        super();
        this.nombre = nombre;
        this.edad = edad;
    }

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
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno [id=" + id + ", nombre=" + nombre + ", edad=" + edad
                + "]";
    }



}