package by.epamlab.controllers;

import org.apache.log4j.BasicConfigurator;

import by.epamlab.frames.ElevatorFrame;

public class ControllerGUI extends Controller {

	private FrameController frameController;
	private boolean isCancelled;
	private ElevatorFrame frame;

	public ControllerGUI(int passengerNumber, int storyNumber, int capacity,
			int animationBoost) {
		super(passengerNumber, storyNumber, capacity);
		frame = new ElevatorFrame(storyNumber, transportationTasks, this);
		this.frameController = new FrameController(frame.getElevPanel(),
				storyNumber, animationBoost, frame.getMessagePanel());
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	@Override
	public void moveElevator() {
		int preStory = 0;
		BasicConfigurator.configure();
		while (true) {
			int passengersToLeave = 0;
			preStory = curStory;
			if (isCancelled) {
				frameController.changeToViewFileButton();
				break;
			}
			if (!elevator.isEmpty()) {
				passengersToLeave = getPassengersToGetOut();
				letPassengersGetOut();
			}
			chooseDirection();
			if (!dispatchContainers[curStory].isStoryEmpty()) {
				if (!isElevatorFull()) {
					letPassengersIntoElevator();
				}
			}
			moveNextStory();
			frameController.moveElevator(preStory, curStory, passengersArrived, passengersToLeave);
			logger.debug("Moving from " + preStory + " to " + curStory);
			if (passengersArrived == passengerNumber) {
				break;
			}
		}
		if (isValidate()) {
			logger.debug("All passengers arrived");
			frameController.changeToViewFileButton();
		}
	}
	
	public synchronized void wakeUpController() {
		notifyAll();
	}

}
