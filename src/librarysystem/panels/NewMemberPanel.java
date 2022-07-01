package librarysystem.panels;

import business.Address;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class NewMemberPanel implements MessageableWindow {
	ControllerInterface controllerInterface = SystemController.INSTANCE;

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;

	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField streetField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zipField;
	private JTextField cellField;

	private JPanel innerPanel;

	private JButton addButton;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public NewMemberPanel() {
		topPanel = new JPanel();
		mainPanel = new JPanel();
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
	}

	private void defineTopPanel() {
		JLabel label = new JLabel("Add New Member");
		Util.adjustLabelFont(label, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(label);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
//        BorderLayout bl = new BorderLayout();
//        bl.setVgap(30);
		GridLayout gridLayout = new GridLayout(8, 2);
		middlePanel.setLayout(gridLayout);

//		innerPanel = new JPanel();
//		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
//		innerPanel.setLayout(fl);
//
//		JPanel leftPanel = new JPanel();
//		JPanel rightPanel = new JPanel();
//		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel idLabel = new JLabel("ID");
		idLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel streetLabel = new JLabel("Street");
		streetLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel cityLabel = new JLabel("City");
		cityLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel stateLabel = new JLabel("State");
		stateLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		JLabel cellLabel = new JLabel("Mobile Number");
		cellLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

		idField = new JTextField(10);
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		streetField = new JTextField(10);
		cityField = new JTextField(10);
		stateField = new JTextField(10);
		zipField = new JTextField(10);
		cellField = new JTextField(10);

		middlePanel.add(idLabel);
		middlePanel.add(idField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(firstNameLabel);
		middlePanel.add(firstNameField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(lastNameLabel);
		middlePanel.add(lastNameField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(streetLabel);
		middlePanel.add(streetField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(cityLabel);
		middlePanel.add(cityField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(stateLabel);
		middlePanel.add(stateField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(zipLabel);
		middlePanel.add(zipField);
//		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		middlePanel.add(cellLabel);
		middlePanel.add(cellField);

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

//		innerPanel.add(leftPanel);
//		innerPanel.add(rightPanel);
//		middlePanel.add(innerPanel, BorderLayout.NORTH);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		lowerPanel.setLayout(fl);
		addButton = new JButton("Create New Member");
		addNewMemberButtonListener(addButton);
		lowerPanel.add(addButton);
	}

	private void addNewMemberButtonListener(JButton addButton) {
		addButton.addActionListener(event -> {
			String id = idField.getText();
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String street = streetField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zip = zipField.getText();
			String cell = cellField.getText();

			try {
				controllerInterface.addMember(id, firstName, lastName, cell, street, city, state, zip);
				displayInfo("Successfully added new Library member!");
			} catch (LibrarySystemException e) {
				displayError("Error! " + e.getMessage());
			}
		});
	}

	@Override
	public void updateData() {
	}
}
