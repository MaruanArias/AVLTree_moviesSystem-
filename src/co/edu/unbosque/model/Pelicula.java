package co.edu.unbosque.model;

import modelo.Comparador;

/**
 * Esta clase define los atributos del modelo abstracto pelicula definido para
 * el almacenamiento y manipulaci�n de registros en la aplicaci�n. Implementa la
 * interfaz Comparador necesaria para el almacenamiento y consulta de registros
 * en la estructura de datos �rbol AVL
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class Pelicula implements Comparador {

	/**
	 * Declaraci�n de atributos del modelo
	 */
	private String titulo;
	private String estudio;
	private String estado;
	private String[] versiones;
	private double precio;
	private String clasificacion;
	private int anio;
	private String genero;
	private String fechaPublicacionDVD;
	private int id;

	/**
	 * Constructor por defecto de la clase
	 */
	public Pelicula() {

	}

	/**
	 * Constructor con par�metros de la clase
	 * 
	 * @param titulo              dato a almacenar como t�tulo
	 * @param estudio             dato a almacenar como estudio
	 * @param estado              dato a almacenar como estado
	 * @param versiones           dato a almacenar como versiones
	 * @param precio              dato a almacenar como precio
	 * @param clasificacion       dato a almacenar como clasificaci�n
	 * @param anio                dato a almacenar como a�o
	 * @param genero              dato a almacenar como g�nero
	 * @param fechaPublicacionDVD dato a almacenar como fecha puclicaci�n del DVD
	 * @param id                  dato a almacenar como id
	 */
	public Pelicula(String titulo, String estudio, String estado, String[] versiones, double precio,
			String clasificacion, int anio, String genero, String fechaPublicacionDVD, int id) {
		super();
		this.titulo = titulo;
		this.estudio = estudio;
		this.estado = estado;
		this.versiones = versiones;
		this.precio = precio;
		this.clasificacion = clasificacion;
		this.anio = anio;
		this.genero = genero;
		this.fechaPublicacionDVD = fechaPublicacionDVD;
		this.id = id;
	}

	/**
	 * Convierte el atributo versiones de tipo array a una cadena de texto separada
	 * por punto y coma
	 * 
	 * @return String Versiones de la p�licula separada por punto y coma
	 */
	public String versionesToString() {
		if (versiones == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < versiones.length; i++) {
			versiones[i].replace(';', ':');
			sb.append(versiones[i]);
			if (i < versiones.length - 1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	/**
	 * Convierte los atributos del objeto Pelicula a una cadena de texto separada
	 * por punto y coma
	 * 
	 * @return String informaci�n del objeto pelicula separada por punto y coma
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(titulo);
		sb.append(';');
		sb.append(estudio);
		sb.append(';');
		sb.append(estado);
		sb.append(';');
		sb.append(versionesToString());
		sb.append(';');
		sb.append(Double.toString(precio));
		sb.append(';');
		sb.append(clasificacion);
		sb.append(';');
		sb.append(anio);
		sb.append(';');
		sb.append(genero);
		sb.append(';');
		sb.append(fechaPublicacionDVD);
		sb.append(';');
		sb.append(Integer.toString(id));
		sb.append('\n');

		return sb.toString();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String[] getVersiones() {
		return versiones;
	}

	public void setVersiones(String[] versiones) {
		this.versiones = versiones;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFechaPublicacionDVD() {
		return fechaPublicacionDVD;
	}

	public void setFechaPublicacionDVD(String fechaPublicacionDVD) {
		this.fechaPublicacionDVD = fechaPublicacionDVD;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * M�todo que compara si el objeto pasado por par�metro es igual a este.
	 * Esta comparaci�n se ejecuta por medio del atributo id
	 */
	@Override
	public boolean igualQue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * M�todo que compara si el objeto pasado por par�metro es mayor o igual a este
	 * Esta comparaci�n se ejecuta por medio del atributo id
	 */
	@Override
	public boolean mayorIgualQue(Object arg0) {
		Pelicula p2 = (Pelicula) arg0;
		return id >= p2.id;
	}

	/**
	 * M�todo que compara si el objeto pasado por par�metro es mayor a este
	 * Esta comparaci�n se ejecuta por medio del atributo id
	 */
	@Override
	public boolean mayorQue(Object arg0) {
		Pelicula p2 = (Pelicula) arg0;
		return id > p2.id;
	}

	/**
	 * M�todo que compara si el objeto pasado por par�metro es menor o igual a este
	 * Esta comparaci�n se ejecuta por medio del atributo id
	 */
	@Override
	public boolean menorIgualQue(Object arg0) {
		Pelicula p2 = (Pelicula) arg0;
		return id <= p2.id;
	}

	/**
	 * M�todo que compara si el objeto pasado por par�metro es menor a este
	 * Esta comparaci�n se ejecuta por medio del atributo id
	 */
	@Override
	public boolean menorQue(Object arg0) {
		Pelicula p2 = (Pelicula) arg0;
		return id < p2.id;
	}
}
