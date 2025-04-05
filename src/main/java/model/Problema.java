package main.java.model;

import java.util.Objects;

public class Problema {

	private static final int TIEMPO_PENALIZACION = 20;
	private TipoProblema tipo;
	private int tiempoFinalizacion;
	private int penalizacion;
	private boolean resuelto;

	public Problema(TipoProblema tipo, int tiempoFinalizacion) {
		this.tipo = tipo;
		this.tiempoFinalizacion = tiempoFinalizacion;
		this.penalizacion = 0;
		this.resuelto = false;
	}

	/**
	 * Tiempo total a computar el problema teniendo en cuenta el tiempo que se ha
	 * tardado en resolver y las penalizaciones
	 * 
	 * @return
	 */
	public int getTiempoTotal() {
		return this.tiempoFinalizacion - this.penalizacion;
	}

	/**
	 * Marca el problema como resuelto y guarda el tiempo en minutos que se ha
	 * tardado en resolver
	 */
	public void marcarProblemaResuelto() {
		this.resuelto = true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipo);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj instanceof Problema && this.hashCode() == obj.hashCode();
	}

	/**
	 * Incrementa el tiempo de penalización del problema en el cálculo final para
	 * los empates
	 */
	public void penalizarProblema() {
		this.penalizacion += TIEMPO_PENALIZACION;
	}

	public TipoProblema getTipo() {
		return this.tipo;
	}

	public boolean isResuelto() {
		return this.resuelto;
	}
}
