package co.edu.unbosque.controller;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * Esta clase define la inicialización del controlador de la aplicación y la
 * ejecución de la vista
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class Index {

	/**
	 * Método que instancia el controlador de la aplicación y inicia la vista.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
		UIManager.put("JTextField.background", new ColorUIResource(Color.WHITE));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.WHITE));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));

		Controlador controlador = new Controlador();
		controlador.iniciarVista();
	}

}
