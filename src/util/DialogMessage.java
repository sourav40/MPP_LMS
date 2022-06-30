package util;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DialogMessage {

	public static void showDialog(JFrame jFrame, String message) {
		JDialog d = new JDialog(jFrame, "dialog Box");
		centerFrameOnDesktop(d);

		// create a label
		JLabel l = new JLabel(message);

		d.add(l);

		// setsize of dialog
		d.setSize(300, 100);

		// set visibility of dialog
		d.setVisible(true);
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}

	public static void main(String[] args) {
		DialogMessage.showDialog(new JFrame(), "message");
	}

}
