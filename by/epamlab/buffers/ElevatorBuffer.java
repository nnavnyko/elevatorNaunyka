package by.epamlab.buffers;

import org.apache.log4j.Logger;

import by.epamlab.beans.Passenger;
import by.epamlab.controllers.Controller;
import by.epamlab.enumerations.TransportationState;

public class ElevatorBuffer {
	
	private Controller controller;
	private Passenger passenger;
	private boolean isEmpty;
	private Logger logger;
	
	public ElevatorBuffer(Controller controller, Logger logger) {
		super();
		this.controller = controller;
		isEmpty = true;
		this.logger = logger;
	}
	
	public synchronized void setPassenger(Passenger passenger) {
		while(!isEmpty || (controller.getCurStory() != passenger.getDestinationStory())) {
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
		while(isEmpty) {
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
