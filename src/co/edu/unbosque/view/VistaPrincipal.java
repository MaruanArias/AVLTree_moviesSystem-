package co.edu.unbosque.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PanelMenuPrincipal panelMenuPrincipal;
	public PanelEliminar panelEliminar;
	public PanelRegistro panelRegistro;
	public PanelEditar panelEditar;
	public PanelTabla panelTabla;

	public VistaPrincipal() {
		//Logo del Programa
		Image icon = new ImageIcon(getClass().getResource("/co/edu/unbosque/imagenes/icon.png")).getImage();
		setIconImage(icon);
		setSize(1100, 550);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panelMenuPrincipal = new PanelMenuPrincipal();
		panelMenuPrincipal.setVisible(true);

		this.panelRegistro = new PanelRegistro();
		panelRegistro.setVisible(false);

		this.panelEliminar = new PanelEliminar();
		panelEliminar.setVisible(false);

		this.panelTabla = new PanelTabla();
		panelTabla.setVisible(false);

		this.panelEditar = new PanelEditar();
		panelTabla.setVisible(false);

		add(panelMenuPrincipal);
		add(panelRegistro);
		add(panelEditar);
		add(panelEliminar);
		add(panelTabla);
	}

	/**
	 * Metodo para mostrar un mensaje de error
	 * 
	 * @param sms
	 * @param titulo
	 */
	public void mostrarMensajeError(String sms, String titulo) {
		JOptionPane.showMessageDialog(null, sms, titulo, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Metodo para mostrar un mensaje de advertencia
	 * 
	 * @param sms
	 * @param titulo
	 */
	public void mostrarMensajeAdvertencia(String sms, String titulo) {
		JOptionPane.showMessageDialog(null, sms, titulo, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Metodo para mostrar un mensaje de información
	 * 
	 * @param sms
	 * @param titulo
	 */
	public void mostrarMensajeInformacion(String sms, String titulo) {
		JOptionPane.showMessageDialog(null, sms, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Metodo para mostrar un mensaje en especifico
	 * @param sms
	 * @return
	 */
	public String entradaDato(String sms) {
		return JOptionPane.showInputDialog(sms);
	}

}