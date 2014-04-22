package by.epamlab.controllers;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import by.epamlab.Constants;
import by.epamlab.frames.ElevatorPanel;
import by.epamlab.frames.MessagePanel;

public class FrameController {
	private ElevatorPanel elevPanel;
	private int storyNumber;
	private int curPos;
	private int prePos;
	private boolean isUp = false;
	private int animationBoost;
	private MessagePanel msgPanel;
	private int curPosition;
	private int sleepTime;
	
	public FrameController(ElevatorPanel elevPanel, int storyNumber, int animationBoost, MessagePanel msgPanel) {
		super();
		this.elevPanel = elevPanel; // pane contains elevator
		this.storyNumber = storyNumber;
		this.animationBoost = animationBoost;
		this.msgPanel = msgPanel;
	}
	
	//change direction if needs
	public void selectDirection(int preStory, int curStory) {
		final int STEP_ELEVATOR = 60;
		prePos = (storyNumber - preStory) * STEP_ELEVATOR;
		curPos = (storyNumber - curStory) * STEP_ELEVATOR;
		if (curPos > prePos) {
			isUp = false;
		} else {
			isUp = true;
		}
	}
	
	public void moveElevator(int preStory, int curStory, int passengersArrived, int passengersToLeave) {
		// time for thread sleeps (depends on animationBoost)
		sleepTime = Constants.TIME_SLEEP / animationBoost;
		selectDirection(preStory, curStory);
		final String ARRIVED = "Passengers arrived: ";
		final String LEAVE = "Passengers left: ";
		// show some messages to message-panel
		msgPanel.setMessage(ARRIVED + passengersArrived);
		msgPanel.setArrivedMessage(LEAVE + passengersToLeave);
		// sleep if someone arrived
		if (passengersToLeave != 0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// move elevator up or down
		if (isUp) {
			for (int i = prePos; i >= curPos; i--) {
				curPosition = i;
				repaintElevator();
			}
		} else {
			for (int i = prePos; i <= curPos; i++) {
				curPosition = i;
				repaintElevator();
			}
		}
	}
	
	public void repaintElevator() {
		try {
			// swing-element should be painted in EventDispatchThread
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					elevPanel.setPosElevator(curPosition);
					elevPanel.repaint();
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void changeToViewFileButton() {
		// change button to view the log-file
		msgPanel.setFileViewButton();
	}
	
}
