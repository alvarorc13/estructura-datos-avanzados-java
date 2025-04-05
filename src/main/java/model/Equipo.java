package main.java.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import main.java.exceptions.ProgramaMeException;

public class Equipo {
	private static final int MAXIMO_PROBLEMAS = 8;
	/**
	 * ToDo (°ロ°) ! WARNING!! ACHTUNG!! ATTENTION!! (°ロ°) !
	 * 
	 * Cambia el tipo de colección a la interfaz hija y su implementación más
	 * adecuada y justifícalo.
	 */
	private Collection<Concursante> concursantes;
	private Collection<Problema> problemas;
	private String nombre;
	private static final int MAXIMO_POR_EQUIPO = 3;
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.concursantes = new HashSet<>();
		this.problemas = new HashSet<>();
	}

	public void addConcursante(Concursante concursante) throws ProgramaMeException {
		if (concursante == null) {
			throw new ProgramaMeException("El concursante no puede ser nulo");
		} else if (concursantes.size()==MAXIMO_POR_EQUIPO) {
			throw new ProgramaMeException("El equipo está a tope.");
		}
		this.concursantes.add(concursante);
	}

	public Collection<Concursante> getConcursantes() {
		return concursantes;
	}

	public void comenzarProblema(Problema p) throws ProgramaMeException {
		if (p == null) {
			throw new ProgramaMeException("Problema no valido");
		} else if (this.problemas.contains(p)) {
			throw new ProgramaMeException("El problema ya está comenzado.");
		} else if (problemas.size()==MAXIMO_PROBLEMAS) {
			throw new ProgramaMeException("Ya no hay más problemas");
		}
		this.problemas.add(p);
	}

	public void resolverProblema(Problema p) throws ProgramaMeException {
		if (p == null) {
			throw new ProgramaMeException("Problema no valido");
		} else if (!problemas.contains(p)) {
			throw new ProgramaMeException("No puedes resolver un problema que no has comenzado");			
		}
		for (Problema pro : problemas) {
			if (pro.equals(p)) {
				pro.marcarProblemaResuelto();
			}
		}
	}

	public void fallarEnEntregaDeProblema(Problema p) {
		this.problemas.forEach(pro -> {
			if (pro.equals(p)) {
				pro.penalizarProblema();
			}
		});
	}

	public boolean esEquipoMasculino() {
		return this.concursantes.stream().filter(c -> c.esHombre()).count() == MAXIMO_POR_EQUIPO;
	}

	public boolean esEquipoFemenino() {
		return this.concursantes.stream().filter(c -> c.esMujer()).count() == MAXIMO_POR_EQUIPO;
	}

	public boolean esEquipoMixto() {
		return !esEquipoMasculino() && !esEquipoFemenino();
	}

	public int getEdadMaxima() {
		return this.concursantes.stream().sorted((c1, c2) -> -Integer.compare(c1.getEdad(), c2.getEdad())).findFirst().get().getEdad();
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getProblemasResueltos() {
		return (int) this.problemas.stream().filter(p -> p.isResuelto()).count();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj instanceof Equipo && this.hashCode() == obj.hashCode();
	}

	/**
	 * Devuelve una cadena con el nombre de los integrantes, el número de problemas
	 * resueltos y el tiempo total de ejecución, es decir, la suma del tiempo de
	 * finalización - penalización de los problemas resueltos (sólo resueltos).
	 */
	public String toString() {
		return "Nombre de los integrantes: "
				+ this.concursantes.stream().map(c -> c.getNombre()).collect(Collectors.joining(", ")).toString()
				+ ", numero de problemas resueltos: " + getProblemasResueltos() + ", tiempo total de ejecucion: "
				+ tiempoFinalizacionTotal();
	}

	public int tiempoFinalizacionTotal() {
		return this.problemas.stream().filter(p -> p.isResuelto()).mapToInt(p->p.getTiempoTotal()).sum();
	}

	public Collection<Problema> getProblemas() {
		return problemas;
	}

}
