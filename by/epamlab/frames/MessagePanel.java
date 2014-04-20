package by.epamlab.frames;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.epamlab.controllers.ControllerGUI;
import by.epamlab.listeners.CancelListener;
import by.epamlab.listeners.ViewFileListener;

public class MessagePanel extends JPanel {

	static final long serialVersionUID = 1L;
	private JTextField messageField1;
	private JTextField messageField2;
	private JButton btn1;
	
	public MessagePanel(Thread[] transportationTasks, ControllerGUI controller) {
		super();
		String message = "Starting...";
		String arrived = "";
		setLayout(new GridLayout(2, 1));
		messageField1 = new JTextField(message);
		messageField1.setFont(new Font("Arial", Font.BOLD, 20));
		messageField2 = new JTextField(arrived);
		messageField2.setFont(new Font("Arial", Font.BOLD, 20));
		JPanel msgPane = new JPanel();
		msgPane.setLayout(new GridLayout(2, 1));
		msgPane.add(messageField1);
		msgPane.add(messageField2);
		btn1 = new JButton();
		btn1.setFont(new Font("Arial", Font.BOLD, 30));
		btn1.setText("CANCEL");
		btn1.addActionListener(new CancelListener(transportationTasks, controller));
		add(msgPane);
		add(btn1);
	}
	
	public void setMessage(String message) {
		messageField1.setText(message);
		repaint();
	}
	
	public void setArrivedMessage(String arrived) {
		messageField2.setText(arrived);
		repaint();
	}
	
	public void setFileViewButton() {
		btn1.setText("VIEW LOG-FILE");
		btn1.addActionListener(new ViewFileListener());
	}

	
}
