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
 * Esta clase define la estructura de datos de tipo �lbol AVL que almacenar� los
 * registros ingresados a la aplicaci�n. Implementa la interfaz
 * IManejadorPelicula y la implementaci�n l�gica de sus m�todos.
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class ManejadorPelicula implements IManejadorPelicula {

	/**
	 * Declaraci�n de atributos de la clase
	 */
	private ArbolAvl peliculasArbolAvl;

	/**
	 * M�todo constructor de la clase que inicializa su atributo
	 */
	public ManejadorPelicula() {
		super();
		this.peliculasArbolAvl = new ArbolAvl();
		;
	}

	/**
	 * Este m�todo lee la informaci�n del archivo y crea objetos de tipo Pelicula
	 * con ayuda de la clase ConstructorPelicula. Posteriormente estos son agregados
	 * al atributo tipo �lbol AVL de eta clase
	 * 
	 * @param file Archivo a leer y extraer los registros a almacenar en la
	 *             estructura de datos
	 * @return Un array unidimensional de tipo P�licula con los registros existentes
	 *         en el archivo
	 * @throws Exception Excepci�n que se genera en caso de que lea del archivo un
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
	 * M�todo que recibe un archivo en el cual se actualizar� los registros que
	 * existen en la estructura de datos de la aplicaci�n
	 * 
	 * @param file Archivo a actualizar con los registros de la estructura de datos
	 * @throws IOException Excepci�n que se genera en caso de que el archivo no
	 *                     pueda ser accedido
	 */
	public void actualizarArchivo(File file) throws IOException {
		int limite = numElementos(this.peliculasArbolAvl.raizArbol());
		Pelicula[] peliculas = ConstructorPelicula.toArray(this.peliculasArbolAvl, limite);

		File tempFile = new File(file.getAbsolutePath() + ".tmp");
		tempFile.delete();

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(tempFile, false), "ISO-8859-1"));

		bw.write("T�tulo;Estudio;Estado;Versiones;Precio;Clasificaci�n;A�o;G�nero;Fecha de Publicaci�n de DVD;ID\n");

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
	 * M�todo que cuenta la candidad de l�neas existentes en el archivo
	 * 
	 * @param file Archivo con la informaci�n a contar en l�neas
	 * @return N�mero entero sobre la cantidad de l�neas del archivo
	 * @throws IOException Excepci�n que se genera en caso de que el archivo no
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
	 * M�todo que cuenta la cantidad de registros almacenados en la estructura de
	 * datos
	 * 
	 * @param r Nodo raiz del �rbol
	 * @return N�mero entero con la cantidad de registros almacenados
	 */
	public static int numElementos(NodoAvl r) {// escribir claves de �rbol
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
	 * M�todo que implementa la l�gica de buscar un regisro de tipo p�licula en la
	 * estructura de datos que maneja la informaci�n en la aplicaci�n
	 * 
	 * @param id Este par�metro es el id del registro a buscar en la estructura de
	 *           datos
	 * @return Un objeto de tipo P�licula con la informaci�n del registro
	 *         encontrado, en caso de que no sea encontrado el id, retornar� nulo
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
	 * M�tpdp que implementa la l�gica de guardar un objeto de tipo Pelicula en la
	 * estrucuta de datos utilizada por la aplicaci�n
	 * 
	 * @param pelicula Objeto a guardar en la estructura de datos
	 * @throws Exception Excepci�n retornada por la estructura de datos en caso de
	 *                   que el Id del registro en el objeto ya exista en la
	 *                   estructura de datos
	 */
	@Override
	public void guardarPelicula(Pelicula pelicula) throws Exception {
		this.peliculasArbolAvl.insertar(pelicula);
	}

	/**
	 * M�todo que implementa la l�gica de eliminar de la estructura de datos los
	 * registros de tipo Pelicula que coincidan con los ids recibidos por par�metro
	 * 
	 * @param ids Array unidimensional que contiene los ids de las peliculas a
	 *            eliminar
	 * @return Cadena de texto con el resultado de la eliminaci�n de cada uno de los
	 *         registros
	 * @throws Exception Excepci�n generada en casoo de que no coincida el id
	 *                   recibido por par�metro con los almacenados
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
	 * M�todo que implementa la l�gica de extraer los g�neros de los registros tipo
	 * Pelicula almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con los g�neros existentes
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
	 * M�todo que implementa la l�gica de extraer las clasificaciones de los
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
	 * M�todo que implementa la l�gica de extraer las versiones de los registros
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como clasificaci�n alguna de las
	 * clasificaciones pasadas por p�rametros
	 * 
	 * @param clasificacion Array unidimensional que contiene las clasificaciones de
	 *                      los registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como g�nero alguno de los generos
	 * pasados por p�rametros
	 * 
	 * @param genero Array unidimensional que contiene los g�neros de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como versi�n alguna de las versiones
	 * pasadas por p�rametros
	 * 
	 * @param version Array unidimensional que contiene las versiones de los
	 *                registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula, ordenados de mayor a menor por el dato Costo, que
	 * tengan como g�nero el valor pasado por par�metro
	 * 
	 * @param genero Array unidimensional que contiene el g�nero de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como t�tulo, o parte de �l, el valor
	 * pasado por p�rametro
	 * 
	 * @param titulo Texto que debo contener el registro a extraer en su t�tulo
	 * 
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que implementa la l�gica de extraer de la estructura de datos los
	 * registros de tipo Pelicula que tengan como dato en su atributo Fecha de Debut
	 * un valor que se encuentre entre los valores pasados por par�metro
	 * 
	 * @param ini N�mero entero que indica el valor de inicio del rango
	 * @param fin N�mero entero que indica el valor de fin del rango
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
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
	 * M�todo que extrae los objetos de tipo Pelicula de la estructura de datos tipo
	 * �lbol AVL y almacena en un array unidimencional de tipo Pelicula
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
