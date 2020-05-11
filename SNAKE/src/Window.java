import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Width and Height of Window
	 */
	private int Mwidth = 600;
	private int Mheight = 600;
	private static Window mWindow = null;

	/*
	 * Main Window
	 */
	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		SetDimension(Mwidth, Mheight);
		setResizable(false);

		Snake Y = new Snake();
		add(Y);
	}

	/*
	 * Show window for snake game
	 * 
	 */
	public static Window BringWindow() {
		if (mWindow == null) {
			mWindow = new Window();
			return mWindow;
		}
		return new Window();
	}

	/*
	 * Set dimensions
	 */
	public void SetDimension(int Width, int Height) {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int PosX = 0;
		int PosY = 0;

		if (Mwidth + 100 > dim.width) {
			Mwidth = dim.width - 100;
		}
		if (Mheight + 100 > dim.height) {
			Mheight = dim.height - 100;
		}

		PosX = (dim.width - Mwidth) / 2;
		PosY = (dim.height - Mheight) / 2;

		setBounds(PosX, PosY, Mwidth, Mheight);
	}
}
