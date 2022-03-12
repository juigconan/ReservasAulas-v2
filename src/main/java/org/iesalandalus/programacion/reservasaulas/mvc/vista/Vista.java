package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {

	private static final String ERROR = "ERROR:";
	private static final String NOMBRE_VALIDO = "nombre valido";
	private static final String CORREO_VALIDO = "correo valido";

	private Controlador controlador;

	public Vista() {
		try {
		Opcion.setVista(this);
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		System.out.println("Menu de la aplicación");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		controlador.terminar();
	}

	// A partir de aqui intentaremos capturar los mensajes de error donde puedan existir

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Aula a borrar");
		try {
			controlador.borrarAula(Consola.leerAula());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Aula a buscar");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAula());
			String mensaje = (aula != null) ? aula.toString() : "Aula no registrada en el sistema.";
			System.out.println(mensaje);
		}catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listado de aulas");
		try {
			List<String> aulas = controlador.representarAulas();
			if (aulas.size() > 0) {
				for (String aula : aulas)
					System.out.println(aula);
			} else
				System.out.println(ERROR + " No existe ningún aula.");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Profesor a borrar");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Profesor a buscar");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje = (profesor != null) ? profesor.toString() : "Este profesor no existe.";
			System.out.println(mensaje);
		}catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de profesores");
		try {
			List<String> profesores = controlador.representarProfesores();
			if (profesores.size() > 0) {
				for (String profesor : profesores)
					System.out.println(profesor);
			} else
				System.out.println(ERROR + " No existe ningún profesor.");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void realizarReserva() {
		try {

			Profesor profesor = null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("Reserva insertada correctamente, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO + ".");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Realizar Reserva");

		String nombreAula;
		String nombreProfesor;
		List<String> profesores = controlador.representarProfesores();
		List<String> aulas = controlador.representarAulas();
		String correoProfesor = new String();
		String correoProfesorLimpio = new String();

		Reserva reserva = null;
		Aula aula = null;
		Permanencia permanencia = null;
		boolean aulaExiste = false;
		boolean profesorExiste = false;
		try {
			nombreProfesor = Consola.leerNombreProfesor();
			nombreAula = Consola.leerNombreAula();

			for (int i = 0; i < profesores.size(); i++) {
				String datosProfesores = profesores.get(i).toString();

				/*
				 * Comprobamos si el nombre del profesor existe al representar los prfesores,
				 * obtenemos su correo y buscamos si el aula existe.
				 */
				if (nombreProfesor.equals(
						datosProfesores.substring(datosProfesores.indexOf('=') + 1, datosProfesores.indexOf(',')))) {
					profesorExiste = true;
					correoProfesor = datosProfesores.substring(datosProfesores.indexOf('=') + 1,
							datosProfesores.lastIndexOf(','));
					correoProfesorLimpio = correoProfesor.replace(nombreProfesor + ", correo=", "");

				}
			}

			for (int j = 0; j < aulas.size(); j++) {
				if (aulas.get(j).toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aula = new Aula(nombreAula);
					aulaExiste = true;

				}
			}

			if (!profesorExiste) {
				System.out.println(ERROR + "No está registrado el profesor " + nombreProfesor + " en el sistema.");

			} else if (!aulaExiste) {
				System.out.println(ERROR + "No está registrada el aula" + nombreAula + " en el sistema.");

			}

			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorLimpio);

			reserva = new Reserva(profesorALeer, aula, permanencia);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return reserva;

	}
	
	public void anularReserva() {

		Consola.mostrarCabecera("Reserva a anular");

		try {
		Profesor profesor = null;
			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Reserva anulada correctamente, " + NOMBRE_VALIDO + CORREO_VALIDO + ".");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		Consola.mostrarCabecera("Listado de reservas");
		try {
			List<String> reservas = controlador.representarReservas();
			if (reservas.size() > 0) {
				for (String reserva : reservas)
					System.out.println(reserva);
			} else
				System.out.println(ERROR + " No existe ningún profesor.");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listado de reservas por aula");
		try {
			List<Reserva> reservas = controlador.getReservasAula(Consola.leerAula());
			if (reservas.get(0) != null)
				for (Reserva reserva : reservas) {
					if (reserva != null)
						System.out.println(reserva);
				}
			else
				System.out.println(ERROR + "No hay reservas para este aula");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de reservas por profesor");
		try {
			List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesor());
			if (reservas.get(0) != null)
				for (Reserva reserva : reservas) {
					if (reserva != null)
						System.out.println(reserva);
				}
			else
				System.out.println(ERROR + "No hay reservas para este profesor");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listado de reservas por permanencia");
		try {
			List<Reserva> reservas = controlador.getReservasPermanencia(new Permanencia(Consola.leerDia(),Consola.leerTramo()));
			if (reservas.get(0) != null)
				for (Reserva reserva : reservas) {
					if (reserva != null)
						System.out.println(reserva);
				}
			else
				System.out.println(ERROR + "No hay reservas para esta permanencia");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consulta de disponibilidad");
		controlador.consultarDisponibilidad(Consola.leerAula(), new Permanencia(Consola.leerDia(),Consola.leerTramo()));
	}

}
