import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Snake extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Box mHead = new Box();
	public Timer mTimer = null;
	public Bait mBait = new Bait();
	public Random random = null;

	/*
	 * An array list to store boxes for snake.
	 */
	public ArrayList<Box> list = new ArrayList<Box>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * 
	 * Paints the boxes.
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D r2 = new Rectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(10));
		g2.draw(r2);
	}

	/*
	 * Main method for Snake.
	 */
	public Snake() {

		random = new Random(System.currentTimeMillis());
		addKeyListener(new Keyboard());
		setFocusable(true);
		mTimer = new Timer(100, new TimerControl());
		mTimer.start();

		list.add(mHead);
		for (int i = 0; i < 4; i++) {
			AddTail();
		}

		add(mBait);
		add(mHead);
	}

	/*
	 * Add Tail to the end
	 */
	public void AddTail() {
		Box K = list.get(list.size() - 1).Tail();
		list.add(K);
		add(K);
	}

	/*
	 * Adds Bait to the Snake game.
	 */
	public void AddBait() {

		int Width = getWidth() - 30 - mBait.mWidth;
		int Height = getHeight() - 30 - mBait.mWidth;

		int PosX = 10 + Math.abs(random.nextInt()) % Width;
		int PosY = 10 + Math.abs(random.nextInt()) % Height;

		PosX = PosX - PosX % 20;
		PosY = PosY - PosY % 20;

		for (int i = 0; i < list.size(); i++) {

			if ((PosX == list.get(i).getX()) && (PosY == list.get(i).getY())) {
				AddBait();
				return;
			}
		}

		mBait.setPosition(PosX, PosY);
	}

	/*
	 * Moves all of the boxes of snake.
	 */
	public void MoveAll() {

		for (int i = list.size() - 1; i > 0; i--) {

			Box before = list.get(i - 1);
			Box after = list.get(i);

			list.get(i).Move();
			after.mDirection = before.mDirection;
		}
		mHead.Move();
	}

	/*
	 * Hit construction if the snake hits the red line.
	 */
	public boolean Hit() {

		int pencil = 10;
		int width = getWidth();
		int Height = getHeight();

		if (mHead.getX() <= 10) {
			return true;
		}

		if (mHead.getX() + mHead.mWidth >= width - pencil) {
			return true;
		}

		if (mHead.getY() <= 10) {
			return true;
		}
		if (mHead.getY() + mHead.mWidth >= Height - pencil) {
			return true;
		}

		for (int i = 1; i < list.size(); i++) {
			int X = list.get(i).getX();
			int Y = list.get(i).getY();

			if ((X == mHead.getX()) && (Y == mHead.getY())) {
				return true;
			}
		}

		if ((mBait.getX() == mHead.getX()) && (mBait.getY() == mHead.getY())) {

			AddTail();
			AddBait();
		}

		return false;
	}

	/*
	 * A constructor to control snake with keyboard.
	 */
	class Keyboard implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {

				if (mHead.mDirection != Direction.RIGHT)
					mHead.mDirection = Direction.LEFT;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

				if (mHead.mDirection != Direction.LEFT)
					mHead.mDirection = Direction.RIGHT;

			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {

				if (mHead.mDirection != Direction.DOWN)
					mHead.mDirection = Direction.UP;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				if (mHead.mDirection != Direction.UP)
					mHead.mDirection = Direction.DOWN;
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	/*
	 * A timer constructor to control the speed of the snake.
	 */
	class TimerControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			MoveAll();

			if (Hit())
				mTimer.stop();
		}

	}

}
