package by.epamlab.controllers;

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
		// stop, if user click on Cancel-button
		this.isCancelled = isCancelled;
	}

	@Override
	public void moveElevator() {
		int preStory = 0;
		while (true) {
			int passengersToLeave = 0;
			preStory = curStory;
			if (isCancelled) {
				frameController.changeToViewFileButton();
				break;
			}
			// move passengers to arrival story
			if (!elevator.isEmpty()) {
				passengersToLeave = getPassengersToGetOut();
				letPassengersGetOut();
			}
			// let in passengers, who's direction the same as elevator's
			chooseDirection();
			if (!dispatchContainers[curStory].isStoryEmpty()) {
				if (!isElevatorFull()) {
					letPassengersIntoElevator();
				}
			}
			moveNextStory();
			frameController.moveElevator(preStory, curStory, passengersArrived,
					passengersToLeave);
			logger.debug("Moving from " + preStory + " to " + curStory);
			if (passengersArrived == passengerNumber) {
				break;
			}
		}
		// check is result valid
		if (isValidate()) {
			logger.debug("All passengers arrived");
			frameController.changeToViewFileButton();
		}
	}

}
