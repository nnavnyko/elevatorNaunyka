package by.epamlab.factories;

import by.epamlab.controllers.Controller;
import by.epamlab.controllers.ControllerGUI;

public class ControllerFactory {
	
	public static Controller getClassFromFactory(int passengerNumber, int storyNumber, int capacity, int animationBoost) {
		if (animationBoost > 0) {
			return new ControllerGUI(passengerNumber, storyNumber, capacity, animationBoost);
		} else {
			return new Controller(passengerNumber, storyNumber, capacity);
		}
	}
}
