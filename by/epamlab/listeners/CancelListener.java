package by.epamlab.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.epamlab.controllers.ControllerGUI;

public class CancelListener implements ActionListener {
	private Thread[] transportationTasks;
	private ControllerGUI controller;
	
	public CancelListener(Thread[] transportationTasks, ControllerGUI controller) {
		super();
		this.controller = controller;
		this.transportationTasks = transportationTasks;
	}

	public void actionPerformed(ActionEvent arg0) {
		controller.setCancelled(true);
		for (Thread thread: transportationTasks) {
			thread.interrupt();
		}
		controller.wakeUpController();
	}

}
