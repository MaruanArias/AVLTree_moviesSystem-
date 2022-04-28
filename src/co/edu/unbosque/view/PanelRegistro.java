package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import co.edu.unbosque.style.*;


/**
 * Esta clase define los coponentes visuales del registro de usuario
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class PanelRegistro extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel etiquetaregistro;
	public JLabel etiquetaTitulo;
	public JLabel etiquetaEstudio;
	public JLabel etiquetaEstado;
	public JLabel etiquetaVersiones;
	public JLabel etiquetaPrecio;
	public JLabel etiquetaClasificasion;
	public JLabel etiquetaAno;	
	public JLabel etiquetaGenero;
	public JLabel etiquetaFechaPublicacion;
	public JLabel etiquetaiD;
	
	public JTextPlaceHolderRound titulo;
	public JTextPlaceHolderRound estudio;
	public JTextPlaceHolderRound estado;
	public JTextPlaceHolderRound versiones;
	public JTextPlaceHolderRound precio;
	public JTextPlaceHolderRound clasificasion;
	public JTextPlaceHolderRound ano;
	public JComboBox<String> comboGenero;
	public JDateChooser fechaPublicacion;
	public JTextPlaceHolderRound id;

	public RoundJButton enviar;
	public RoundJButton cancelar;

	/**
	 * Método constructor que crea los componentes de la clase y define sus atributos
	 */
	public PanelRegistro() {
		setBounds(100, 0, 1100, 510);
		setLayout(null);
		
		// Campo Combobox Genero
		String[] list = { "Genero ", "Drama", "Comedia", "Acción", "Aventura", "Terror", "Suspenso", "Romantico", "Musical", "Ciencia Ficción", "Western"  };
		comboGenero = new JComboBox<String>(list);
		comboGenero.setForeground(Color.WHITE);
		comboGenero.setBounds(360, 450, 150, 20);
		comboGenero.setFont(new Font("tahoma", Font.BOLD, 15));
		comboGenero.setEditable(true);
        ((JTextField) comboGenero.getEditor().getEditorComponent()).setBackground(Color.BLACK);
        ((JTextField) comboGenero.getEditor().getEditorComponent()).setForeground(Color.WHITE);
		add(comboGenero); 
		// Etiquetas
		
		// Etiqueta Registro
		etiquetaregistro = new JLabel("REGISTRO");
		etiquetaregistro.setBounds(500, 10, 400, 30);
		etiquetaregistro.setBackground(Color.BLACK);
		etiquetaregistro.setFont(new Font("Arial", Font.BOLD, 25));
		add(etiquetaregistro);
		
		// Etiqueta Titulo
		etiquetaTitulo = new JLabel("Titulo");
		etiquetaTitulo.setBounds(400, 70, 100, 30);
		etiquetaTitulo.setBackground(Color.BLACK);
		etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaTitulo);
		
		// Etiqueta Estudio
		etiquetaEstudio = new JLabel("Estudio");
		etiquetaEstudio.setBounds(700, 70, 100, 30);
		etiquetaEstudio.setBackground(Color.BLACK);
		etiquetaEstudio.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaEstudio);
		
		// Etiqueta versiones
		etiquetaVersiones = new JLabel("Versiones");
		etiquetaVersiones.setBounds(390, 150, 100, 30);
		etiquetaVersiones.setBackground(Color.BLACK);
		etiquetaVersiones.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaVersiones);
		
		// Etiqueta precio
		etiquetaPrecio = new JLabel("Precio");
		etiquetaPrecio.setBounds(705, 150, 100, 30);
		etiquetaPrecio.setBackground(Color.BLACK);
		etiquetaPrecio.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaPrecio);
		
		// Etiqueta clasificación
		etiquetaClasificasion = new JLabel("Clasificacion");
		etiquetaClasificasion.setBounds(385, 240, 100, 30);
		etiquetaClasificasion.setBackground(Color.BLACK);
		etiquetaClasificasion.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaClasificasion);
		
		// Etiqueta Año
		etiquetaAno = new JLabel("Año");
		etiquetaAno.setBounds(715, 240, 100, 30);
		etiquetaAno.setBackground(Color.BLACK);
		etiquetaAno.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaAno);
		
		// Etiqueta  Genero
		etiquetaGenero = new JLabel("Genero");
		etiquetaGenero.setBounds(400, 410, 100, 30);
		etiquetaGenero.setBackground(Color.BLACK);
		etiquetaGenero.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaGenero);
		
		// Etiqueta  Id
		etiquetaiD = new JLabel("Id");
		etiquetaiD.setBounds(720, 330, 100, 30);
		etiquetaiD.setBackground(Color.BLACK);
		etiquetaiD.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaiD);
		
		// Etiqueta  Fecha Publicación
		etiquetaFechaPublicacion = new JLabel("Fecha de Publicación");
		etiquetaFechaPublicacion.setBounds(650, 410, 200, 20);
		etiquetaFechaPublicacion.setBackground(Color.BLACK);
		etiquetaFechaPublicacion.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaFechaPublicacion);
		
		// Etiqueta  Estado
		etiquetaEstado = new JLabel("Estado");
		etiquetaEstado.setBounds(400, 330, 200, 30);
		etiquetaEstado.setBackground(Color.BLACK);
		etiquetaEstado.setFont(new Font("Arial", Font.BOLD, 15));
		add(etiquetaEstado);
		
		// Campos 

		// Campo fechaPublicación  
		
		fechaPublicacion = new JDateChooser();
		fechaPublicacion.setBounds(650, 450, 150, 20);
		fechaPublicacion.setOpaque(true);
		fechaPublicacion.setBackground(Color.WHITE);
		fechaPublicacion.setMaxSelectableDate(new Date());
		add(fechaPublicacion);
		
		// Campo titulo
		titulo = new JTextPlaceHolderRound(15);
		titulo.setBounds(300, 100, 250, 30);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.BLACK);
		titulo.setOpaque(false);
		add(titulo);

		// Campo estudio
		estudio = new JTextPlaceHolderRound(15);
		estudio.setBounds(600, 100, 250, 30);
		estudio.setHorizontalAlignment(SwingConstants.CENTER);
		estudio.setForeground(Color.BLACK);
		estudio.setOpaque(false);
		add(estudio);
		
		// Campo estado
		estado = new JTextPlaceHolderRound(15);
		estado.setBounds(300, 360, 250, 30);
		estado.setHorizontalAlignment(SwingConstants.CENTER);
		estado.setForeground(Color.BLACK);
		estado.setOpaque(false);
		add(estado);
		
		// Campo Id
		id = new JTextPlaceHolderRound(15);
		id.setBounds(600, 360, 250, 30);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setForeground(Color.BLACK);
		id.setOpaque(false);
		add(id);
		// Campo Versiones
		versiones = new JTextPlaceHolderRound(15);
		versiones.setBounds(300, 180, 250, 30);
		versiones.setHorizontalAlignment(SwingConstants.CENTER);
		versiones.setForeground(Color.BLACK);
		versiones.setOpaque(false);
		add(versiones);

		// Campo precio
		precio = new JTextPlaceHolderRound(15);
		precio.setBounds(600, 180, 250, 30);
		precio.setHorizontalAlignment(SwingConstants.CENTER);
		precio.setForeground(Color.BLACK);
		precio.setOpaque(false);
		add(precio);
		
		// Campo clasificación
		clasificasion = new JTextPlaceHolderRound(15);
		clasificasion.setBounds(300, 270, 250, 30);
		clasificasion.setHorizontalAlignment(SwingConstants.CENTER);
		clasificasion.setForeground(Color.BLACK);
		clasificasion.setOpaque(false);
		add(clasificasion);
		
		// Campo Año
		ano = new JTextPlaceHolderRound(15);
		ano.setBounds(600, 270, 250, 30);
		ano.setHorizontalAlignment(SwingConstants.CENTER);
		ano.setForeground(Color.BLACK);
		ano.setOpaque(false);
		add(ano);
		
		// Boton Enviar
		enviar = new RoundJButton("Enviar");
		enviar.setBounds(850, 460, 100, 30);
		enviar.setHorizontalAlignment(SwingConstants.CENTER);
		enviar.setBorderPainted(false);
		enviar.setContentAreaFilled(false);
		enviar.setFocusPainted(false);
		enviar.setForeground(Color.WHITE);
		enviar.setBackground(Color.BLACK);
		enviar.setOpaque(false);
		enviar.addMouseListener(this);
		add(enviar);


	}	
	/**
	 * Método que limpia la información de los campos de texto
	 */
	public void limpiarFormulario() {
		
		titulo.setText("");
		estudio.setText("");
		versiones.setText("");
		precio.setText("");
		clasificasion.setText("");
		ano.setText("");
		id.setText("");
		estado.setText("");
		fechaPublicacion.setDate(null);
		comboGenero.setSelectedIndex(0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new GradientPaint(200, 0, Color.WHITE, 200, 430, Color.WHITE));
		g2d.fillRect(0, 0, 1000, 600);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (this.enviar == e.getSource()) {
			enviar.setForeground(Color.GREEN);
			enviar.setFont(new Font("Arial", Font.BOLD, 15));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (this.enviar == e.getSource()) {
			enviar.setForeground(Color.WHITE);
			enviar.setFont(new Font("Arial", Font.BOLD, 12));
		}
	}
	


	
	public int getId() {
		int id = -1;

		try {
			id = Integer.parseInt(this.id.getText());
		} catch (NumberFormatException e) {
		}

		return id;
	}

	public String getTitle() {
		return titulo.getText();
	}

	public String getStudio() {
		return estudio.getText();
	}

	public String getStatus() {
		return estado.getText();
	}

	public String[] getVersions() {
		return versiones.getText().split(",");
	}

	public double getPrice() {
		double precio = -1.0;

		try {
			precio = Double.parseDouble(this.precio.getText());
		} catch (NumberFormatException e) {
		}

		return precio;
	}

	public String getClasification() {
		return clasificasion.getText();
	}

	public int getYear() {
		int year = -1;

		try {
			year = Integer.parseInt(ano.getText());
		} catch (NumberFormatException e) {
		}

		return year;
	}

	public String getGenre() {
		return comboGenero.getSelectedItem().toString();
	}
 /**
  * Retorna la fecha de publicación en el formato dd/MM/yyyy
  * @return f.format(fecha);
  */
	public String getDvdPublicationDate() {
	    Date fecha = fechaPublicacion.getDate();
        DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
       
		return  f.format(fecha);
	}
}
