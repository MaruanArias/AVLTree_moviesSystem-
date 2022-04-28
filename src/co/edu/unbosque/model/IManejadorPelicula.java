package co.edu.unbosque.model;

/**
 * Esta clase define una colecci�n de m�todos con las funcionalidades b�sicas de
 * la aplicaci�n
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public interface IManejadorPelicula {
	/**
	 * M�todo que busca un regisro de tipo p�licula en la estructura de datos que
	 * maneja la informaci�n en la aplicaci�n
	 * 
	 * @param id Este par�metro es el id del registro a buscar en la estructura de
	 *           datos
	 * @return Un objeto de tipo P�licula con la informaci�n del registro
	 *         encontrado, en caso de que no sea encontrado el id, retornar� nulo
	 */
	public Pelicula getPeliculaPorID(int id);

	/**
	 * M�tpdp que guarda un objeto de tipo Pelicula en la estrucuta de datos
	 * utilizada por la aplicaci�n
	 * 
	 * @param pelicula Objeto a guardar en la estructura de datos
	 * @throws Exception Excepci�n retornada por la estructura de datos en caso de
	 *                   que el Id del registro en el objeto ya exista en la
	 *                   estructura de datos
	 */
	public void guardarPelicula(Pelicula pelicula) throws Exception;

	/**
	 * M�todo que elimina de la estructura de datos los registros de tipo Pelicula
	 * que coincidan con los ids recibidos por par�metro
	 * 
	 * @param ids Array unidimensional que contiene los ids de las peliculas a
	 *            eliminar
	 * @return Cadena de texto con el resultado de la eliminaci�n de cada uno de los
	 *         registros
	 * @throws Exception Excepci�n generada en casoo de que no coincida el id
	 *                   recibido por par�metro con los almacenados
	 */
	public String eliminarPeliculas(int[] ids) throws Exception;

	/**
	 * M�todo que extrae los g�neros de los registros tipo Pelicula almacenados en
	 * la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con los g�neros existentes
	 */
	public String[] getGenerosPeliculas();

	/**
	 * M�todo que extrae las clasificaciones de los registros tipo Pelicula
	 * almacenados en la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las clasificaciones
	 *         existentes
	 */
	public String[] getClasificacionesPeliculas();

	/**
	 * M�todo que extrae las versiones de los registros tipo Pelicula almacenados en
	 * la estructura de datos
	 * 
	 * @return Array unidimensional de tipo String con las versiones existentes
	 */
	public String[] getVersionesPeliculas();

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como clasificaci�n alguna de las clasificaciones pasadas por
	 * p�rametros
	 * 
	 * @param clasificacion Array unidimensional que contiene las clasificaciones de
	 *                      los registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasPorClasificacion(String[] clasificacion);

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como g�nero alguno de los generos pasados por p�rametros
	 * 
	 * @param genero Array unidimensional que contiene los g�neros de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasPorGenero(String[] genero);

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como versi�n alguna de las versiones pasadas por p�rametros
	 * 
	 * @param version Array unidimensional que contiene las versiones de los
	 *                registros que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasPorVersion(String[] version);

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula,
	 * ordenados de mayor a menor por el dato Costo, que tengan como g�nero el valor
	 * pasado por par�metro
	 * 
	 * @param genero Array unidimensional que contiene el g�nero de los registros
	 *               que se desean extraer
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasCostosasPorGenero(String genero);

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como t�tulo, o parte de �l, el valor pasado por p�rametro
	 * 
	 * @param titulo Texto que debo contener el registro a extraer en su t�tulo
	 * 
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasPorTitulo(String titulo);

	/**
	 * M�todo que extrae de la estructura de datos los registros de tipo Pelicula
	 * que tengan como dato en su atributo Fecha de Debut un valor que se encuentre
	 * entre los valores pasados por par�metro
	 * 
	 * @param ini N�mero entero que indica el valor de inicio del rango
	 * @param fin N�mero entero que indica el valor de fin del rango
	 * @return Un array unidimensional de tipo Pelicula con los registros que hayan
	 *         cumplido la condici�n
	 */
	public Pelicula[] getPeliculasPorRangoDebut(int ini, int fin);
}
