package by.epamlab.beans;

import by.epamlab.enumerations.TransportationState;

public class Passenger {
	private int passangerID;
	private TransportationState state;
	private int destinationStory;
	private boolean isUp;

	public Passenger(int passangerID, int destinationStory, boolean isUp) {
		super();
		this.passangerID = passangerID;
		this.destinationStory = destinationStory;
		this.state = TransportationState.NOT_STARTED;
		this.isUp = isUp;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public int getPassangerID() {
		return passangerID;
	}

	public void setPassangerID(int passangerID) {
		this.passangerID = passangerID;
	}

	public TransportationState getState() {
		return state;
	}

	public void setState(TransportationState state) {
		this.state = state;
	}

	public int getDestinationStory() {
		return destinationStory;
	}

	public void setDestinationStory(int destinationStory) {
		this.destinationStory = destinationStory;
	}

	@Override
	public String toString() {
		return "PasID: " + passangerID + " Dest: " + (destinationStory + 1);
	}
	
	public boolean isDestination(int curStory) {
		if (destinationStory == curStory) {
			return true;
		}
		return false;
	}
}
