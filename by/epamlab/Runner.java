package by.epamlab;

import by.epamlab.controllers.Controller;
import by.epamlab.factories.ControllerFactory;
import by.epamlab.parcers.ConfigParcer;

public class Runner {

	public static void main(String[] args) {
		ConfigParcer parcer = new ConfigParcer();
		Controller controller = ControllerFactory.getClassFromFactory(parcer.getPassengersNumber(), parcer.getStoriesNumber(), parcer.getElevatorCapacity(), parcer.getAnimationBoost());
		controller.startMoveElevator();

	}

}
