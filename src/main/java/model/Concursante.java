package main.java.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Concursante {

	private Genero genero;
	private String nombre;
	private LocalDate fechaNacimiento;

	public Concursante(String nombre, LocalDate fechaNacimiento, Genero genero) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
	}

	public boolean esHombre() {
		return this.genero==Genero.HOMBRE;
	}

	public boolean esMujer() {
		return !esHombre();
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return (int) ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
	}

	@Override
	public int hashCode() {
		return Objects.hash(genero, nombre, fechaNacimiento);
	}

	/**
	 * Dos concursantes son iguales si coinciden todos sus atributos
	 */
	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj instanceof Concursante && this.hashCode() == obj.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Nombre: %s, genero: %s, fecha nacimiento: %s", this.nombre, this.genero,
				this.fechaNacimiento);
	}

}
