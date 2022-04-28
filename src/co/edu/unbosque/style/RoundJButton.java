package co.edu.unbosque.style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

	public class RoundJButton extends JButton {

		private static final long serialVersionUID = 1L;
		private Shape shape;
/**
 * 
 * @param string
 */
		public RoundJButton(String string) {
			super(string);
			setOpaque(false);
		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
			super.paintComponent(g);
		}

		public void paintBorder(Graphics g) {

			g.setColor(new Color(91,44,111));
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
			}
			return shape.contains(x, y);
		}
		
		

	}// Cierre del métod

