package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {
		
	}
	
	static public void mostrarMenu() {
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	static public void mostrarCabecera(String cabecera) {

		System.out.printf("%n%s%n", cabecera);
		String formatoStr = "%0" + cabecera.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}
	
	static public int elegirOpcion() {
		int eleccion;
			System.out.println("Introduce una opción");
			eleccion = Entrada.entero();	
		return eleccion;
	}
	
	static public Aula leerAula() {
		String nombre = leerNombreAula();
		return new Aula(nombre);
	}
	
	static public String leerNombreAula() {
		System.out.println("Introduce el nombre del aula:");
		String nombre = Entrada.cadena();
		return nombre;
	}
	
	static public Profesor leerProfesor() {
		Profesor profesor;
		String nombre = leerNombreProfesor();
		System.out.println("Introduce el correo del profesor:");
		String correo = Entrada.cadena();
		System.out.println("Introduce el telefono del profersor:");
		String telefono = Entrada.cadena();
		profesor =  (telefono == null || telefono == "") ? new Profesor(nombre, correo) : new Profesor (nombre, correo, telefono);
		return profesor;
	}
	
	
	static public String leerNombreProfesor() {
		System.out.println("Introduce el nombre del profesor:");
		String nombre = Entrada.cadena();
		return nombre;
	}
	
	static public Tramo leerTramo () {
		int eleccion;
		do {
			System.out.println("Introduce un tramo valido(1- Mañana, 2- Tarde):");
			eleccion = Entrada.entero();	
		}while(eleccion != 1 && eleccion != 0);
		
		return Tramo.values()[eleccion-1];
	}
	
	static public LocalDate leerDia() {

		LocalDate dia = null;

		System.out.printf("Introduza una fecha(dd/MM/yyyy):");
		String entrada = Entrada.cadena();
		try {
			dia = LocalDate.parse(entrada, FORMATO_DIA);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es correcto. Formato correcto (dd/MM/yyyy)");
		}

		return dia;
	}
	
}
