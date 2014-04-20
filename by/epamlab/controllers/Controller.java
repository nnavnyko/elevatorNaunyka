package by.epamlab.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import by.epamlab.beans.Passenger;
import by.epamlab.buffers.DispatchBuffer;
import by.epamlab.buffers.ElevatorBuffer;
import by.epamlab.containers.ArrivalStory;
import by.epamlab.containers.DispatchStory;
import by.epamlab.enumerations.TransportationState;
import by.epamlab.threads.TransportationTask;

public class Controller {
	// properties
	protected int passengerNumber;
	protected int storyNumber;
	protected int capacity;
	// current story
	protected int curStory;
	// containers
	protected DispatchStory[] dispatchContainers;
	protected List<Passenger> elevator;
	protected ArrivalStory[] arrivalContainers;
	// direction (if up, value = true, if down, value = false)
	protected boolean directionUp = true;
	// logger to write the log-file
	protected volatile Logger logger;
	// count of passengers
	protected int passengersArrived;
	protected int passengersToComeIn;
	protected int passengersToGetOut;
	// buffers
	protected DispatchBuffer[] dispatchBuffers;
	protected ElevatorBuffer elevatorBuffer;

	protected Thread[] transportationTasks;

	public Controller(int passengerNumber, int storyNumber, int capacity) {
		super();
		this.passengerNumber = passengerNumber;
		this.storyNumber = storyNumber;
		this.capacity = capacity;
		// creating arrays of containers and buffers
		dispatchContainers = new DispatchStory[storyNumber];
		arrivalContainers = new ArrivalStory[storyNumber];
		dispatchBuffers = new DispatchBuffer[storyNumber];
		elevator = new ArrayList<Passenger>(capacity);
		// create logger to write the log-file
		logger = Logger.getLogger(getClass());
		for (int i = 0; i < storyNumber; i++) {
			dispatchContainers[i] = new DispatchStory(i);
			dispatchBuffers[i] = new DispatchBuffer(this, i, logger);
			arrivalContainers[i] = new ArrivalStory(i);
		}
		elevatorBuffer = new ElevatorBuffer(this, logger);
		passengersArrived = 0;
		transportationTasks = new Thread[passengerNumber];
	}

	public int getCurStory() {
		return curStory;
	}

	public boolean isDirectionUp() {
		return directionUp;
	}

	protected void moveNextStory() {
		// switch story
		if (directionUp) {
			curStory++;
		} else {
			curStory--;
		}
	}

	protected void chooseDirection() {
		// choose direction
		if (curStory == (storyNumber - 1)) {
			directionUp = false;
		}
		if (curStory == 0) {
			directionUp = true;
		}
	}

	// method returns count of passengers, who arrived to destination story
	protected int getPassengersToGetOut() {
		int result = 0;
		for (Passenger passenger : elevator) {
			if (passenger.getDestinationStory() == curStory) {
				result++;
			}
		}
		return result;
	}
	
	public boolean isElevatorFull() {
		if (elevator.size() == capacity) {
			return true;
		} else {
			return false;
		}
	}

	// initializing data: distributing passengers, starting threads
	public void initData() {
		Random random = new Random(storyNumber);
		Passenger passenger;
		for (int i = 0; i < passengerNumber; i++) {
			int destination = random.nextInt(storyNumber);
			int story = random.nextInt(storyNumber);
			// if destination and story have the same values,
			// we have to change story value
			if (story == destination) {
				if (story == storyNumber - 1) {
					story -= 1;
				} else {
					story += 1;
				}
			}
			// choose passenger's direction
			boolean isUp;
			if (story > destination) {
				isUp = false;
			} else {
				isUp = true;
			}
			passenger = new Passenger(i, destination, isUp);
			dispatchContainers[story].addPassenger(passenger);
			transportationTasks[i] = new Thread(new TransportationTask(
					passenger, this, logger, dispatchBuffers[story],
					elevatorBuffer));
			transportationTasks[i].start();
		}
	}

	protected void movePassengerToElevator() {
		// get passenger from buffer
		Passenger passenger = dispatchBuffers[curStory].getPassenger();
		elevator.add(passenger);
		dispatchContainers[curStory].removePassenger(passenger);
		passengersToComeIn--;
	}

	protected void letPassengersIntoElevator() {
		// select passengers, which have the same direction as elevator has
		passengersToComeIn = dispatchContainers[curStory]
				.passengersToGetIn(directionUp);
		dispatchBuffers[curStory].wakeUp();
		while (passengersToComeIn != 0) {
			movePassengerToElevator();
			if (isElevatorFull()) {
				break;
			}
			dispatchBuffers[curStory].wakeUp();
		}
	}

	protected void movePassengerToArrivalStory() {
		// get passenger from elevator-buffer
		Passenger passenger = elevatorBuffer.getPassenger();
		arrivalContainers[curStory].addPassenger(passenger);
		elevator.remove(passenger);
		passengersToGetOut--;
		passengersArrived++;
	}

	//move passengers to arrival story
	protected void letPassengersGetOut() {
		passengersToGetOut = getPassengersToGetOut();
		elevatorBuffer.wakeUp();
		while (passengersToGetOut != 0) {
			movePassengerToArrivalStory();
			if (passengersToGetOut != 0) {
				elevatorBuffer.wakeUp();
			}
		}
	}

	public void moveElevator() {
		//needs to write actions in console
		BasicConfigurator.configure();
		int preStory = 0;
		while (true) {
			preStory = curStory;
			// move passengers to arrival story
			if (!elevator.isEmpty()) {
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
			logger.debug("Moving from " + preStory + " to " + curStory);
			if (passengersArrived == passengerNumber) {
				break;
			}
		}
		if (isValidate()) {
			logger.debug("All passengers arrived");
		}
	}

	protected boolean isValidate() {
		int passengersInArrivalStory = 0;
		for (int i = 0; i < storyNumber; i++) {
			passengersInArrivalStory += arrivalContainers[i].countPassengers();
		}
		if (passengersInArrivalStory != passengerNumber) {
			return false;
		}
		if (!elevator.isEmpty()) {
			return false;
		}
		for (int i = 0; i < storyNumber; i++) {
			if (!dispatchContainers[i].isStoryEmpty()) {
				return false;
			}
		}
		for (int i = 0; i < storyNumber; i++) {
			for (Passenger passenger : arrivalContainers[i].getPassengerList()) {
				if (!TransportationState.COMPLETED.equals(passenger.getState())) {
					return false;
				}
			}
		}
		return true;
	}

	public void startMoveElevator() {
		initData();
		moveElevator();
	}
}
