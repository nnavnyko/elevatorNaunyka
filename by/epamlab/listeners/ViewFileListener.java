package by.epamlab.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewFileListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		String fileName = "C:\\vsprog\\LoggingSample\\";
		String[] commands = { "cmd", "/c", "start", fileName };
		try {
			Runtime.getRuntime().exec(commands);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
