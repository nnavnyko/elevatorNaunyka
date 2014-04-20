package by.epamlab.threads;

import org.apache.log4j.Logger;

import by.epamlab.beans.Passenger;
import by.epamlab.buffers.DispatchBuffer;
import by.epamlab.buffers.ElevatorBuffer;
import by.epamlab.controllers.Controller;
import by.epamlab.enumerations.TransportationState;

public class TransportationTask implements Runnable {
	
	private Passenger passenger;
	private Logger logger;
	private DispatchBuffer dispatchBuffer;
	private ElevatorBuffer elevatorBuffer;

	public TransportationTask(Passenger passenger, Controller controller,
			Logger logger, DispatchBuffer dispatchBuffer, ElevatorBuffer elevatorBuffer) {
		super();
		this.passenger = passenger;
		this.logger = logger;
		this.dispatchBuffer = dispatchBuffer;
		this.elevatorBuffer = elevatorBuffer;
	}

	public void run() {
		passenger.setState(TransportationState.IN_PROGRESS);
		// move passenger to elevator
		dispatchBuffer.setPassenger(passenger);
		// move passenger to arrival story
		elevatorBuffer.setPassenger(passenger);
		// change passenger's state
		passenger.setState(TransportationState.COMPLETED);
		logger.debug("Passenger arrived: " + passenger);
	}

}
