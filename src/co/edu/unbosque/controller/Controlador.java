package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFileChooser;
import co.edu.unbosque.model.ManejadorPelicula;
import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.view.VistaPrincipal;

/**
 * Esta clase define la accesibilidad al modelo, datos y vista del programa.
 * Controla la navegación y transferencia de datos entre las capas Implementa la
 * interfaz ActionListener para los escuchadores sobre las acciones sobre los
 * botones de la aplicación.
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class Controlador implements ActionListener {
	/**
	 * Declaración de atributos del controlador
	 */
	private VistaPrincipal ventana;
	private ManejadorPelicula manejadorPelicula;
	private File dataSet;

	/**
	 * Contructor propio de la clase. Inicializa los atributos propios de la clase.
	 * Asigna un escuchador a los botones de la vista para la navegación.
	 */
	public Controlador() {
		this.manejadorPelicula = new ManejadorPelicula();
		ventana = new VistaPrincipal();

		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				formWindowClosing();
				System.exit(0);
			}
		});
		ventana.panelMenuPrincipal.btnListaPeliculas.addActionListener(this);
		ventana.panelMenuPrincipal.btnRegistrar.addActionListener(this);
		ventana.panelMenuPrincipal.btnEliminar.addActionListener(this);
		ventana.panelMenuPrincipal.btnEditar.addActionListener(this);
		ventana.panelTabla.botonCargue.addActionListener(this);
		ventana.panelRegistro.enviar.addActionListener(this);
		ventana.panelEditar.buscar.addActionListener(this);
		ventana.panelEditar.enviar.addActionListener(this);
		ventana.panelEliminar.botonBorrar.addActionListener(this);
		ventana.panelTabla.botonFiltrar.addActionListener(this);
		ventana.panelTabla.botonActualizarArchivo.addActionListener(this);

	}

	/**
	 * Método que inicia las vistas del programa. Activa vista principal e inactiva
	 * las secundarias.
	 */
	public void iniciarVista() {

		this.ventana.setTitle("TV BOSQUE");
		this.ventana.setLocationRelativeTo(null);
		this.ventana.setVisible(true);

		ventana.panelMenuPrincipal.setVisible(true);
		ventana.panelTabla.setVisible(true);
		ventana.panelTabla.agregarTabla(null);
		ventana.panelRegistro.setVisible(false);
		ventana.panelEditar.setVisible(false);
		ventana.panelEliminar.setVisible(false);
		ventana.panelMenuPrincipal.btnListaPeliculas.setEnabled(false);
		ventana.panelMenuPrincipal.btnEliminar.setEnabled(true);
		ventana.panelMenuPrincipal.btnRegistrar.setEnabled(true);
	}

	/**
	 * Método de la interfaz ActionListener. Este método permite capturar las
	 * acciones realizadas por el usuario en la interfaz. Mapea la nevegación y
	 * acciones a ejecutar por cada botón oprimido.
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (ventana.panelMenuPrincipal.btnListaPeliculas == evento.getSource()) {

			ventana.panelMenuPrincipal.setVisible(true);
			ventana.panelTabla.setVisible(true);

			ventana.panelRegistro.setVisible(false);
			ventana.panelEliminar.setVisible(false);
			ventana.panelEditar.setVisible(false);
			ventana.panelMenuPrincipal.btnListaPeliculas.setEnabled(false);
			ventana.panelMenuPrincipal.btnEliminar.setEnabled(true);
			ventana.panelMenuPrincipal.btnRegistrar.setEnabled(true);
			ventana.panelMenuPrincipal.btnEditar.setEnabled(true);

			Pelicula[] peliculas = this.manejadorPelicula.getPeliculasArbolAvl();
			ventana.panelTabla.agregarTabla(peliculas);
			ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");

		} else if (ventana.panelMenuPrincipal.btnRegistrar == evento.getSource()) {

			ventana.panelMenuPrincipal.setVisible(true);
			ventana.panelTabla.setVisible(false);
			ventana.panelRegistro.setVisible(true);
			ventana.panelEliminar.setVisible(false);
			ventana.panelEditar.setVisible(false);
			ventana.panelMenuPrincipal.btnListaPeliculas.setEnabled(true);
			ventana.panelMenuPrincipal.btnEliminar.setEnabled(true);
			ventana.panelMenuPrincipal.btnRegistrar.setEnabled(false);
			ventana.panelMenuPrincipal.btnEditar.setEnabled(true);
			if (ventana.panelRegistro.enviar == evento.getSource()) {
				ventana.panelRegistro.limpiarFormulario();
			}

		} else if (ventana.panelMenuPrincipal.btnEliminar == evento.getSource()) {

			ventana.panelMenuPrincipal.setVisible(true);
			ventana.panelTabla.setVisible(false);
			ventana.panelRegistro.setVisible(false);
			ventana.panelEditar.setVisible(false);
			ventana.panelEliminar.setVisible(true);
			ventana.panelMenuPrincipal.btnListaPeliculas.setEnabled(true);
			ventana.panelMenuPrincipal.btnEliminar.setEnabled(false);
			ventana.panelMenuPrincipal.btnRegistrar.setEnabled(true);
			ventana.panelMenuPrincipal.btnEditar.setEnabled(true);

			Pelicula[] peliculas = this.manejadorPelicula.getPeliculasArbolAvl();
			ventana.panelEliminar.agregarTabla(peliculas);

		} else if (ventana.panelMenuPrincipal.btnEditar == evento.getSource()) {

			ventana.panelMenuPrincipal.setVisible(true);
			ventana.panelTabla.setVisible(false);
			ventana.panelRegistro.setVisible(false);
			ventana.panelEliminar.setVisible(false);
			ventana.panelEditar.setVisible(true);
			ventana.panelMenuPrincipal.btnListaPeliculas.setEnabled(true);
			ventana.panelMenuPrincipal.btnEliminar.setEnabled(true);
			ventana.panelMenuPrincipal.btnRegistrar.setEnabled(true);
			ventana.panelMenuPrincipal.btnEditar.setEnabled(false);
			ventana.panelEditar.enviar.setEnabled(false);
			ventana.panelEditar.limpiarFormulario();
			ventana.panelEditar.id.setEnabled(true);

		} else if (ventana.panelTabla.botonCargue == evento.getSource()) {
			JFileChooser fileChooser;
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			fileChooser.showOpenDialog(fileChooser);
			dataSet = fileChooser.getSelectedFile();

			try {

				Pelicula[] tmPeliculas = manejadorPelicula.cargarPeliculasDesdeArchivo(dataSet);
				ventana.panelTabla.cargarPeliculasTabla(tmPeliculas);
				ventana.panelTabla.numRegistros.setText(tmPeliculas.length + " Registros");
			} catch (Exception e) {
				ventana.mostrarMensajeError("No se pudo cargar los registros.", "Error Nigga");
				e.printStackTrace();
			}
		} else if (ventana.panelRegistro.enviar == evento.getSource()) {
			Pelicula tmpPelicula = validarPeliculaRegistrar();
			if (tmpPelicula != null) {
				Pelicula[] peliculas = { tmpPelicula };

				try {
					manejadorPelicula.guardarPelicula(peliculas[0]);
					ventana.panelRegistro.limpiarFormulario();
					ventana.mostrarMensajeInformacion("Registro exitoso", "Lo lograste a mimir");
				} catch (Exception e) {
					ventana.mostrarMensajeError("No se pudo guardar el registro.", "Error Nigga");
					e.printStackTrace();
				}

			}
		} else if (ventana.panelEditar.buscar == evento.getSource()) {
			int id = ventana.panelEditar.getId();
			Pelicula tmpPelicula = manejadorPelicula.getPeliculaPorID(id);
			if (tmpPelicula != null) {
				try {
					ventana.panelEditar.llenarFormulario(tmpPelicula);
					ventana.panelEditar.id.setEnabled(false);
					ventana.panelEditar.enviar.setEnabled(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				ventana.panelEditar.limpiarFormulario();
				ventana.mostrarMensajeInformacion("Este ID no existe, confirme la información",
						"What's up Nigga. Where is my ID?");
			}
		} else if (ventana.panelEditar.enviar == evento.getSource()) {
			Pelicula tmpPelicula = validarPeliculaEditar();
			if (tmpPelicula != null) {
				int[] ids = { tmpPelicula.getId() };

				try {
					manejadorPelicula.eliminarPeliculas(ids);
					Pelicula[] peliculas = { tmpPelicula };
					manejadorPelicula.guardarPelicula(peliculas[0]);
					ventana.panelEditar.limpiarFormulario();
					ventana.mostrarMensajeInformacion("Cambio Exitoso", "Lo lograste a mimir");
				} catch (Exception e) {
					ventana.mostrarMensajeError("No se pudo actualizar el registro.", "Error Nigga");
					e.printStackTrace();
				}
			}
		} else if (ventana.panelEliminar.botonBorrar == evento.getSource()) {

			try {
				int[] ids = retornarIdsPeliculas();
				if (ids != null) {
					String estado = manejadorPelicula.eliminarPeliculas(ids);
					Pelicula[] peliculas = this.manejadorPelicula.getPeliculasArbolAvl();
					ventana.panelEliminar.agregarTabla(peliculas);
					ventana.mostrarMensajeInformacion("Eliminación exitosa :v.\n" + estado, "Good Job Nigga");
				} else {
					ventana.mostrarMensajeAdvertencia("No fue posible eliminar los IDs ingresados",
							"Error my little dog");
				}

			} catch (Exception e) {
				ventana.mostrarMensajeError("No se pudo eliminar los registros.", "Error Nigga");
				e.printStackTrace();
			}
		} else if (ventana.panelTabla.botonFiltrar == evento.getSource()) {
			try {
				String tipoFiltro = ventana.panelTabla.comboFiltrar.getSelectedItem().toString();
				String[] valor = ventana.panelTabla.campoFiltrar.getText().split(",");
				switch (tipoFiltro) {
				case "Periodo Debut":
					int inicio = Integer.parseInt(valor[0].trim());
					int fin = Integer.parseInt(valor[1].trim());

					if (fin >= inicio && valor.length == 2) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasPorRangoDebut(inicio, fin);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Periodo inválido", "Sorry man");
					}
					break;
				case "Género":
					if (valor.length > 0) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasPorGenero(valor);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Debe ingresar al menos un género", "Sorry man");
					}
					break;
				case "Título":
					if (valor.length == 1) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasPorTitulo(valor[0]);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Solo se admite 1 título a filtrar", "Sorry man");
					}
					break;
				case "Costoso x Genero":
					if (valor.length == 1) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasCostosasPorGenero(valor[0]);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Solo se admite este género a filtrar", "Sorry man");
					}
					break;
				case "Clasificación":
					if (valor.length > 0) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasPorClasificacion(valor);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Debe ingresar al menos una clasificación", "Sorry man");
					}
					break;
				case "Versiones":
					if (valor.length > 0) {
						Pelicula[] peliculas = this.manejadorPelicula.getPeliculasPorVersion(valor);
						ventana.panelTabla.agregarTabla(peliculas);
						ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					} else {
						ventana.mostrarMensajeError("Debe ingresar al menos una clasificación", "Sorry man");
					}
					break;
				case "Todo":
					Pelicula[] peliculas = this.manejadorPelicula.getPeliculasArbolAvl();
					ventana.panelTabla.agregarTabla(peliculas);
					ventana.panelTabla.numRegistros.setText(peliculas.length + " Registros");
					break;
				default:
					ventana.mostrarMensajeInformacion("Seleccione un filtro válido", "¿Por qué es así?");
				}
			} catch (Exception e) {
				ventana.mostrarMensajeError("No se pudo filtrar los registros.", "Error Nigga, where is my filter?");
				e.printStackTrace();
			}
		} else if (ventana.panelTabla.botonActualizarArchivo == evento.getSource()) {
			this.formWindowClosing();
		}

	}

	/**
	 * Método que valida si el id pasado por paámetro existe en la estructura de
	 * datos o no
	 * 
	 * @param id Paámetro que representa el id a buscar en la estructura de datos.
	 * @return True si el id existe en la estructura de datos, false en caso
	 *         contrario
	 */
	public boolean idEnUso(int id) {
		if (manejadorPelicula.getPeliculaPorID(id) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Método que solicita a través de la vista el ingreso de ids especificos de los
	 * registros a eliminar de la estructura de datos. Lee los datos y los
	 * transforma en un array de enteros.
	 * 
	 * @return Un array de enteros con los ids ingresados, en caso contrario, Nulo
	 *         si no ingresa un valor
	 */
	public int[] retornarIdsPeliculas() {
		String[] strIds = ventana
				.entradaDato("Ingrese los IDs de las peliculas a eliminar\nRecuerde separarlos por coma (,)")
				.split(",");
		if (strIds.length == 0) {
			return null;
		}

		int[] ids = new int[strIds.length];
		for (int i = 0; i < ids.length; i++) {
			try {
				ids[i] = Integer.parseInt(strIds[i].trim());
			} catch (NumberFormatException e) {
				ids[i] = -1;
				ventana.mostrarMensajeError("Error, " + strIds[i] + " no es un Id valido", "What's up my little dog");
			}
		}

		return ids;
	}

	/**
	 * Método que lee la información del formulario de registro. Valida que la
	 * información ingresada en cada campo corresponda al tipo de dato del atributo
	 * que representa del modelo.
	 * 
	 * @return Un objeto de tipo Pelicula construido con la información ingresada.
	 *         En caso de que algún dato no cumpla con las restricciones de tipo,
	 *         retornará nulo.
	 */
	public Pelicula validarPeliculaRegistrar() {
		if (ventana.panelRegistro.getId() < 0) {
			ventana.mostrarMensajeError("ID invalido", "Error Nigga");
			return null;
		}

		if (idEnUso(ventana.panelRegistro.getId())) {
			ventana.mostrarMensajeError("ID. " + ventana.panelRegistro.getId() + " ya esta en uso ", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getTitle().isEmpty()) {
			ventana.mostrarMensajeError("Campo título vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getStudio().isEmpty()) {
			ventana.mostrarMensajeError("Campo estudio vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getStatus().isEmpty()) {
			ventana.mostrarMensajeError("Campo estado vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getGenre().isEmpty()) {
			ventana.mostrarMensajeError("Campo género vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getPrice() < 0) {
			ventana.mostrarMensajeError("Campo Precio invalido debe ser un número", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getClasification().isEmpty()) {
			ventana.mostrarMensajeError("Campo clasificación vacío", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getYear() < 0) {
			ventana.mostrarMensajeError("Campo Año invalido, debe ser un número", "Error Nigga");
			return null;
		}

		if (ventana.panelRegistro.getDvdPublicationDate().isEmpty()) {
			ventana.mostrarMensajeError("Campo Fecha vacío", "Error Nigga");
			return null;
		}

		String[] dateParts = ventana.panelRegistro.getDvdPublicationDate().split("/");
		if (dateParts.length != 3) {
			ventana.mostrarMensajeError("Campo Fecha Publicación\\nFormato invalido ingrese dd/mm/aaaa", "Error Nigga");
			return null;
		}

		if (dateParts[0].length() != 2 || dateParts[1].length() != 2 || dateParts[2].length() != 4) {
			ventana.mostrarMensajeError("Campo Fecha Publicación\\nFormato invalido ingrese dd/mm/aaaa", "Error Nigga");
			return null;
		}

		int day, month;
		try {
			day = Integer.parseInt(dateParts[0]);
			if (day < 1 || day > 31) {
				throw new NumberFormatException();
			}

			month = Integer.parseInt(dateParts[1]);
			if (month < 1 || month > 12) {
				throw new NumberFormatException();
			}

			Integer.parseInt(dateParts[2]);
		} catch (NumberFormatException e) {
			ventana.mostrarMensajeError("Campo Fecha Publicación eror", "Error Nigga");
			return null;
		}

		Pelicula pelicula = new Pelicula();
		pelicula.setId(ventana.panelRegistro.getId());
		pelicula.setTitulo(ventana.panelRegistro.getTitle());
		pelicula.setEstudio(ventana.panelRegistro.getStudio());
		pelicula.setEstado(ventana.panelRegistro.getStatus());
		pelicula.setVersiones(ventana.panelRegistro.getVersions());
		pelicula.setPrecio(ventana.panelRegistro.getPrice());
		pelicula.setClasificacion(ventana.panelRegistro.getClasification());
		pelicula.setAnio(ventana.panelRegistro.getYear());
		pelicula.setGenero(ventana.panelRegistro.getGenre());
		pelicula.setFechaPublicacionDVD(ventana.panelRegistro.getDvdPublicationDate());

		return pelicula;
	}

	/**
	 * Método que lee la información del formulario de editar. Valida que la
	 * información ingresada en cada campo corresponda al tipo de dato del atributo
	 * que representa del modelo.
	 * 
	 * @return Un objeto de tipo Pelicula construido con la información ingresada.
	 *         En caso de que algún dato no cumpla con las restricciones de tipo,
	 *         retornará nulo.
	 */
	public Pelicula validarPeliculaEditar() {
		if (ventana.panelEditar.getId() < 0) {
			ventana.mostrarMensajeError("ID invalido", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getTitle().isEmpty()) {
			ventana.mostrarMensajeError("Campo título vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getStudio().isEmpty()) {
			ventana.mostrarMensajeError("Campo estudio vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getStatus().isEmpty()) {
			ventana.mostrarMensajeError("Campo estado vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getGenero().isEmpty()) {
			ventana.mostrarMensajeError("Campo género vacío ", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getPrice() < 0) {
			ventana.mostrarMensajeError("Campo Precio invalido debe ser un número", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getClasification().isEmpty()) {
			ventana.mostrarMensajeError("Campo clasificación vacío", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getYear() < 0) {
			ventana.mostrarMensajeError("Campo Año invalido, debe ser un número", "Error Nigga");
			return null;
		}

		if (ventana.panelEditar.getDvdPublicationDate().isEmpty()) {
			ventana.mostrarMensajeError("Campo Fecha vacío", "Error Nigga");
			return null;
		}

		String[] dateParts = ventana.panelEditar.getDvdPublicationDate().split("/");
		if (dateParts.length != 3) {
			ventana.mostrarMensajeError("Campo Fecha Publicación\\nFormato invalido ingrese dd/mm/aaaa", "Error Nigga");
			return null;
		}

		if (dateParts[0].length() != 2 || dateParts[1].length() != 2 || dateParts[2].length() != 4) {
			ventana.mostrarMensajeError("Campo Fecha Publicación\\nFormato invalido ingrese dd/mm/aaaa", "Error Nigga");
			return null;
		}

		int day, month;
		try {
			day = Integer.parseInt(dateParts[0]);
			if (day < 1 || day > 31) {
				throw new NumberFormatException();
			}

			month = Integer.parseInt(dateParts[1]);
			if (month < 1 || month > 12) {
				throw new NumberFormatException();
			}

			Integer.parseInt(dateParts[2]);
		} catch (NumberFormatException e) {
			ventana.mostrarMensajeError("Campo Fecha Publicación eror", "Error Nigga");
			return null;
		}

		Pelicula pelicula = new Pelicula();
		pelicula.setId(ventana.panelEditar.getId());
		pelicula.setTitulo(ventana.panelEditar.getTitle());
		pelicula.setEstudio(ventana.panelEditar.getStudio());
		pelicula.setEstado(ventana.panelEditar.getStatus());
		pelicula.setVersiones(ventana.panelEditar.getVersions());
		pelicula.setPrecio(ventana.panelEditar.getPrice());
		pelicula.setClasificacion(ventana.panelEditar.getClasification());
		pelicula.setAnio(ventana.panelEditar.getYear());
		pelicula.setGenero(ventana.panelEditar.getGenero());
		pelicula.setFechaPublicacionDVD(ventana.panelEditar.getDvdPublicationDate());

		return pelicula;
	}

	/**
	 * Este método ejecuta la funcionalidad que guarda la información actual que
	 * posee la estructura de datos en el dataSet cargado al sistema
	 */
	private void formWindowClosing() {
		try {
			manejadorPelicula.actualizarArchivo(dataSet);
			ventana.mostrarMensajeInformacion("Archivo actualizado.\nHasta La próxima xd", "bai");
		} catch (IOException e) {
			ventana.mostrarMensajeError("No fue posible actualizar el archivo :'v", "Sorry man");
			e.printStackTrace();
		} catch (Exception e) {
			ventana.mostrarMensajeError("No fue posible actualizar el archivo :'v", "Sorry man");
		}
	}
}
