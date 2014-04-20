package by.epamlab.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class StoryPanel extends JPanel {
	static final long serialVersionUID = 1L;

	public StoryPanel(int storyNumber) {
		super();
		setBackground(Color.GREEN);
		setLayout(new GridLayout(storyNumber, 1));
		setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = storyNumber; i > 0; i--) {
			add(new Label(i + ""));
		}
	}
	
	
	
}
