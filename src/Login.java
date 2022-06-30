import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

public class Login extends JFrame {

	private final ControllerInterface sc = SystemController.INSTANCE;

	private JPanel contentPanel;
	private JTextField username;
	private JTextField password;
	static Login frame = new Login();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uID = username.getText().trim();
				String pwd = password.getText().trim();
				if (uID.length() == 0 || pwd.length() == 0) {
//					displayError("Id and Password fields must be nonempty");
					System.out.println("Id and Password fields must be nonempty");
				} else {
					try {
						sc.login(uID, pwd);
//						updateLeftPanel(SystemController.currentAuth);
//						displayInfo("Login successful");
//						librarySystem.repaint();
						frame.dispose();
						Main.entry();
					} catch (Exception ex) {
//						displayError("Error! " + e.getMessage());
						ex.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setBounds(308, 144, 117, 29);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(216, 64, 80, 16);
		contentPanel.add(lblNewLabel);

		username = new JTextField();
		username.setBounds(308, 59, 130, 26);
		contentPanel.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(216, 111, 80, 16);
		contentPanel.add(lblNewLabel_1);

		password = new JTextField();
		password.setBounds(308, 106, 130, 26);
		contentPanel.add(password);
		password.setColumns(10);
	}

}
