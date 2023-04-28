package modelo;



public class Modo {
    private int id;
    private String nombre;

    public Modo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos getter y setter para id y nombre
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modo other = (Modo) obj;
		return id == other.id;
	}
}
