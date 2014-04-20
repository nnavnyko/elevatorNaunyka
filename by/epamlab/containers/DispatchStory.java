package by.epamlab.containers;

import java.util.ArrayList;
import java.util.List;

import by.epamlab.beans.Passenger;

public class DispatchStory {

	private int story;
	public List<Passenger> passengerList = new ArrayList<Passenger>();

	public DispatchStory(int story) {
		super();
		this.story = story;
	}

	public int getStory() {
		return story;
	}

	public void addPassenger(Passenger passenger) {
		passengerList.add(passenger);
	}

	public boolean isStoryEmpty() {
		return passengerList.isEmpty();
	}

	public void removePassenger(Passenger passenger) {
		passengerList.remove(passenger);
	}

	// method returns count of passengers, who's direction the same as
	// elevator's
	public int passengersToGetIn(boolean directionUp) {
		int count = 0;
		for (Passenger passenger : passengerList) {
			if (directionUp == passenger.isUp()) {
				count++;
			}
		}
		return count;
	}
}
