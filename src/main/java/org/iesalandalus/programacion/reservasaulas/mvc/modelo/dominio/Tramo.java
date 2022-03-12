package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {
	MANANA("Mañana"), TARDE("Tarde");
	
	String cadenaAMostrar;
	
	private Tramo (String nombre) {
		this.cadenaAMostrar = nombre;
	}
	
	@Override
	public String toString() {
		return cadenaAMostrar;
	}
}

