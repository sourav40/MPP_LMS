package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogMessage {

	public static void showDialog(JFrame jFrame, String message, String messageType) {

		switch (messageType) {
		case "Plain":
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.PLAIN_MESSAGE);
			break;
		case "Error":
			JOptionPane.showMessageDialog(jFrame, message, "Message", JOptionPane.ERROR_MESSAGE);
			break;
		case "Warning":
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
