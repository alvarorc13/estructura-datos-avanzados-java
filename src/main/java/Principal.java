package main.java;

import java.time.LocalDate;
import main.java.exceptions.ProgramaMeException;
import main.java.model.Competicion;
import main.java.model.Concursante;
import main.java.model.Equipo;
import main.java.model.Genero;
import main.java.model.Problema;
import main.java.model.TipoProblema;

public class Principal {

	public static void main(String[] args) {
		Concursante c1 = new Concursante("Pepe", LocalDate.now().minusYears(80), Genero.HOMBRE);
		Concursante c2 = new Concursante("Juan", LocalDate.now().minusYears(20), Genero.HOMBRE);
		Concursante c3 = new Concursante("Sofia", LocalDate.now().minusYears(20), Genero.MUJER);

		Equipo e1 = new Equipo("Equipo1");

		Concursante c4 = new Concursante("Maria", LocalDate.now().minusYears(20), Genero.MUJER);
		Concursante c5 = new Concursante("Lucia", LocalDate.now().minusYears(50), Genero.MUJER);
		Concursante c6 = new Concursante("Claudia", LocalDate.now().minusYears(20), Genero.MUJER);

		Equipo e2 = new Equipo("Equipo2");
		
		try {
			e1.addConcursante(c1);
			e1.addConcursante(c2);
			e1.addConcursante(c3);
			e2.addConcursante(c4);
			e2.addConcursante(c5);
			e2.addConcursante(c6);
		} catch (ProgramaMeException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		Competicion comp1 = new Competicion();
		try {
			comp1.addEquipo(e1);
			comp1.addEquipo(e2);
		} catch (ProgramaMeException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			e1.comenzarProblema(new Problema(TipoProblema.A, 0));
			e1.resolverProblema(new Problema(TipoProblema.A, 50));
		} catch (ProgramaMeException e) {
			System.out.println(e.getLocalizedMessage());
		}

		try {
			e2.comenzarProblema(new Problema(TipoProblema.A, 0));
			e2.resolverProblema(new Problema(TipoProblema.A, 50));
			e2.comenzarProblema(new Problema(TipoProblema.B, 0));
			e2.resolverProblema(new Problema(TipoProblema.B, 50));
		} catch (ProgramaMeException e) {
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println(comp1.mostrarEquiposOrdenadosPorProblemasResueltos_Comparator());
		System.out.println();
		System.out.println(comp1.mostrarEquiposOrdenadosPorProblemasResueltos_Lambda());
		System.out.println();
		System.out.println(comp1.obtenerEdadConcursanteMasVeterano());
		System.out.println();
		System.out.println(comp1.mostrarRanking());
		System.out.println();
//		System.out.println(comp1.agruparEquiposPorGenero());
//		System.out.println();
//		System.out.println(comp1.agruparEquiposPorProblemas());
//		System.out.println();
//		System.out.println(comp1.obtenerNombresUnicos());

//		try {
//			e1.addConcursante(c1);
//			e1.addConcursante(c2);
////			e1.addConcursante(null); // El concursante no puede ser nulo
//			e1.addConcursante(c3);
////			e1.addConcursante(c4); // El equipo está a tope
////			e1.comenzarProblema(null); // Problema no valido
//			e1.comenzarProblema(new Problema(TipoProblema.A, 0));
////			e1.comenzarProblema(new Problema(TipoProblema.A)); // El problema ya está comenzado.
////			e1.resolverProblema(null); // Problema no valido
//			e1.resolverProblema(new Problema(TipoProblema.A, 50));
//			
//			System.out.println(e1);
//
//		} catch (ProgramaMeException e) {
//			System.out.println(e.getLocalizedMessage());
//		}

	}

}
