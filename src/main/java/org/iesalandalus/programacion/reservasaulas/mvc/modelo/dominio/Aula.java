package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
	static final float PUNTOS_POR_PUESTO = 0.5f;
	static final int MAX_PUESTOS = 50;
	static final int MIN_PUESTOS = 10;
	private String nombre;
	private int puesto;
	
	public Aula(String nombre, int puesto) {
		setNombre(nombre);
		setPuesto(puesto);
	}
	
	public Aula(Aula aula) {
		if(aula == null)
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		setNombre(aula.getNombre());
		setPuesto(aula.getPuestos());
	}
	
	private void setNombre(String nombre) {
		if(nombre == null)
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		if(nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getPuestos() {
		return this.puesto;
	}

	public void setPuesto(int puesto) {
		if(puesto>MAX_PUESTOS | puesto<MIN_PUESTOS)
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		this.puesto = puesto;
	}
	
	public float getPuntos() {
		return getPuestos()*PUNTOS_POR_PUESTO;
	}
	
	public Aula getAulaFicticia(String nombre) {
		return new Aula(nombre, 10);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", puestos=" + puesto;
	}
	
}
