package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.math3.exception.NullArgumentException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private List<Aula> listaAulas;

	public Aulas() {
		listaAulas = new ArrayList<>();
	}

	public Aulas(Aulas aulas) {
		if (aulas == null)
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		setAulas(aulas);
	}

	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		List<Aula> otrasAulas = new ArrayList<>();
		// Un bucle tan largo como el array introducido en el que vamos metiendo
		// en cada vuelta un elemento de dicho array en uno nuevo, creando así
		// una copia
		for (int i = 0; i < getNumAulas(); i++) {

			Aula aula = aulas.get(i);
			otrasAulas.add(new Aula(aula));

		}

		return otrasAulas;

	}

	public List<Aula> getAulas() {
		return copiaProfundaAulas(listaAulas);
	}

	private void setAulas(Aulas aulas) {
		copiaProfundaAulas(aulas.listaAulas);
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		if(!listaAulas.contains(aula))
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		listaAulas.remove(aula);

		System.out.println("Aula borrada");
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		// Se ejecutará si no contiene el aula
		if (!listaAulas.contains(aula))
			listaAulas.add(new Aula(aula));
		else 
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
	}

	public Aula buscar(Aula aula) {

		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		if (listaAulas.contains(aula))
			return new Aula (aula);
		else
			return null;

	}
	
	public int getNumAulas() {
		if(listaAulas == null)
			return 0;
		return listaAulas.size();
	}

	public List<String> representar() {
		
		List<String> representarAulas = new ArrayList<>();
		// Un bucle tan largo como el array nuestro en el que vamos metiendo
		// en cada vuelta el string de cada elemento de nuestra lista
		// dentro de una lista de String
		for (int i = 0; i < getAulas().size(); i++) {

			Aula aula = getAulas().get(i);
			representarAulas.add(new Aula(aula).toString());

		}

		return representarAulas;

	}

}
