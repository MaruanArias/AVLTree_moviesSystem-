package co.edu.unbosque.model;

import modelo.ArbolAvl;
import modelo.Nodo;

/**
 * Esta clase define m�todo para el manejo del modelo y estructura de datos.
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class ConstructorPelicula {
	/**
	 * Declaraci�n de atributos del constructor del modelo
	 */
	private static Pelicula[] peliculas;
	private static int posicion;

	/**
	 * M�todo que crea un objeto del modelo con la informaci�n recibida por
	 * par�metro en una cadena de texto.
	 * 
	 * @param str Cadena de texto con los datos necesarios para la creaci�n de un
	 *            objeto del modelo Pelicula
	 * @return Un objeto de la clase Pelicula si la informaci�n recibida cumple con
	 *         las restricciones de datos, en caso contrario, nulo
	 */
	public static Pelicula crearPeliculaDesdeCadena(String str) {
		if (str.startsWith("\"")) {
			final int nextQuote = str.substring(1).indexOf('\"');
			if (nextQuote > 0) {
				final String title = str.substring(0, nextQuote);
				int index = title.indexOf(';');
				if (index > 0) {
					str = str.substring(0, index - 1) + str.substring(index + 1);
				}
			}
		}

		String[] datos = str.split(";");
		if (datos.length != 10) {
			System.err.println("no pudo ser cargada: " + str);
			return null;
		}

		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(datos[0]);
		pelicula.setEstudio(datos[1]);
		pelicula.setEstado(datos[2]);
		pelicula.setVersiones(datos[3].split(","));

		double precio = -1.0;
		try {
			precio = Double.parseDouble(datos[4].replace(',', '.'));
		} catch (NumberFormatException e) {

		} finally {
			pelicula.setPrecio(precio);
		}

		pelicula.setClasificacion(datos[5]);

		int anio = 0;
		try {
			anio = Integer.parseInt(datos[6]);
		} catch (NumberFormatException e) {

		} finally {
			pelicula.setAnio(anio);
		}

		pelicula.setGenero(datos[7]);
		pelicula.setFechaPublicacionDVD(datos[8]);

		int id = -1;
		try {
			id = Integer.parseInt(datos[9]);
		} catch (NumberFormatException e) {

		} finally {
			pelicula.setId(id);
		}

		return pelicula;
	}

	/**
	 * M�todo que transforma la estructura de datos utilizada, �rbol AVL, a una
	 * estructura de datos est�tica, un array inidimensional.
	 * 
	 * @param arbol     Estructura de datos de tipo �rbol AVL con registros de tipo
	 *                  Pelicula
	 * @param elementos Cantidad de registros que contiene el �rbol
	 * @return Un array unidimencional con los registros contenidos en la estructura
	 *         de datos �rol AVL
	 */
	public static Pelicula[] toArray(ArbolAvl arbol, int elementos) {
		posicion = 0;
		final int total = elementos;
		peliculas = new Pelicula[total];
		obtenerDato(arbol.raizArbol(), 0);
		return peliculas;
	}

	/**
	 * M�todo que recorre el �rbol AVL recursivamente, extrayendo cada registro y almacenandolo en el array.
	 * Este m�todo es utilizado por el m�todo toArray para la conversi�n entre estructuras.
	 * @param pivote La raiz del �rbol AVL
	 * @param nivel Nivel de recorrido del �rbol
	 */
	private static void obtenerDato(Nodo pivote, int nivel) {
		if (pivote != null) {
			Pelicula e = (Pelicula) pivote.dato;
			peliculas[posicion++] = e;
			obtenerDato(pivote.dcho, nivel + 1);
			obtenerDato(pivote.izdo, nivel + 1);
		}

	}

}
