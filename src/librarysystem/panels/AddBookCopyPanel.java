package librarysystem.panels;

import business.Book;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.LibrarySystem;
import util.DialogMessage;

import javax.swing.*;
import java.awt.*;

public class AddBookCopyPanel implements MessageableWindow {
	private final ControllerInterface sc = SystemController.INSTANCE;
	private final JPanel mainPanel;
	private JPanel topPanel;
	private JPanel outerMiddle;

	private JTextField isbn;

	public AddBookCopyPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddle, BorderLayout.CENTER);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void clearData() {
		isbn.setText("");
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AddBookLabel = new JLabel("Add Book Copy");
		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(AddBookLabel);
	}

	public void defineOuterMiddle() {
		outerMiddle = new JPanel();

		outerMiddle.setLayout(null);

		// outerMiddle.setLayout(new BorderLayout());

		// // set up left and right panels

		// JPanel middlePanel = new JPanel();

		// FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);

		// middlePanel.setLayout(fl);

		// JPanel leftPanel = new JPanel();

		// JPanel rightPanel = new JPanel();

		// leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		// rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel bookIsbn = new JLabel("Enter ISBN");

		bookIsbn.setBounds(30, 90, 200, 10);

		isbn = new JTextField(10);

		isbn.setBounds(250, 70, 250, 50);

		// leftPanel.add(bookIsbn);

		// leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		outerMiddle.add(bookIsbn);

		outerMiddle.add(isbn);

		// rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

		// middlePanel.add(leftPanel);

		// middlePanel.add(rightPanel);

		// outerMiddle.add(middlePanel, BorderLayout.NORTH);

		// add button at bottom

		JButton addBookButton = new JButton("Add Book Copy");

		addBookButton.setBounds(245, 130, 150, 25);

		attachAddBookButtonListener(addBookButton);

		// JPanel addBookButtonPanel = new JPanel();

		// addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		outerMiddle.add(addBookButton);

		// outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);

	}

	private void attachAddBookButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			String bookId = isbn.getText();

			if (bookId.length() == 0) {
				displayError("Error!: ISBN should not be empty");
				DialogMessage.showDialog(LibrarySystem.INSTANCE, "Error!: ISBN should not be empty",
						DialogMessage.ERROR);
			} else {
				Book book = sc.getBookById(bookId);

				if (book != null) {
					book.addCopy();
					try {
						sc.updateBook(book);
						displayInfo("The book copy has been created");
						DialogMessage.showDialog(LibrarySystem.INSTANCE, "The book copy has been created",
								DialogMessage.INFO);
					} catch (LibrarySystemException e) {
						e.printStackTrace();
						displayError("Book does not exist");
						DialogMessage.showDialog(LibrarySystem.INSTANCE, "Book does not exist", DialogMessage.ERROR);
					}

				} else {
					displayError("Book does not exist");
					DialogMessage.showDialog(LibrarySystem.INSTANCE, "Book does not exist", DialogMessage.ERROR);
				}
			}

		});
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}
}
