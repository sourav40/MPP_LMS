package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.LibrarySystem;
import util.DialogMessage;

public class CheckoutBookPanel implements MessageableWindow {
	ControllerInterface sc = SystemController.INSTANCE;

	private final JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;

	private JTextField isbnField;
	private JTextField memberIdField;

	private JPanel innerPanel;

	private JButton checkoutButton;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public CheckoutBookPanel() {
		mainPanel = new JPanel();
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		BorderLayout bl = new BorderLayout();
//		bl.setVgap(30);
		mainPanel.setLayout(bl);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		JLabel label = new JLabel("Checkout Book");
		Util.adjustLabelFont(label, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(label);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(null);

		JLabel memberIdLabel = new JLabel("Member ID");
		memberIdLabel.setBounds(30, 90, 200, 10);

		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(30, 150, 200, 10);

		memberIdField = new JTextField();
		isbnField = new JTextField();
		memberIdField.setBounds(250, 70, 250, 50);
		isbnField.setBounds(250, 130, 250, 50);

		middlePanel.add(memberIdLabel);
		middlePanel.add(memberIdField);

		middlePanel.add(isbnLabel);
		middlePanel.add(isbnField);
		checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(245, 200, 150, 25);
		addCheckoutButtonListener(checkoutButton);
		middlePanel.add(checkoutButton);

	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		lowerPanel.setLayout(fl);
//		checkoutButton = new JButton("Checkout");
//		addCheckoutButtonListener(checkoutButton);
//		lowerPanel.add(checkoutButton);
	}

	private void addCheckoutButtonListener(JButton checkoutBtn) {
		checkoutBtn.addActionListener(evt -> {
			String bkISBN = isbnField.getText().trim();
			String memberID = memberIdField.getText().trim();

			if (bkISBN.length() == 0 || memberID.length() == 0) {
				DialogMessage.showDialog(LibrarySystem.INSTANCE, "ISBN and Member ID may not be empty",
						DialogMessage.ERROR);
			} else {
				try {
					sc.checkoutBook(memberID, bkISBN);
					DialogMessage.showDialog(LibrarySystem.INSTANCE, "Checkout successful", DialogMessage.INFO);
				} catch (LibrarySystemException e) {
					DialogMessage.showDialog(LibrarySystem.INSTANCE, e.getMessage(), DialogMessage.ERROR);
				}
			}
		});
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

}
