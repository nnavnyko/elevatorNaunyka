package by.epamlab.buffers;

import org.apache.log4j.Logger;

import by.epamlab.beans.Passenger;
import by.epamlab.controllers.Controller;
import by.epamlab.enumerations.TransportationState;

public class DispatchBuffer {

	private Controller controller;
	private Passenger passenger;
	private boolean isEmpty;
	private int story;
	private Logger logger;

	public DispatchBuffer(Controller controller,  int story, Logger logger) {
		super();
		this.controller = controller;
		this.isEmpty = true;
		this.story = story;
		this.logger = logger;
	}

	public synchronized void setPassenger(Passenger passenger) {
		while (!isEmpty || controller.isElevatorFull()
				|| (passenger.isUp() != controller.isDirectionUp())
				|| (controller.getCurStory() != story)) {
			try {
				wait();
			} catch (InterruptedException e) {
				passenger.setState(TransportationState.ABORTED);
				logger.debug("Aborted: " + passenger);
			}
		}
		isEmpty = false;
		this.passenger = passenger;
		notifyAll();
	}

	public synchronized Passenger getPassenger() {
		while (isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isEmpty = true;
		//notifyAll();
		return passenger;
	}

	public synchronized void wakeUp() {
		notifyAll();
	}

}
