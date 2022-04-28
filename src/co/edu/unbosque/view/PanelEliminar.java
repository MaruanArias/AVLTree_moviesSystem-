package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.style.RoundJButton;


/**
 * Esta panel muestra la tabla que contiene los  datos de los usuarios
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class PanelEliminar extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public JScrollPane barra_Lateral;
	public JScrollPane sp;
	private JTable tabla;

	public RoundJButton botonBorrar;
	public DefaultTableModel modelo;
	public JLabel etiquetaEliminar;
	
	private Object[][] datos;

	/**
	 * Método contructor del panel
	
	 */
	public PanelEliminar() {
		setBounds(0, 0, 1100, 510);
		setLayout(null);

		botonBorrar = new RoundJButton("Seleccionar");
		botonBorrar.setBounds(950, 475, 120, 30);
		botonBorrar.setHorizontalAlignment(SwingConstants.CENTER);
		botonBorrar.setBorderPainted(false);
		botonBorrar.setContentAreaFilled(false);
		botonBorrar.setFocusPainted(false);
		botonBorrar.setForeground(Color.WHITE);
		botonBorrar.setBackground(Color.BLACK);
		botonBorrar.setOpaque(false);
		botonBorrar.addMouseListener(this);
		botonBorrar.setFont(new Font("Arial", Font.PLAIN, 11));
		add(botonBorrar);

		etiquetaEliminar = new JLabel("ELIMINAR");
		etiquetaEliminar.setForeground(Color.BLACK);
		etiquetaEliminar.setFont(new Font("Tahoma", Font.BOLD, 30));
		etiquetaEliminar.setBounds(550, -10, 280, 40);
		etiquetaEliminar.setHorizontalAlignment(SwingConstants.CENTER);

		add(etiquetaEliminar);
		
	}

	/**
	 * Metodo para agregar una table a la cual se le pasa como parametro una matriz
	 * de objetos
	 * @param datos
	 */
	public void agregarTabla(Pelicula[] peliculas) {
//La tabla si se estaba actualizando, lo que sucedia es que se agregaba como un nuevo componente debajo del primero creado.
//Se procede a validar si el componente existe, de ser así, se elimina
		if (isAncestorOf(sp))
			remove(sp);
		String columnas[] = {"Titulo","Estudio","Estado","Versiones","Precio","Clasificación","Año","Genero","Fecha de publicación","Id"};
		cargarPeliculasTabla(peliculas);
		modelo = new DefaultTableModel(datos, columnas);
		tabla = new JTable(modelo);
		DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
		alinear.setHorizontalAlignment(SwingConstants.CENTER);

		tabla.getColumnModel().getColumn(3).setCellRenderer(alinear);
		tabla.getColumnModel().getColumn(4).setCellRenderer(alinear);
		tabla.getColumnModel().getColumn(5).setCellRenderer(alinear);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setEnabled(false);

		sp = new JScrollPane(tabla);
		sp.setBounds(340, 30, 725, 440);
		add(sp);

	}
	
	public void cargarPeliculasTabla(Pelicula[] peliculas) {
		if (peliculas == null) {
			return;
		}
		datos = new String[peliculas.length][10];

		for (int i = 0; i < peliculas.length; i++) {
			
			
			if (peliculas[i] == null) {
				continue;
			}
			
			datos[i][0] = peliculas[i].getTitulo();
			datos[i][1] = peliculas[i].getEstudio();
			datos[i][2] = peliculas[i].getEstado();
			datos[i][3] = peliculas[i].versionesToString();

			if (peliculas[i].getPrecio() < 0) {
				datos[i][4] = "No Registra.";
			} else {
				datos[i][4] = Double.toString(peliculas[i].getPrecio());
			}

			datos[i][5] = peliculas[i].getClasificacion();

			if (peliculas[i].getAnio() < 0) {
				datos[i][6] = "No Registra.";
			} else {
				datos[i][6] = Integer.toString(peliculas[i].getAnio());
			}

			datos[i][7] = peliculas[i].getGenero();
			datos[i][8] = peliculas[i].getFechaPublicacionDVD();
			datos[i][9] = Integer.toString(peliculas[i].getId());

			
		}

		
	}
 
	/**
	 * Método que devuelve el modelo que se le pasa como parametro al JTbale
	 * @return El modelo
	 */
	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JTable getTabla() {
		return tabla;
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new GradientPaint(200, 0, Color.white, 200, 430, Color.white));
		g2d.fillRect(0, 0, 1100, 600);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (this.botonBorrar == e.getSource()) {
			botonBorrar.setForeground(Color.GREEN);
			botonBorrar.setFont(new Font("Arial", Font.BOLD, 15));
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (this.botonBorrar == e.getSource()) {
			botonBorrar.setForeground(Color.WHITE);
			botonBorrar.setFont(new Font("Arial", Font.BOLD, 12));
			
		}
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
