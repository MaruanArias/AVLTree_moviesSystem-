package co.edu.unbosque.controller;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * Esta clase define la inicializaci�n del controlador de la aplicaci�n y la
 * ejecuci�n de la vista
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class Index {

	/**
	 * M�todo que instancia el controlador de la aplicaci�n y inicia la vista.
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
