package by.epamlab.validators;

public class Validator {
	private String errorMessage;
	private int passengerNumber;
	private int storyNumber;
	private int capacity;

	public Validator(int passengerNumber, int storyNumber, int capacity) {
		super();
		this.passengerNumber = passengerNumber;
		this.storyNumber = storyNumber;
		this.capacity = capacity;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isValidate() {
		if (passengerNumber <= 0) {
			errorMessage = "ERROR: Passenger number is zero or less, change config.properties";
			return false;
		}
		if (storyNumber <= 0) {
			errorMessage = "ERROR: Story number is zero or less, change config.properties";
			return false;
		}
		if (capacity <= 0) {
			errorMessage = "ERROR: Capacity is zero or less, change config.properties";
			return false;
		}
		return true;
	}

}
