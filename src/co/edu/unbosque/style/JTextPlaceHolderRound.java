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

public class JTextPlaceHolderRound extends JTextField {

	private static final long serialVersionUID = 1L;
	private Shape shape;
	private Dimension d = new Dimension(200, 32);
	private String placeholder = "";
	private Color phColor = new Color(20, 20, 20);
	private boolean band = true;

	public JTextPlaceHolderRound(int size) {
		
		super(size);

		setOpaque(false);

		setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(d);
		setPreferredSize(d);
		setVisible(true);
		setMargin(new Insets(3, 6, 3, 6));

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
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	/**
	 * @return the phColor
	 */
	public Color getPhColor() {
		return phColor;
	}

	/**
	 * @param phColor the phColor to set
	 */
	public void setPhColor(Color phColor) {
		this.phColor = phColor;
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// color de placeholder
		g.setColor(new Color(phColor.getRed(), phColor.getGreen(), phColor.getBlue(), 100));
		// dibuja texto
		g.drawString((band) ? placeholder : "", getMargin().left, (getSize().height) / 2 + getFont().getSize() / 2);
		
	}
	
	/**
	 * Bordes redondos
	 *  @param g the g to set
	 */
	
	protected void paintBorder(Graphics g) {

		
		g.setColor(getForeground());
		g.drawRoundRect(0, 29, getWidth()-1, getHeight()-1, 15, 15);
		
		
	}
	
	public boolean contains(int x, int y) {
		
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
			
		}
		return shape.contains(x, y);
	}
	
	
}
