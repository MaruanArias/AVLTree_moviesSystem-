//http://www.jc-mouse.net/java/anade-un-placeholder-a-un-jtextfield
package co.edu.unbosque.style;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Esta clase define el estilo de los campos JTextField
 * 
 * @author Fabian A. Gonzalez M.
 * @author Maruan E. Arias C.
 * @author John A. Bedoya R.
 * @author Antonio J. Mata R.
 * @version: 1.0
 */
public class JRoundText extends JTextField {

	private static final long serialVersionUID = 1L;
	private Shape shape;
	private Dimension d = new Dimension(200, 32);
	private String placeholder = "";
	private Color phColor = new Color(255, 255, 255);
	private boolean band = true;

	/**
	 * Método constructor que genera los atributos del campo
	 * 
	 * @param size Tamaño del campo
	 */
	public JRoundText(int size) {
		super(size);

		setOpaque(false);

		setHorizontalAlignment(SwingConstants.LEFT);

		setSize(d);
		setPreferredSize(d);
		setVisible(true);
		setMargin(new Insets(10, 6, 3, 6));

		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				band = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				band = (getText().length() > 0) ? false : true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

		});
	}

	/**
	 * Método que retorna el valor del placeHolder
	 * 
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * Método que cambia el valor del atributo placeHolder
	 * 
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	/**
	 * Método que retorna el valor del atributo phColor
	 * 
	 * @return the phColor
	 */
	public Color getPhColor() {
		return phColor;
	}

	/**
	 * Método cambia el valor del atributo phColor
	 * 
	 * @param phColor the phColor to set
	 */
	public void setPhColor(Color phColor) {
		this.phColor = phColor;
	}

	/**
	 * Método que modifica el color el placeHolder y dibuja el texto
	 * 
	 * @param g Componente gráfico a modificar
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// color de placeholder
		g.setColor(new Color(phColor.getRed(), phColor.getGreen(), phColor.getBlue(), 90));
		// dibuja texto
		g.drawString((band) ? placeholder : "", getMargin().left, (getSize().height) / 2 + getFont().getSize() / 2);

	}

	/**
	 * Método que dibuja el borde del placeHolder
	 * @param g Componente gráfico a modificar
	 */
	protected void paintBorder(Graphics g) {
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	}

	/**
	 * Método que evalua si las coordenadas están dentro de la forma y la ajusta
	 * 
	 * @param x Coordenada x
	 * @param y Coordenada y
	 */
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}
		return shape.contains(x, y);
	}
}