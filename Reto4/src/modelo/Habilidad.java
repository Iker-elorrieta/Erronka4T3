package modelo;

public class Habilidad {
    private int cod;
    private int cod_personaje;
    private String nombre;
    private String descripcion;

    public Habilidad(int cod, int cod_personaje, String nombre, String descripcion) {
		super();
		this.cod = cod;
		this.cod_personaje = cod_personaje;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


    public Habilidad() {
		// TODO Auto-generated constructor stub
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

	public int getCod_personaje() {
		return cod_personaje;
	}

	public void setCod_personaje(int cod_personaje) {
		this.cod_personaje = cod_personaje;
	}
}
