package by.epamlab.frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import by.epamlab.controllers.ControllerGUI;


public class ElevatorFrame extends JFrame {
	
	static final long serialVersionUID = 1L;
	private MessagePanel messagePanel;
	private ElevatorPanel elevPanel;
	private StoryPanel storyPanel;
	
	public ElevatorFrame(int storyNumber, Thread[] transportationTasks, ControllerGUI controller) throws HeadlessException {
		super();
		//mainPanel = new MainElevatorPanel(storyNumber);
		messagePanel = new MessagePanel(transportationTasks, controller);
		elevPanel = new ElevatorPanel(storyNumber);
		storyPanel = new StoryPanel(storyNumber);
		JPanel pane = new JPanel(new GridLayout(1,3));
		pane.add(elevPanel);
		pane.add(storyPanel);
		pane.add(messagePanel);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Elevator");
		final int STORY_SIZE = 70;
		final int WIDTH = 750;
		setPreferredSize(new Dimension(WIDTH, STORY_SIZE * storyNumber));
		pack();
		setVisible(true);
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

	public ElevatorPanel getElevPanel() {
		return elevPanel;
	}

	public StoryPanel getStoryPanel() {
		return storyPanel;
	}
	
	
}
