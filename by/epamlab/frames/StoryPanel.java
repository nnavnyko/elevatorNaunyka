package by.epamlab.frames;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StoryPanel extends JPanel {
	static final long serialVersionUID = 1L;
	private int storyNumber;

	public StoryPanel(int storyNumber) {
		super();
		this.storyNumber = storyNumber;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = storyNumber; i > 0; i--) {
			g.drawString(i+"", 50, (storyNumber - i)* 70 + 24);
		}
	}

}
