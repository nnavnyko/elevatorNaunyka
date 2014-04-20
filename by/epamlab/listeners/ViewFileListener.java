package by.epamlab.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewFileListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		String fileName = "C:\\vsprog\\LoggingSample\\";
		String[] commands = { "cmd", "/c", "start", fileName };
		try {
			//open folder contains log-file
			Runtime.getRuntime().exec(commands);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
