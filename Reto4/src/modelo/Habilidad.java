package modelo;

public class Habilidad {
    private int cod;
    private String nombre;
    private String descripcion;

    public Habilidad(int cod, String nombre, String descripcion) {
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getCod() {
        return cod;
    } 

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habilidad)) return false;
        Habilidad habilidad = (Habilidad) o;
        return getCod() == habilidad.getCod();
    }
}
