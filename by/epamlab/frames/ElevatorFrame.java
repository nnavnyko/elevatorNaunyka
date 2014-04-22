package by.epamlab.frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.epamlab.controllers.ControllerGUI;


public class ElevatorFrame extends JFrame {
	
	static final long serialVersionUID = 1L;
	private MessagePanel messagePanel;
	private ElevatorPanel elevPanel;
	private StoryPanel storyPanel;
	
	public ElevatorFrame(int storyNumber, Thread[] transportationTasks, ControllerGUI controller) throws HeadlessException {
		super();
		final int HEIGHT = 750;
		final int WIDTH = 800;
		messagePanel = new MessagePanel(transportationTasks, controller);
		elevPanel = new ElevatorPanel(storyNumber);
		storyPanel = new StoryPanel(storyNumber);
		JPanel pane = new JPanel(new GridLayout(1,2));
		JPanel elevatorPanel = new JPanel(new GridLayout(1,2));
		elevatorPanel.setSize(400, storyNumber * 70);
		elevatorPanel.add(elevPanel);
		elevatorPanel.add(storyPanel);
		JScrollPane scrollpane = new JScrollPane(elevatorPanel);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.add(scrollpane);
		pane.add(messagePanel);
		setContentPane(pane);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Elevator");
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
