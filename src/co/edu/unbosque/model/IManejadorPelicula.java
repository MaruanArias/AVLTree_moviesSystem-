package co.edu.unbosque.model;

/**
 * Esta clase define una colección de métodos con las funcionalidades básicas de
 * la aplicación
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public interface IManejadorPelicula {
	/**
	 * Método que busca un regisro de tipo pélicula en la estructura de datos que
	 * maneja la información en la aplicación
	 * 
	 * @param id Este parámetro es el id del registro a buscar en la estructura de
	 *           datos
	 * @return Un objeto de tipo Pëlicula con la información del registro
	 *         encontrado, en caso de que no sea encontrado el id, retornará nulo
	 */
	public Pelicula getPeliculaPorID(int id);

	/**
	 * Métpdp que guarda un objeto de tipo Pelicula en la estrucuta de datos
	 * utilizada por la aplicación
	 * 
	 * @param pelicula Objeto a guardar en la estructura de datos
	 * @throws Exception Excepción retornada por la estructura de datos en caso de
	 *                   que el Id del registro en el objeto ya exista en la
	 *                   estructura de datos
	 */
	public void guardarPelicula(Pelicula pelicula) throws Exception;

	/**
	 * Método que elimina de la estructura de datos los registros de tipo Pelicula
	 * que coincidan con los ids recibidos por parámetro
	 * 
	 * @param ids Array unidimensional que contiene los ids de las peliculas a
	 *            eliminar
	 * @return Cadena de texto con el resultado de la eliminación de cada uno de los
	 *         registros
	 * @throws Exception Excepción generada en casoo de que no coincida el id
	 *                   recibido por parámetro con los almacenados
	 */
	public String eliminarPeliculas(int[] ids) throws Exception;

	/**
	 * Mátodo que extrae los géneros de los registros tipo Pelicula almacenados en
	 * la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con los géneros existentes
	 */
	public String[] getGenerosPeliculas();

	/**
	 * Mátodo que extrae las clasificaciones de los registros tipo Pelicula
	 * almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las clasificaciones
	 *         existentes
	 */
	public String[] getClasificacionesPeliculas();

	/**
	 * Mátodo que extrae las versiones de los registros tipo Pelicula almacenados en
	 * la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las versiones existentes
	 */
	public String[] getVersionesPeliculas();

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como clasificación alguna de las clasificaciones pasadas por
	 * párametros
	 * 
	 * @param clasificacion Array unidimensional que contiene las clasificaciones de
	 *                      los registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasPorClasificacion(String[] clasificacion);

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como género alguno de los generos pasados por párametros
	 * 
	 * @param genero Array unidimensional que contiene los géneros de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasPorGenero(String[] genero);

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como versión alguna de las versiones pasadas por párametros
	 * 
	 * @param version Array unidimensional que contiene las versiones de los
	 *                registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasPorVersion(String[] version);

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula,
	 * ordenados de mayor a menor por el dato Costo, que tengan como género el valor
	 * pasado por parámetro
	 * 
	 * @param genero Array unidimensional que contiene el género de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasCostosasPorGenero(String genero);

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como título, o parte de él, el valor pasado por párametro
	 * 
	 * @param titulo Texto que debo contener el registro a extraer en su título
	 * 
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasPorTitulo(String titulo);

	/**
	 * Método que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como dato en su atributo Fecha de Debut un valor que se encuentre
	 * entre los valores pasados por parámetro
	 * 
	 * @param ini Número entero que indica el valor de inicio del rango
	 * @param fin Número entero que indica el valor de fin del rango
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condición
	 */
	public Pelicula[] getPeliculasPorRangoDebut(int ini, int fin);
}
