package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.style.*;
/**
 * Esta panel muestra la tabla que contiene los  datos de los usuarios
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class PanelTabla extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public JTextArea area_Texto;
	public JScrollPane barra_Lateral;
	public JScrollPane sp;
	private JTable tabla;

	public RoundJButton botonFiltrar;
	public RoundJButton botonCargue;
	public RoundJButton botonActualizarArchivo;
	public JComboBox<String> comboFiltrar;
	public JButton botonFiltroSecundario;

	public JTextPlaceHolderRound campoFiltrar;
	public JTextPlaceHolderRound numRegistros;
	public DefaultTableModel modelo;
	public TableRowSorter<TableModel> ordenador;
	
	private String[][] datos;
	/**
	 * Método contructor del panel
	
	 */
	public PanelTabla() {
		setBounds(500, 300, 1100, 500);
		setLayout(null);
		//campo para filtrar
		campoFiltrar = new JTextPlaceHolderRound(15);
		campoFiltrar.setBounds(540, 0, 350, 30);
		campoFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		campoFiltrar.setForeground(Color.BLACK);
		campoFiltrar.setOpaque(false);
		campoFiltrar.setPlaceholder("Filtrar. Recuerde ingresar los datos seperados por coma (,)");
		add(campoFiltrar);
		
		//campo numero de Registros
		numRegistros = new JTextPlaceHolderRound(15);
		numRegistros.setBounds(490, 38, 200, 25);
		numRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		numRegistros.setForeground(Color.BLACK);
		numRegistros.setOpaque(false);
		numRegistros.setPlaceholder("0 registros");
		add(numRegistros);
		
		 //campo comboBox Tipo Filtro
		String[] list = { "Tipo Filtro ", "Periodo Debut", "Género", "Título", "Costoso x Genero","Clasificación", "Versiones", "Todo" };
		comboFiltrar = new JComboBox<String>(list);
		comboFiltrar.setForeground(Color.WHITE);
		comboFiltrar.setBounds(380, 7, 150, 20);
		comboFiltrar.setFont(new Font("tahoma", Font.BOLD, 14));
		comboFiltrar.setEditable(true);
        ((JTextField) comboFiltrar.getEditor().getEditorComponent()).setBackground(Color.BLACK);
        ((JTextField) comboFiltrar.getEditor().getEditorComponent()).setForeground(Color.WHITE);
		add(comboFiltrar); 
		
		 //boton Filtrar
		botonFiltrar = new RoundJButton("Filtrar");
		botonFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		botonFiltrar.setBorderPainted(false);
		botonFiltrar.setContentAreaFilled(false);
		botonFiltrar.setFocusPainted(false);
		botonFiltrar.setOpaque(false);
		botonFiltrar.addMouseListener(this);
		botonFiltrar.setForeground(Color.black);
		botonFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		botonFiltrar.setBackground(new Color(255, 255, 255));
		botonFiltrar.setBounds(380, 40, 100, 20);
		add(botonFiltrar);

        //boton Cargue Masivo
		botonCargue = new RoundJButton("Cargue Masivo");
		botonCargue.setBounds(925, 15, 130, 20);
		botonCargue.setHorizontalAlignment(SwingConstants.CENTER);
		botonCargue.setBorderPainted(false);
		botonCargue.setContentAreaFilled(false);
		botonCargue.setFocusPainted(false);
		botonCargue.setForeground(Color.black);
		botonCargue.setBackground(new Color(255, 255, 255));
		botonCargue.setOpaque(false);
		botonCargue.addMouseListener(this);
		botonCargue.setFont(new Font("Arial", Font.PLAIN, 11));
		add(botonCargue);
		
		// boton Actualizar CSV
		botonActualizarArchivo = new RoundJButton("Actualizar CSV");
		botonActualizarArchivo.setBounds(925, 40, 130, 20);
		botonActualizarArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		botonActualizarArchivo.setBorderPainted(false);
		botonActualizarArchivo.setContentAreaFilled(false);
		botonActualizarArchivo.setFocusPainted(false);
		botonActualizarArchivo.setForeground(Color.black);
		botonActualizarArchivo.setBackground(new Color(255, 255, 255));
		botonActualizarArchivo.setOpaque(false);
		botonActualizarArchivo.addMouseListener(this);
		botonActualizarArchivo.setFont(new Font("Arial", Font.PLAIN, 11));
		add(botonActualizarArchivo);

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
		sp.setBounds(340, 70, 725, 425);
		add(sp);

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
	
	/**
	 * Metodo que permite realizar el cargue de pelicas a la tabla las cuales van en un arrayList
	 * @param peliculas
	 */
	public void cargarPeliculasTabla(Pelicula[] peliculas) {
		if (peliculas == null) {
			return;
		}
		datos = new String[peliculas.length][10];
		modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);

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

			modelo.addRow(datos[i]);
			
		}

		tabla.setModel(modelo);
		modelo.fireTableDataChanged();
		tabla.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (this.botonFiltrar == e.getSource()) {
			botonFiltrar.setText("Filtrar");
			botonFiltrar.setForeground(Color.GREEN);
			botonFiltrar.setFont(new Font("Arial", Font.BOLD, 15));
			
		} else if (this.botonFiltroSecundario == e.getSource()) {
			botonFiltroSecundario.setText("Filtro Secundario");
			botonFiltroSecundario.setForeground(Color.GREEN);
			botonFiltroSecundario.setFont(new Font("Arial", Font.BOLD, 13));
			
		} else if(this.botonCargue == e.getSource()) {
			botonCargue.setText("Cargue Masivo");
			botonCargue.setForeground(Color.blue);
			botonCargue.setFont(new Font("Arial", Font.BOLD, 13));
		}else if(this.botonActualizarArchivo == e.getSource()) {
			botonActualizarArchivo.setText("Actualizar CSV");
			botonActualizarArchivo.setForeground(Color.blue);
			botonActualizarArchivo.setFont(new Font("Arial", Font.BOLD, 13));
			}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (this.botonFiltrar == e.getSource()) {
			botonFiltrar.setForeground(Color.BLACK);
			botonFiltrar.setFont(new Font("Arial", Font.BOLD, 12));
			
		} else if (this.botonFiltroSecundario == e.getSource()) {
			botonFiltroSecundario.setForeground(Color.WHITE);
			botonFiltroSecundario.setForeground(Color.WHITE);
			botonFiltroSecundario.setFont(new Font("Arial", Font.BOLD, 11));
			
		}else if(this.botonCargue == e.getSource()) {
			botonCargue.setForeground(Color.BLACK);
			botonCargue.setForeground(Color.BLACK);
			botonCargue.setFont(new Font("Arial", Font.BOLD, 11));
		}else if(this.botonActualizarArchivo == e.getSource()) {
			botonActualizarArchivo.setForeground(Color.BLACK);
			botonActualizarArchivo.setForeground(Color.BLACK);
			botonActualizarArchivo.setFont(new Font("Arial", Font.BOLD, 11));
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
