package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

// Tras varias versiones de la aplicación me he cansado de tener el aviso por no usar al ER y he decidido usarlas de aqui en adelante

public class Profesor {

	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		if (telefono == null) {
			setNombre(nombre);
			setCorreo(correo);
		} else {
			setNombre(nombre);
			setCorreo(correo);
			setTelefono(telefono);
		}
	}

	public Profesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		setNombre(profesor.getNombre());
		setCorreo(profesor.getCorreo());
		setTelefono(profesor.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		if (nombre == "")
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		this.nombre = formateaNombre(nombre);
	}

	private String formateaNombre(String nombre) {
		String nuevoNombre;
		nombre.trim();
		// En esta array de cadena metemos el cada palabra del nombre que este separada
		// por espacios.
		String[] cadena = nombre.split(" ");
		// Creamos un StringBuilder para poder sumar cada palabra ya arreglada
		StringBuilder sb = new StringBuilder();

		for (String palabra : cadena) {
			// Convertimos cada palabra en un array de carateres
			char[] nuevaPalabra = palabra.toLowerCase().toCharArray();
			// Ponemos en mayuscula la primera letra de cada palabra
			nuevaPalabra[0] = Character.toUpperCase(nuevaPalabra[0]);
			// Sumamos la palabra a nuestro StringBuilder cun un espacio al final
			sb.append(new String(nuevaPalabra)).append(" ");
		}
		// Con el toString conseguimos que el metodo devuelva un String en lugar de un
		// StringBuilder, y con usamos el trim para desacernos del ultimo espacio
		nuevoNombre = sb.toString().trim();
		return nuevoNombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		// Cambiamos el if para que si correo esta vacio o compuesto de espacios en blanco salte el error.
		if (correo.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		if (!correo.matches(ER_CORREO))
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido");
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		if (!telefono.matches(ER_TELEFONO))
			throw new IllegalArgumentException("ERROR: El formato del telefono no es válido.");
		this.telefono = telefono;
	}
	
	public Profesor getProfesorFicticio(String correo) {
		return new Profesor("Jose Ficticio", correo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		if (telefono == null)
			return "nombre=" + getNombre() + ", correo=" + getCorreo();
		else
			return "nombre=" + getNombre() + ", correo=" + getCorreo() + ", telefono=" + getTelefono();
	}

}
