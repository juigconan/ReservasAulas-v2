package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Permanencia {

	private LocalDate dia;
	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Permanencia(LocalDate dia) {
		if(dia == null)
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		setDia(dia);
	}

	public Permanencia(Permanencia permanencia) {
		if (permanencia == null)
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		setDia(permanencia.getDia());
	}

	public LocalDate getDia() {
		if(dia == null)
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		return this.dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null)
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		this.dia = dia;
	}

	public abstract int getPuntos();

	@Override
	public String toString() {
		String dia = this.dia.format(FORMATO_DIA);
		return "día=" + dia;
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);


}
