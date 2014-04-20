package by.epamlab.frames;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class MainElevatorPanel extends JPanel {
	static final long serialVersionUID = 1L;
	private ElevatorPanel elevPanel;
	private StoryPanel storyPanel;
	public MainElevatorPanel(int storyNumber) {
		super();
		elevPanel = new ElevatorPanel(storyNumber);
		storyPanel = new StoryPanel(storyNumber);
		setLayout(new GridLayout(1,2));
		add(elevPanel);
		add(storyPanel);
	}
	
	public ElevatorPanel getElevPanel() {
		return elevPanel;
	}


}
