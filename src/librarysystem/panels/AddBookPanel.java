package librarysystem.panels;

import business.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBookPanel implements MessageableWindow {
	public JPanel getMainPanel() {
		clearData();
		return mainPanel;
	}

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel outerMiddle;
	private JPanel addBookButtonPanel;
	private JPanel addResetBookButtonPanel;

	private JTextField bookISBN;
	private JTextField titleField;
	private JTextField maxCheckoutLength;
	private JTextField authLastNameField;
	private JTextField authFirstNameField;
	private JTextField authPhoneNumberField;

	private final ControllerInterface sc = SystemController.INSTANCE;

	public void clearData() {
		authPhoneNumberField.setText("");
		authFirstNameField.setText("");
		authLastNameField.setText("");
		maxCheckoutLength.setText("");
		titleField.setText("");
		bookISBN.setText("");
	}

	public AddBookPanel() {

		mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        GridLayout gridLayout = new GridLayout(6, 2);
//        mainPanel.setLayout(gridLayout);

		defineTopPanel();
		defineOuterMiddle();
		defineLowerPanel();

		BorderLayout bl = new BorderLayout();
		bl.setVgap(70);
		mainPanel.setLayout(bl);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddle, BorderLayout.CENTER);
		mainPanel.add(addBookButtonPanel, BorderLayout.SOUTH);
		mainPanel.add(addResetBookButtonPanel, BorderLayout.EAST);

//		clearData();
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AddBookLabel = new JLabel("Add Book Title");
		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(AddBookLabel);
	}

	public void defineOuterMiddle() {
		outerMiddle = new JPanel();

//        outerMiddle.setLayout(new BorderLayout());

		GridLayout gridLayout = new GridLayout(6, 2);
		outerMiddle.setLayout(gridLayout);

		// set up left and right panels
//        JPanel middlePanel = new JPanel();
//        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
//        middlePanel.setLayout(fl);
//        JPanel leftPanel = new JPanel();
//        JPanel rightPanel = new JPanel();
//        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Book Title");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JLabel bookISBNLabel = new JLabel("Book ISBN");
		bookISBNLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JLabel authLastNameLabel = new JLabel("Author Last Name");
		authLastNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JLabel authFirstNameLabel = new JLabel("Author First Name");
		authFirstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JLabel authPhoneNumberLabel = new JLabel("Author Phone Number");
		authPhoneNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JLabel maxCheckoutLengthLabel = new JLabel("Max Checkout Length");
		maxCheckoutLengthLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		bookISBN = new JTextField();
		titleField = new JTextField();
		maxCheckoutLength = new JTextField(10);
		authLastNameField = new JTextField(10);
		authFirstNameField = new JTextField();
		authPhoneNumberField = new JTextField(10);

		outerMiddle.add(authFirstNameLabel);
		outerMiddle.add(authFirstNameField);
		authFirstNameField.setPreferredSize(new Dimension(20, 10));
//        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		outerMiddle.add(authLastNameLabel);
		outerMiddle.add(authLastNameField);
//        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		outerMiddle.add(authPhoneNumberLabel);
		outerMiddle.add(authPhoneNumberField);
//        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		outerMiddle.add(titleLabel);
		outerMiddle.add(titleField);
//        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		outerMiddle.add(bookISBNLabel);
		outerMiddle.add(bookISBN);
//        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		outerMiddle.add(maxCheckoutLengthLabel);
		outerMiddle.add(maxCheckoutLength);

//        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
//        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
//        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
//        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
//        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//        middlePanel.add(leftPanel);
//        middlePanel.add(rightPanel);
//        outerMiddle.add(middlePanel, BorderLayout.NORTH);
	}

	private void defineLowerPanel() {
		// add button at bottom
//    	lowerPanel = new JPanel();
		addBookButtonPanel = new JPanel();
		addResetBookButtonPanel = new JPanel();

		addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addResetBookButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addBookButton = new JButton("Add Book");
		JButton resetBookButton = new JButton("Reset");

		attachAddBookButtonListener(addBookButton);
		attachRestBookButtonListener(resetBookButton);

		addBookButtonPanel.add(addBookButton);
		addResetBookButtonPanel.add(resetBookButton);
//        outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);
	}
//        //add button at bottom
//        JButton addBookButton = new JButton("Add Book");
//        attachAddBookButtonListener(addBookButton);
//        JPanel addBookButtonPanel = new JPanel();
//        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        addBookButtonPanel.add(addBookButton);
//        outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);
//    }

	private void attachRestBookButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			clearData();
		});
	}

	private void attachAddBookButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String bkISBN = bookISBN.getText().trim();
			String title = titleField.getText().trim();
			String lName = authLastNameField.getText().trim();
			String fName = authFirstNameField.getText().trim();
			String phoneNumber = authPhoneNumberField.getText().trim();
			String maxCheckoutLen = maxCheckoutLength.getText().trim();
			if (lName.length() == 0 || fName.length() == 0 || phoneNumber.length() == 0 || bkISBN.length() == 0
					|| maxCheckoutLen.length() == 0) {
				displayError("All fields should be non-empty");
			} else if (!Util.isNumeric(maxCheckoutLen)) {
				displayError("Error: Max Checkout Length should be a number");
			} else {
				Address address = new Address("101 S. Main", "Fairfield", "IA", "52556");
				Author author = new Author(fName, lName, phoneNumber, address, "A happy man is he.");
				try {
					sc.addBook(bkISBN, title, Integer.parseInt(maxCheckoutLen), List.of(author));
					displayInfo("The book " + title + " has been added to the collection!");
				} catch (LibrarySystemException e) {
					displayError("Error: " + e.getMessage());
				}
			}
		});
	}

	public void updateData() {
	}
}
