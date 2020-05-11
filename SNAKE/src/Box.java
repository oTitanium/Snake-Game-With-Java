import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public class Box extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int mWidth = 20;
	public int mDirection = Direction.RIGHT;

	/*
	 * Main method of Boxes.
	 */
	public Box() {
		setBounds(100, 100, mWidth, mWidth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * 
	 * Paints the boxes in red color.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D r2 = new Rectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2));
		g2.draw(r2);
		g2.setColor(Color.red);
		g2.fill(r2);
	}

	/*
	 * Move left with keyboard.
	 */
	public void MoveLeft() {
		int PosX = getX();
		int PosY = getY();

		PosX -= mWidth;
		setBounds(PosX, PosY, mWidth, mWidth);
	}

	/*
	 * Move right with keyboard.
	 */
	public void MoveRight() {
		int PosX = getX();
		int PosY = getY();

		PosX += mWidth;
		setBounds(PosX, PosY, mWidth, mWidth);
	}

	/*
	 * Move up with keyboard.
	 */
	public void MoveUp() {
		int PosX = getX();
		int PosY = getY();

		PosY -= mWidth;
		setBounds(PosX, PosY, mWidth, mWidth);
	}

	/*
	 * Move down with keyboard.
	 */
	public void MoveDown() {
		int PosX = getX();
		int PosY = getY();

		PosY += mWidth;
		setBounds(PosX, PosY, mWidth, mWidth);
	}

	/*
	 * Constructor to create a tail.
	 */
	public Box Tail() {
		Box K = new Box();

		int X = getX();
		int Y = getY();

		K.setBounds(X, Y, mWidth, mWidth);
		K.mDirection = -mDirection;
		K.Move();
		K.mDirection = mDirection;
		return K;
	}

	/*
	 * Main method to move all snake.
	 */
	public void Move() {

		if (mDirection == Direction.LEFT) {
			MoveLeft();
		} else if (mDirection == Direction.RIGHT) {
			MoveRight();
		} else if (mDirection == Direction.DOWN) {
			MoveDown();
		} else {
			MoveUp();
		}
	}
}
