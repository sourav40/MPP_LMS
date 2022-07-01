package librarysystem.panels;

import business.ControllerInterface;
import business.SystemController;
import librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
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
		mainPanel.setLayout(new FlowLayout());
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

		AddBookCopyPanel abcp = new AddBookCopyPanel();
		NewMemberPanel nmp = new NewMemberPanel();

		String bookPath = System.getProperty("user.dir") + "/src/librarysystem/book.png";
		Icon bookIcon = new ImageIcon(bookPath);
		JButton loginLabel = new JButton("Available Books : " + sc.allBookIds().size(), bookIcon);
		loginLabel.setBackground(Color.LIGHT_GRAY);
		loginLabel.setOpaque(true);
		loginLabel.setBorderPainted(false);
		loginLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		String memberPath = System.getProperty("user.dir") + "/src/librarysystem/member.png";
		Icon memberIcon = new ImageIcon(memberPath);
		JButton member = new JButton("Members : " + sc.allMemberIds().size(), memberIcon);
		member.setBackground(Color.LIGHT_GRAY);
		member.setOpaque(true);
		member.setBorderPainted(false);
		member.setFont(new Font("Arial", Font.PLAIN, 20));

		mainPanel.add(loginLabel);
		mainPanel.add(member);
	}

}
