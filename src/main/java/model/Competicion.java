package main.java.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import main.java.exceptions.ProgramaMeException;

public class Competicion {

	private static final int MAXIMO_EQUIPOS = 36;
	private static final String SALTO_LINEA = System.getProperty("line.separator");
	/**
	 * ToDo (°ロ°) ! WARNING!! ACHTUNG!! ATTENTION!! (°ロ°) !
	 * 
	 * Cambia el tipo de colección a la interfaz hija y su implementación más
	 * adecuada y justifícalo.
	 */
	private Collection<Equipo> equipos;

	public Competicion() {
		this.equipos = new HashSet<>();
	}

	public void addEquipo(Equipo e) throws ProgramaMeException {
		if (e == null || e.getNombre() == null) {
			throw new ProgramaMeException("No puedes insertar equipos nulos");
		} else if (this.equipos.size() == MAXIMO_EQUIPOS) {
			throw new ProgramaMeException("Ya no pueden inscribirse mas equipos");
		}
		this.equipos.add(e);
	}

	public void eliminarEquipo(String nombre) throws ProgramaMeException {
		if (nombre == null) {
			throw new ProgramaMeException("Nombre no válido.");
		}
		boolean encontrado = false;
		Iterator<Equipo> it = this.equipos.iterator();
		while (it.hasNext() && !encontrado) {
			if (it.next().equals(new Equipo(nombre))) {
				it.remove();
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new ProgramaMeException("Equipo no encontrado.");
		}
	}

	public int obtenerEdadConcursanteMasVeterano() {
//		No contempla que sea hombre
		return this.equipos.stream().sorted((e1, e2) -> -Integer.compare(e1.getEdadMaxima(), e2.getEdadMaxima()))
				.findFirst().get().getEdadMaxima();
	}

	/**
	 * Devuelve una cadena con todos los equipos ordenados por el número de
	 * problemas resueltos (resuelto == true) satisfactoriamente. Implementar con
	 * clase para comparar.
	 * 
	 */
	public String mostrarEquiposOrdenadosPorProblemasResueltos_Comparator() {
		return this.equipos.stream().sorted(new Comparator<Equipo>() {
			@Override
			public int compare(Equipo o1, Equipo o2) {
				return -Integer.compare(o1.getProblemasResueltos(), o2.getProblemasResueltos());
			}
		}).map(String::valueOf).collect(Collectors.joining(SALTO_LINEA));
	}

	/**
	 * Primo hermano del anterior
	 * (mostrarEquiposOrdenadosPorProblemasResueltos_Comparator), pero con lambda.
	 */
	public String mostrarEquiposOrdenadosPorProblemasResueltos_Lambda() {
		return this.equipos.stream()
				.sorted((e1, e2) -> -Integer.compare(e1.getProblemasResueltos(), e2.getProblemasResueltos()))
				.map(String::valueOf).collect(Collectors.joining(SALTO_LINEA));
	}

	/**
	 * Devuelve una cadena con el ránking de equipos: el ránking ordena primero
	 * decrecientemente por el número de problemas resueltos por un equipo dado,
	 * pero ante empate (por ejemplo, si dos equipos han resuelto el mismo número de
	 * problemas) se ordenan decrecientemente por el tiempo total de finalización de
	 * los ejercicios resueltos.
	 */
	public String mostrarRanking() {
//		return this.equipos.stream().sorted((e1, e2) -> {
//			int resultado = -Integer.compare(e1.getProblemasResueltos(), e2.getProblemasResueltos());
//			if (resultado == 0) {
//				resultado = Integer.compare(e1.tiempoFinalizacionTotal(), e2.tiempoFinalizacionTotal());
//			}
//			return resultado;
//		}).map(String::valueOf).collect(Collectors.joining(SALTO_LINEA));

		return this.equipos.stream()
				.sorted(Comparator.comparingInt(Equipo::getProblemasResueltos).reversed()
						.thenComparingInt(Equipo::tiempoFinalizacionTotal))
				.map(String::valueOf).collect(Collectors.joining(SALTO_LINEA));
	}

	/**
	 * Devuelve una estructura de datos donde para cada tipo de problema se asocian
	 * los equipos que han tratado de realizar (superado o no) un problema dado.
	 */
	public Map<String, List<Equipo>> agruparEquiposPorProblemas() {
		Map<String, List<Equipo>> equiposPorProblemas = new HashMap<>();
		this.equipos.forEach(e -> e.getProblemas().forEach(p -> {
			String temp = p.getTipo().toString();
			equiposPorProblemas.computeIfAbsent(temp, k -> new ArrayList<>()).add(e);
		}));
		return equiposPorProblemas;
	}

	/**
	 * Devuelve una estructura de datos donde la clave indica la composición de cada
	 * grupo (femenino, masculino, mixto) y se asocian los equipos según este valor
	 */
	public Map<String, List<Equipo>> agruparEquiposPorGenero() {
		Map<String, List<Equipo>> equiposPorGenero = new HashMap<>();
		this.equipos.forEach(e -> {
			String temp = e.esEquipoMixto() ? "Mixto" : e.esEquipoFemenino() ? "Femenino" : "Masculino";
			equiposPorGenero.computeIfAbsent(temp, k -> new ArrayList<>()).add(e);
		});
		return equiposPorGenero;
	}

	/**
	 * Devuelve una estructura de datos con los nombres de todos los concursantes
	 * sin repetidos
	 */
	public Collection<String> obtenerNombresUnicos() {
		List<String> nombresUnicos = new ArrayList<>();
		for (Equipo equipo : this.equipos) {
			for (Concursante nombre : equipo.getConcursantes()) {
				if (!nombresUnicos.contains(nombre.getNombre()))
					nombresUnicos.add(nombre.getNombre());
			}
		}
		return nombresUnicos;
	}

}
