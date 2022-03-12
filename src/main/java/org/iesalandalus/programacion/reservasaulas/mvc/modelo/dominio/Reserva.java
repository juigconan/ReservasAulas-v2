package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {

	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;
	
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		if(profesor == null)
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		if(aula == null)
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		if(permanencia == null)
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);		
	}
	
	public Reserva(Reserva reserva) {
		if(reserva == null)
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		setProfesor(reserva.getProfesor());
		setAula(reserva.getAula());
		setPermanencia(reserva.getPermanencia());	
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Permanencia getPermanencia() {
		return permanencia;
	}

	public void setPermanencia(Permanencia permanencia) {
		this.permanencia = permanencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia)
				&& Objects.equals(profesor, other.profesor);
	}

	@Override
	public String toString() {
		return "Profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia;
	}
	
	
}
