//http://www.jc-mouse.net/java/anade-un-placeholder-a-un-jtextfield
package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import co.edu.unbosque.style.*;

/**
 * Esta clase define el esquema para el ingrese de información
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class PanelMenuPrincipal extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private JLabel etiquetaTitulo;
	private JLabel etiquetaImgLogo;
	public JLabel etiquetaRespuesta;
	public RoundJButton btnListaPeliculas;
	public RoundJButton btnRegistrar;
	public RoundJButton btnEliminar;
	public RoundJButton btnEditar;

	/**
	 * Método constructor de la clase el cual define los elementos de la clase
	 */
	public PanelMenuPrincipal() {

		setBounds(0, 0, 320, 600);
		setLayout(null);

//		JLabel

		etiquetaTitulo = new JLabel("Tv Bosque");
		etiquetaTitulo.setForeground(Color.WHITE);
		etiquetaTitulo.setBounds(75, 120, 200, 35);
		etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 35));
		add(etiquetaTitulo);

		etiquetaRespuesta = new JLabel();
		etiquetaRespuesta.setForeground(Color.green);
		etiquetaRespuesta.setFont(new Font("Tahoma", Font.BOLD, 10));
		etiquetaRespuesta.setBounds(10, 380, 280, 40);
		etiquetaRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		add(etiquetaRespuesta);

//		JLabel para las imagenes

		ImageIcon imagenLogo = new ImageIcon(getClass().getResource("/co/edu/unbosque/imagenes/logo.png"));
		etiquetaImgLogo = new JLabel();
		etiquetaImgLogo.setIcon(new ImageIcon(imagenLogo.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		etiquetaImgLogo.setBounds(110, -140, 425, 400);
		add(etiquetaImgLogo);

//		Botones 

		btnListaPeliculas = new RoundJButton("Lista Películas");
		btnListaPeliculas.setBounds(75, 200, 180, 35);
		btnListaPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaPeliculas.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaPeliculas.setBorderPainted(false);
		btnListaPeliculas.setContentAreaFilled(false);
		btnListaPeliculas.setFocusPainted(false);
		btnListaPeliculas.setForeground(Color.black);
		btnListaPeliculas.setBackground(new Color(255, 254, 254));
		btnListaPeliculas.setOpaque(false);
		btnListaPeliculas.addMouseListener(this);
		add(btnListaPeliculas);

		btnRegistrar = new RoundJButton("Registro");
		btnRegistrar.setBounds(75, 270, 180, 35);
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setContentAreaFilled(false);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setForeground(Color.black);
		btnRegistrar.setBackground(new Color(255, 254, 254));
		btnRegistrar.setOpaque(false);
		btnRegistrar.addMouseListener(this);
		add(btnRegistrar);

		btnEliminar = new RoundJButton("Eliminar");
		btnEliminar.setBounds(75, 340, 180, 35);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setForeground(Color.black);
		btnEliminar.setBackground(new Color(255, 254, 254));
		btnEliminar.setOpaque(false);
		btnEliminar.addMouseListener(this);
		add(btnEliminar);

		btnEditar = new RoundJButton("Editar");
		btnEditar.setBounds(75, 410, 180, 35);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditar.setBorderPainted(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setFocusPainted(false);
		btnEditar.setForeground(Color.black);
		btnEditar.setBackground(new Color(255, 254, 254));
		btnEditar.setOpaque(false);
		btnEditar.addMouseListener(this);
		add(btnEditar);

	}

	/**
	 * Metodo usado para hacer un degrade para el background de un JPanel
	 * 
	 * @param g Componente gráfico a modificar
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new GradientPaint(200, 0, Color.black, 200, 430, Color.black));
		g2d.fillRect(0, 0, 325, 600);

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (this.btnListaPeliculas == arg0.getSource()) {
			btnListaPeliculas.setFont(new Font("Tahoma", Font.BOLD, 17));
			btnListaPeliculas.setBackground(new Color(182, 182, 182));

		} else if (this.btnRegistrar == arg0.getSource()) {
			btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
			btnRegistrar.setBackground(new Color(182, 182, 182));

		} else if (this.btnEliminar == arg0.getSource()) {
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 17));
			btnEliminar.setBackground(new Color(182, 182, 182));

		} else if (this.btnEditar == arg0.getSource()) {
			btnEditar.setFont(new Font("Tahoma", Font.BOLD, 17));
			btnEditar.setBackground(new Color(182, 182, 182));
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		btnListaPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaPeliculas.setForeground(Color.black);
		btnListaPeliculas.setBackground(new Color(255, 254, 254));

		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.setForeground(Color.black);
		btnRegistrar.setBackground(new Color(255, 254, 254));

		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminar.setForeground(Color.black);
		btnEliminar.setBackground(new Color(255, 254, 254));
		
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.setForeground(Color.black);
		btnEditar.setBackground(new Color(255, 254, 254));

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
