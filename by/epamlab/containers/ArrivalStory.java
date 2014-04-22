package by.epamlab.containers;

import java.util.ArrayList;
import java.util.List;

import by.epamlab.beans.Passenger;

public class ArrivalStory {
	
	private int story;
	private List<Passenger> passengerList = new ArrayList<Passenger>();
	
	public ArrivalStory(int story) {
		super();
		this.story = story;
	}
	
	public int getStory() {
		return story;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void addPassenger(Passenger passenger) {
		passengerList.add(passenger);
	}
	
	public int countPassengers() {
		return passengerList.size();
	}
}
