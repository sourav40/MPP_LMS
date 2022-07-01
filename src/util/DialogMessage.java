package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogMessage {

	public static final String INFO = "INFO";
	public static final String ERROR = "ERROR";
	public static final String WARNING = "WARNING";

	public static void showDialog(JFrame jFrame, String message, String messageType) {

		switch (messageType) {
		case "INFOs":
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.PLAIN_MESSAGE);
			break;
		case "ERROR":
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.ERROR_MESSAGE);
			break;
		case "WARNING":
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.WARNING_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.PLAIN_MESSAGE);
			break;

		}
	}

	public static void main(String[] args) {
		DialogMessage.showDialog(new JFrame(), "Dialog message", "Error");
	}

}
