import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

public class Bait extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int mWidth = 20;

	/*
	 * Main method for Bait.
	 */
	public Bait() {
		setPosition(20, 20);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * 
	 * Paints the Bait.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D ellipse = new Ellipse2D.Double(1, 1, mWidth - 2, mWidth - 2);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2));
		g2.draw(ellipse);
		g2.setColor(Color.red);
		g2.fill(ellipse);
	}

	/*
	 * Set the position of the bait.
	 */
	public void setPosition(int PosX, int PosY) {
		setBounds(PosX, PosY, mWidth, mWidth);
	}

}
