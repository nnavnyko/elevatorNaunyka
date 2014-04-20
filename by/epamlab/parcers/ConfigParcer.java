package by.epamlab.parcers;

import java.util.ResourceBundle;

import by.epamlab.Constants;

public class ConfigParcer {
	
	private int storiesNumber;
	private int elevatorCapacity;
	private int passengersNumber;
	private int animationBoost;
	
	public ConfigParcer() {
		super();
		loadData();
	}

	public int getStoriesNumber() {
		return storiesNumber;
	}

	public int getElevatorCapacity() {
		return elevatorCapacity;
	}

	public int getPassengersNumber() {
		return passengersNumber;
	}

	public int getAnimationBoost() {
		return animationBoost;
	}
	
	public void loadData() {
		ResourceBundle rb = ResourceBundle.getBundle(Constants.CONFIG_PROPERTY_FILE);
		storiesNumber = Integer.parseInt(rb.getString(Constants.STORIES_NUMBER));
		elevatorCapacity = Integer.parseInt(rb.getString(Constants.ELEVATOR_CAPACITY));
		passengersNumber = Integer.parseInt(rb.getString(Constants.PASSENGER_NUMBER));
		animationBoost = Integer.parseInt(rb.getString(Constants.ANIMATION_BOOST));
	}
	
}
