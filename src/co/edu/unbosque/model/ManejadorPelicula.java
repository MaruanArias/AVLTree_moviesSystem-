package co.edu.unbosque.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import modelo.ArbolAvl;
import modelo.NodoAvl;

/**
 * Esta clase define la estructura de datos de tipo Álbol AVL que almacenará los
 * registros ingresados a la aplicación. Implementa la interfaz
 * IManejadorPelicula y la implementación lógica de sus métodos.
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class ManejadorPelicula implements IManejadorPelicula {

	/**
	 * Declaración de atributos de la clase
	 */
	private ArbolAvl peliculasArbolAvl;

	/**
	 * Método constructor de la clase que inicializa su atributo
	 */
	public ManejadorPelicula() {
		super();
		this.peliculasArbolAvl = new ArbolAvl();
		;
	}

	/**
	 * Este método lee la información del archivo y crea objetos de tipo Pelicula
	 * con ayuda de la clase ConstructorPelicula. Posteriormente estos son agregados
	 * al atributo tipo álbol AVL de eta clase
	 * 
	 * @param file Archivo a leer y extraer los registros a almacenar en la
	 *             estructura de datos
	 * @return Un array unidimensional de tipo Pëlicula con los registros existentes
	 *         en el archivo
	 * @throws Exception Excepción que se genera en caso de que lea del archivo un
	 *                   registro con un id que ya exista en la estructura de datos.
	 */
	public Pelicula[] cargarPeliculasDesdeArchivo(File file) throws Exception {
		int limite = getNumeroLineasArchivo(file);
		Pelicula[] peliculas = new Pelicula[limite];

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		int linesToSkip = 1;
		int linesToRead = 0;

		String line;
		while ((line = br.readLine()) != null) {
			if (linesToSkip > 0) {
				linesToSkip--;
				continue;
			}

			Pelicula pelicula = ConstructorPelicula.crearPeliculaDesdeCadena(line);
			peliculas[linesToRead++] = pelicula;
			try {
				this.peliculasArbolAvl.insertar(pelicula);
			} catch (Exception e) {
				System.out.println("Archivo con id  Repetido");
			}
		}

		br.close();
		return peliculas;
	}

	/**
	 * Método que recibe un archivo en el cual se actualizará los registros que
	 * existen en la estructura de datos de la aplicación
	 * 
	 * @param file Archivo a actualizar con los registros de la estructura de datos
	 * @throws IOException Excepción que se genera en caso de que el archivo no
	 *                     pueda ser accedido
	 */
	public void actualizarArchivo(File file) throws IOException {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] peliculas = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);

		File tempFile = new File(file.getAbsolutePath() + ".tmp");
		tempFile.delete();

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(tempFile, false), "ISO-8859-1"));

		bw.write("Título;Estudio;Estado;Versiones;Precio;Clasificación;Año;Género;Fecha de Publicación de DVD;ID\n");

		for (int i = 0; i < limite; i++) {
			if (peliculas[i] == null) {
				continue;
			}

			bw.write(peliculas[i].toString());

		}

		bw.close();

		file.delete();
		if (!tempFile.renameTo(file)) {
			System.out.println("Error al reemplazar el archivo");
		}
	}

	/**
	 * Método que cuenta la candidad de líneas existentes en el archivo
	 * 
	 * @param file Archivo con la información a contar en líneas
	 * @return Número entero sobre la cantidad de líneas del archivo
	 * @throws IOException Excepción que se genera en caso de que el archivo no
	 *                     pueda ser accedido
	 */
	public int getNumeroLineasArchivo(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		int lines = 0;
		while (br.readLine() != null) {
			lines++;
		}

		br.close();
		return lines;
	}

	/**
	 * Método que cuenta la cantidad de registros almacenados en la estructura de
	 * datos
	 * 
	 * @param r Nodo raiz del árbol
	 * @return Número entero con la cantidad de registros almacenados
	 */
	public static int numElementos(NodoAvl r) {// escribir claves de árbol
		if (r != null) {
			int cuantosIzquierda, cuantosDerecha;
			cuantosIzquierda = numElementos((NodoAvl) r.subarbolIzdo());
			// System.out.print(r.valorNodo());
			cuantosDerecha = numElementos((NodoAvl) r.subarbolDcho());
			return cuantosIzquierda + cuantosDerecha + 1;
		} else
			return 0;
	}

	/**
	 * Método que implementa la lógica de buscar un regisro de tipo pélicula en la
	 * estructura de datos que maneja la información en la aplicación
	 * 
	 * @param id Este parámetro es el id del registro a buscar en la estructura de
	 *           datos
	 * @return Un objeto de tipo Pëlicula con la información del registro
	 *         encontrado, en caso de que no sea encontrado el id, retornará nulo
	 */
	@Override
	public Pelicula getPeliculaPorID(int id) {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula pelicula = null;
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);

		int i = 0;
		while (i < limite && pelicula == null) {

			Pelicula tmpPelicula = p[i];
			if (tmpPelicula != null && tmpPelicula.getId() == id) {
				pelicula = tmpPelicula;
			}
			i++;
		}

		return pelicula;
	}

	/**
	 * Métpdp que implementa la lógica de guardar un objeto de tipo Pelicula en la
	 * estrucuta de datos utilizada por la aplicación
	 * 
	 * @param pelicula Objeto a guardar en la estructura de datos
	 * @throws Exception Excepción retornada por la estructura de datos en caso de
	 *                   que el Id del registro en el objeto ya exista en la
	 *                   estructura de datos
	 */
	@Override
	public void guardarPelicula(Pelicula pelicula) throws Exception {
		this.peliculasArbolAvl.insertar(pelicula);
	}

	/**
	 * Método que implementa la lógica de eliminar de la estructura de datos los
	 * registros de tipo Pelicula que coincidan con los ids recibidos por parámetro
	 * 
	 * @param ids Array unidimensional que contiene los ids de las peliculas a
	 *            eliminar
	 * @return Cadena de texto con el resultado de la eliminación de cada uno de los
	 *         registros
	 * @throws Exception Excepción generada en casoo de que no coincida el id
	 *                   recibido por parámetro con los almacenados
	 */
	@Override
	public String eliminarPeliculas(int[] ids) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			Pelicula tmpPelicula = getPeliculaPorID(ids[i]);
			if (tmpPelicula != null) {
				this.peliculasArbolAvl.eliminar(tmpPelicula);
				sb.append(ids[i] + " registro eliminado.\n");
			} else {
				sb.append(ids[i] + " Registro no encontrado.\n");
			}
		}

		return sb.toString();
	}

	/**
	 * Mátodo que implementa la lógica de extraer los géneros de los registros tipo
	 * Pelicula almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con los géneros existentes
	 */
	@Override
	public String[] getGenerosPeliculas() {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<String> tmpGeneros = new ArrayList<String>();

		int j = 0;
		while (j < limite) {

			Pelicula tmpPelicula = p[j];
			boolean registrado = false;
			for (int i = 0; i < tmpGeneros.size() && !registrado; i++) {
				if (tmpGeneros.get(i).equals(tmpPelicula.getGenero())) {
					registrado = true;
				}
			}

			if (!registrado) {
				tmpGeneros.add(tmpPelicula.getGenero());
			}
			j++;
		}

		final int qGeneros = tmpGeneros.size();
		String[] generos = new String[qGeneros];
		for (int i = 0; i < qGeneros; i++) {
			generos[i] = tmpGeneros.get(i);
		}

		return generos;
	}

	/**
	 * Mátodo que implementa la lógica de extraer las clasificaciones de los
	 * registros tipo Pelicula almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las clasificaciones
	 *         existentes
	 */
	@Override
	public String[] getClasificacionesPeliculas() {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<String> tmpClasificaciones = new ArrayList<String>();

		int j = 0;
		while (j < limite) {

			Pelicula tmpPelicula = p[j];
			boolean registrado = false;
			for (int i = 0; i < tmpClasificaciones.size() && !registrado; i++) {
				if (tmpClasificaciones.get(i).equals(tmpPelicula.getClasificacion())) {
					registrado = true;
				}
			}

			if (!registrado) {
				tmpClasificaciones.add(tmpPelicula.getClasificacion());
			}
			j++;
		}

		final int qClasificaciones = tmpClasificaciones.size();
		String[] clasificaciones = new String[qClasificaciones];
		for (int i = 0; i < qClasificaciones; i++) {
			clasificaciones[i] = tmpClasificaciones.get(i);
		}

		return clasificaciones;
	}

	/**
	 * Mátodo que implementa la lógica de extraer las versiones de los registros
	 * tipo Pelicula almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las versiones existentes
	 */
	@Override
	public String[] getVersionesPeliculas() {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<String> tmpVersiones = new ArrayList<String>();

		int j = 0;
		while (j < limite) {

			String[] versions = p[j].getVersiones();
			if (versions == null || versions.length == 0) {
				continue;
			}

			final int totalVersions = versions.length;
			for (int i = 0; i < totalVersions; i++) {
				boolean registrado = false;
				for (int k = 0; k < tmpVersiones.size() && !registrado; k++) {
					if (tmpVersiones.get(k).equals(versions[i])) {
						registrado = true;
					}
				}

				if (!registrado) {
					tmpVersiones.add(versions[i]);
				}
			}
			j++;
		}

		final int qVersiones = tmpVersiones.size();
		String[] versiones = new String[qVersiones];
		for (int i = 0; i < qVersiones; i++) {
			versiones[i] = tmpVersiones.get(i);
		}

		return versiones;
	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como clasificación alguna de las
	 * clasificaciones pasadas por párametros
	 * 
	 * @param clasificacion Array unidimensional que contiene las clasificaciones de
	 *                      los registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasPorClasificacion(String[] clasificacion) {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<Pelicula> tmpPeliculas = new ArrayList<Pelicula>();

		for (int i = 0; i < p.length; i++) {
			Pelicula pelicula = p[i];
			if (pelicula != null) {
				for (int j = 0; j < clasificacion.length; j++) {
					if (pelicula.getClasificacion().equalsIgnoreCase(clasificacion[j])) {
						tmpPeliculas.add(pelicula);
					}
				}
			}
		}

		final int qPeliculas = tmpPeliculas.size();
		Pelicula[] peliculas = new Pelicula[qPeliculas];
		for (int i = 0; i < qPeliculas; i++) {
			peliculas[i] = tmpPeliculas.get(i);
		}

		return peliculas;
	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como género alguno de los generos
	 * pasados por párametros
	 * 
	 * @param genero Array unidimensional que contiene los géneros de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasPorGenero(String[] genero) {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<Pelicula> tmpPeliculas = new ArrayList<Pelicula>();

		for (int i = 0; i < p.length; i++) {
			Pelicula pelicula = p[i];
			if (pelicula != null) {
				for (int j = 0; j < genero.length; j++) {
					if (pelicula.getGenero().equalsIgnoreCase(genero[j])) {
						tmpPeliculas.add(pelicula);
					}
				}
			}
		}

		final int qPeliculas = tmpPeliculas.size();
		Pelicula[] peliculas = new Pelicula[qPeliculas];
		for (int i = 0; i < qPeliculas; i++) {
			peliculas[i] = tmpPeliculas.get(i);
		}

		return peliculas;
	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como versión alguna de las versiones
	 * pasadas por párametros
	 * 
	 * @param version Array unidimensional que contiene las versiones de los
	 *                registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasPorVersion(String[] version) {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<Pelicula> tmpPeliculas = new ArrayList<Pelicula>();

		final int totalVersions = version.length;
		for (int k = 0; k < p.length; k++) {
			Pelicula pelicula = p[k];
			if (pelicula != null) {

				boolean found = false;

				for (int i = 0; i < totalVersions && !found; i++) {
					String[] movieVersions = pelicula.getVersiones();
					for (int j = 0; j < movieVersions.length && !found; j++) {
						if (version[i].equalsIgnoreCase(movieVersions[j])) {
							found = true;
						}
					}
				}

				if (found) {
					tmpPeliculas.add(pelicula);
				}
			}
		}

		final int qPeliculas = tmpPeliculas.size();
		Pelicula[] peliculas = new Pelicula[qPeliculas];
		for (int i = 0; i < qPeliculas; i++) {
			peliculas[i] = tmpPeliculas.get(i);
		}

		return peliculas;
	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula, ordenados de mayor a menor por el dato Costo, que
	 * tengan como género el valor pasado por parámetro
	 * 
	 * @param genero Array unidimensional que contiene el género de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasCostosasPorGenero(String genero) {
		String[] tmpGenero = { genero };
		Pelicula[] peliculas = getPeliculasPorGenero(tmpGenero);
		final int total = peliculas.length;

		for (int i = 0; i < total - 1; i++) {
			for (int j = i + 1; j < total; j++) {
				if (peliculas[j].getPrecio() > peliculas[i].getPrecio()) {
					Pelicula aux = peliculas[i];
					peliculas[i] = peliculas[j];
					peliculas[j] = aux;
				}
			}
		}

		return peliculas;
	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como título, o parte de él, el valor
	 * pasado por párametro
	 * 
	 * @param titulo Texto que debo contener el registro a extraer en su título
	 * 
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasPorTitulo(String titulo) {

		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<Pelicula> tmpPeliculas = new ArrayList<Pelicula>();

		for (int i = 0; i < p.length; i++) {
			Pelicula pelicula = p[i];
			if (pelicula != null) {
				if (pelicula.getTitulo().contains(titulo)) {
					tmpPeliculas.add(pelicula);
				}
			}
		}

		final int qPeliculas = tmpPeliculas.size();
		Pelicula[] peliculas = new Pelicula[qPeliculas];
		for (int i = 0; i < qPeliculas; i++) {
			peliculas[i] = tmpPeliculas.get(i);
		}

		return peliculas;

	}

	/**
	 * Método que implementa la lógica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como dato en su atributo Fecha de Debut
	 * un valor que se encuentre entre los valores pasados por parámetro
	 * 
	 * @param ini Número entero que indica el valor de inicio del rango
	 * @param fin Número entero que indica el valor de fin del rango
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	@Override
	public Pelicula[] getPeliculasPorRangoDebut(int ini, int fin) {

		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		ArrayList<Pelicula> tmpPeliculas = new ArrayList<Pelicula>();

		for (int i = 0; i < p.length; i++) {
			Pelicula pelicula = p[i];
			if (pelicula != null) {
				if (pelicula.getAnio() >= ini && pelicula.getAnio() <= fin) {
					tmpPeliculas.add(pelicula);
				}
			}
		}

		final int qPeliculas = tmpPeliculas.size();
		Pelicula[] peliculas = new Pelicula[qPeliculas];
		for (int i = 0; i < qPeliculas; i++) {
			peliculas[i] = tmpPeliculas.get(i);
		}

		return peliculas;
	}

	/**
	 * Método que extrae los objetos de tipo Pelicula de la estructura de datos tipo
	 * Álbol AVL y almacena en un array unidimencional de tipo Pelicula
	 * 
	 * @return Un array unidimencional de tipo Pelicula con los registros contenidos
	 *         en la estructura de datos.
	 */
	public Pelicula[] getPeliculasArbolAvl() {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol()) + 1;
		Pelicula[] p = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);
		return p;
	}

}
