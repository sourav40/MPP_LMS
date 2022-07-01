package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.SystemController;
import librarysystem.LibrarySystem;
import util.DialogMessage;

public class AllBookIDs implements MessageableWindow {
	public JPanel getMainPanel() {
		return mainPanel;
	}

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
//	private TextArea textArea;
	private JTable table;
	private JPanel lowerPanel;
	private final ControllerInterface sc = SystemController.INSTANCE;

	public AllBookIDs() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineLowerPanel();
		defineMiddlePanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel titlesLabel = new JLabel("All Book IDs");
		Util.adjustLabelFont(titlesLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(titlesLabel);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		lowerPanel.setLayout(fl);
	}

	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);

		List<String[]> records = sc.getAllBookDetails();

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Book ISBN");
		tableModel.addColumn("Book Title");

		table = new JTable(tableModel);

		for (String[] rec : records) {
			tableModel.addRow(rec);
		}

		table.setPreferredSize(new Dimension(500, 300));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);

		middlePanel.add(table);
//		middlePanel.add(scrollPane);
		lowerPanel.add(scrollPane);
	}

	public void updateData() {
//		StringBuilder sb = new StringBuilder();
//		List<String> bookIds = sc.allBookIds();
//		Collections.sort(bookIds);
//		for (String s : bookIds) {
//			sb.append(s).append("\n");
//		}
//		textArea.setText(sb.toString());
//		mainPanel.repaint();
	}
}
