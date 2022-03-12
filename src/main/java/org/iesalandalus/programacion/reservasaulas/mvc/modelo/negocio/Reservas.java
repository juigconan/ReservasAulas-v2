package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;

public class Reservas {

	private List<Reserva> listaReservas;

	public Reservas() {
		listaReservas = new ArrayList<Reserva>();
	}

	public Reservas(Reservas reserva) {
		if(reserva == null)
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		listaReservas = reserva.getReservas();

	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		if (!listaReservas.contains(reserva))
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		listaReservas.remove(reserva);
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		if (listaReservas.contains(reserva))
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		listaReservas.add(reserva);
	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		if (listaReservas.contains(reserva))
			return new Reserva(reserva);
		return null;
	}

	public int getNumReservas() {
		return listaReservas.size();
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<Reserva>();
		// Un bucle tan largo como el array introducido en el que vamos metiendo
		// en cada vuelta un elemento de dicho array en uno nuevo, creando así
		// una copia
		for (int i = 0; i < getNumReservas(); i++) {

			Reserva reserva = reservas.get(i);
			otrasReservas.add(new Reserva(reserva));

		}

		return otrasReservas;

	}
	
	public List<Reserva> getReservas(){
		return copiaProfundaReservas(listaReservas);
	}

	public List<String> representar() {
		List<String> representarReservas = new ArrayList<>();
		for(int i = 0;i < getReservas().size();i++) {
			Reserva reserva = getReservas().get(i);
			representarReservas.add(reserva.toString());
		}
		return representarReservas;
	}

	/*
	 * Esta clase es exactamente igual que Aulas y Profesores hasta este punto,
	 * cambiando el nombre de algunas variables, asi que por eso la copio y pego de
	 * la clase Aula que he creado hace un momento y continuo con el resto del
	 * código.
	 */
	public List<Reserva> getReservasProfesor(Profesor profesor) {

		List<Reserva> reserva = new ArrayList<Reserva>();

		for (int i = 0; i < getNumReservas(); i++) {
			if (listaReservas.get(i).getProfesor().equals(profesor)) {
				reserva.add(new Reserva(listaReservas.get(i)));
			}
		}

		return reserva;

	}

	public List<Reserva> getReservasAula(Aula aula) {
		List<Reserva> reserva = new ArrayList<Reserva>();

		for (int i = 0; i < getNumReservas(); i++) {
			if (listaReservas.get(i).getAula().equals(aula)) {
				reserva.add(new Reserva(listaReservas.get(i)));
			}
		}
		return reserva;

	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {

		List<Reserva> reserva = new ArrayList<>();

		for (int i = 0; i < getNumReservas(); i++) {
			if (listaReservas.get(i).getPermanencia().equals(permanencia)) {
				reserva.add(new Reserva(listaReservas.get(i)));
			}
		}
		return reserva;

	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		boolean disponible = true;

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; i < getReservas().size(); i++) {
			if (aula.equals(listaReservas.get(i).getAula()) && permanencia.equals(listaReservas.get(i).getPermanencia())) {
				disponible = false;
			}

		}

		return disponible;
	}

}
