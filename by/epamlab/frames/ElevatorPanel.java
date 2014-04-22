package by.epamlab.frames;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ElevatorPanel extends JPanel {
	static final long serialVersionUID = 1L;
	private int posElevator;

	public ElevatorPanel(int storyNumber) {
		super();
		// elevator start position
		posElevator = (storyNumber * 70) - 80;
	}
	
	public int getPosElevator() {
		return posElevator;
	}

	public void setPosElevator(int posElevator) {
		this.posElevator = posElevator;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawElevator(g, posElevator);
	}

	public void drawElevator(Graphics g, int yPosition) {
		final int RECT_X = 80;
		final int WIDTH = 50;
		final int HEIGHT = 60;
		final int Y_RECT = yPosition - 40;
		final int LINE_X_POSITION = RECT_X + 25;
		g.setColor(Color.RED);
		g.drawLine(LINE_X_POSITION, Y_RECT, LINE_X_POSITION, Y_RECT + HEIGHT);
		g.drawRect(RECT_X - 3, Y_RECT - 3, WIDTH + 6,
				HEIGHT + 6);
		g.drawRect(RECT_X, Y_RECT, WIDTH,
				HEIGHT);
	}
	
}
