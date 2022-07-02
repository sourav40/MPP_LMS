package librarysystem.panels;

import business.ControllerInterface;
import business.SystemController;
import librarysystem.LibrarySystem;

import javax.swing.*;

import access.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LandingPanel {
	LibrarySystem librarySystem;

	private final ControllerInterface sc = SystemController.INSTANCE;

	public void setLibrarySystem(LibrarySystem librarysystem) {
		librarySystem = librarysystem;
	}

	private JPanel mainPanel;

	String pathToImage;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public LandingPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

//		setPathToImage();
//		insertSplashImage();
		defineTopPanel();
	}

	private void setPathToImage() {
		String currDirectory = System.getProperty("user.dir");
		pathToImage = currDirectory + "/src/librarysystem/library.png";
	}

	private void insertSplashImage() {
		ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));
	}

	private void defineTopPanel() {

		System.out.println("calling the dashboard function....");

		String bookPath = System.getProperty("user.dir") + "/src/librarysystem/book.png";
		Icon bookIcon = new ImageIcon(bookPath);
		JButton loginLabel = new JButton("Total Books : " + sc.allBookIds().size(), bookIcon);
		loginLabel.setBounds(30, 80, 250, 50);
		loginLabel.setBackground(Color.LIGHT_GRAY);
		loginLabel.setOpaque(true);
		loginLabel.setBorderPainted(false);
		loginLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		String memberPath = System.getProperty("user.dir") + "/src/librarysystem/member.png";
		Icon memberIcon = new ImageIcon(memberPath);
		JButton member = new JButton("Members : " + sc.allMemberIds().size(), memberIcon);
		member.setBounds(320, 80, 250, 50);
		member.setBackground(Color.LIGHT_GRAY);
		member.setOpaque(true);
		member.setBorderPainted(false);
		member.setFont(new Font("Arial", Font.PLAIN, 20));

		String logoutPath = System.getProperty("user.dir") + "/src/librarysystem/logout.png";
		Icon logoutIcon = new ImageIcon(logoutPath);
		JButton logout = new JButton(logoutIcon);
		logout.setBorder(null);
		logout.setBounds(460, 2, 250, 50);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LibrarySystem.INSTANCE.dispose();
				Login.main(null);
			}
		});

		mainPanel.add(loginLabel);
		mainPanel.add(member);
		mainPanel.add(logout);

		System.out.println("member size :: " + sc.allMemberIds().size());

		mainPanel.repaint();
	}

}
