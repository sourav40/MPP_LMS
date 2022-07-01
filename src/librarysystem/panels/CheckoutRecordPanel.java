package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.LibrarySystem;
import util.DialogMessage;

public class CheckoutRecordPanel implements MessageableWindow {
	ControllerInterface sc = SystemController.INSTANCE;

	private JTable table;
	private JPanel topPanel;
	private JPanel lowerPanel;
	private JPanel middlePanel;
	private boolean tableDataSet;
	private final JPanel mainPanel;

	private JTextField memberIdField;

	private JButton searchButton;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public CheckoutRecordPanel() {
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
		topPanel = new JPanel();
		JLabel label = new JLabel("View Member Checkout Record");
		Util.adjustLabelFont(label, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(label);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel memberId = new JLabel("Member ID:");
		middlePanel.add(memberId, FlowLayout.LEFT);

		memberIdField = new JTextField(15);
		middlePanel.add(memberIdField, FlowLayout.CENTER);

		searchButton = new JButton("Search");
		searchButtonListener(searchButton);
		middlePanel.add(searchButton, FlowLayout.RIGHT);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		lowerPanel.setLayout(fl);
	}

	private void searchButtonListener(JButton searchBtn) {
		searchBtn.addActionListener(e -> {
			JScrollPane scrollPane;
			String memberID = memberIdField.getText().trim();
			if (memberID.length() == 0) {
				displayError("Member ID should not be empty");
				DialogMessage.showDialog(LibrarySystem.INSTANCE, "Member ID should not be empty", DialogMessage.ERROR);
			} else {
				try {
					List<String[]> records = sc.getMemberCheckoutEntries(memberID);
					if (records.size() == 0) {
						displayInfo("No book entries ");
						DialogMessage.showDialog(LibrarySystem.INSTANCE, "No book entries ", DialogMessage.INFO);
					} else {
						if (!tableDataSet) {
							DefaultTableModel tableModel = new DefaultTableModel();
							tableModel.addColumn("Member Id");
							tableModel.addColumn("Book");
							tableModel.addColumn("Copy Number");
							tableModel.addColumn("Checkout Date");
							tableModel.addColumn("Due Date");

							table = new JTable(tableModel);

							for (String[] rec : records) {
								tableModel.addRow(rec);
							}
							tableModel.addRow(new String[] {});
							tableModel.addRow(new String[] {});
							tableModel.addRow(new String[] {});
							tableModel.addRow(new String[] {});
							tableModel.addRow(new String[] {});
							tableModel.addRow(new String[] {});

							table.setPreferredScrollableViewportSize(table.getPreferredSize());
							table.setFillsViewportHeight(true);
							scrollPane = new JScrollPane(table);

							lowerPanel.add(scrollPane);
							tableDataSet = true;
						} else {
							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.setRowCount(0);

							for (String[] rec : records) {
								tableModel.addRow(rec);
							}

							table.setModel(tableModel);
						}
						displayInfo("Records retrieved successfully");
						DialogMessage.showDialog(LibrarySystem.INSTANCE, "Records retrieved successfully",
								DialogMessage.INFO);
					}
				} catch (LibrarySystemException ex) {
					displayError("Error!: " + ex.getMessage());
					DialogMessage.showDialog(LibrarySystem.INSTANCE, "Error!: " + ex.getMessage(), DialogMessage.ERROR);
				}
			}
		});
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}
}
