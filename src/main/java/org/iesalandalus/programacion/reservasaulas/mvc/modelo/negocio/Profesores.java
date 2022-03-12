package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

/*Esta clase es exactamente igual que Aulas, cambiando el nombre de algunas variables, asi que por eso la copio y pego de la clase Aula
 * que he creado hace un momento
 */
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private List<Profesor> listaProfesores;

	public Profesores() {
		listaProfesores = new ArrayList<>();
	}

	public Profesores(Profesores profesores) {
		if (profesores == null)
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		setProfesores(profesores);
	}

	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		List<Profesor> otrosProfesores = new ArrayList<>();
		// Un bucle tan largo como el array introducido en el que vamos metiendo
		// en cada vuelta un elemento de dicho array en uno nuevo, creando así
		// una copia
		for (int i = 0; i < getNumProfesores(); i++) {

			Profesor profesor = profesores.get(i);
			otrosProfesores.add(new Profesor(profesor));

		}

		return otrosProfesores;

	}

	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(listaProfesores);
	}

	private void setProfesores(Profesores profesores) {
		copiaProfundaProfesores(profesores.listaProfesores);
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		if(!listaProfesores.contains(profesor))
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		listaProfesores.remove(profesor);

		System.out.println("Profesor borrado");
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		// Se ejecutará si no contiene el aula
		if (!listaProfesores.contains(profesor))
			listaProfesores.add(new Profesor(profesor));
		else 
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
	}

	public Profesor buscar(Profesor profesor) {

		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		if (listaProfesores.contains(profesor))
			return new Profesor (profesor);
		else
			return null;

	}
	
	public int getNumProfesores() {
		if(listaProfesores == null)
			return 0;
		return listaProfesores.size();
	}

	public List<String> representar() {
		
		List<String> representarProfesores = new ArrayList<>();
		// Un bucle tan largo como el array nuestro en el que vamos metiendo
		// en cada vuelta el string de cada elemento de nuestra lista
		// dentro de una lista de String
		for (int i = 0; i < getProfesores().size(); i++) {

			Profesor profesor = getProfesores().get(i);
			representarProfesores.add(new Profesor(profesor).toString());

		}

		return representarProfesores;

	}

}
