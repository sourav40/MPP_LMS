package main;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.ControllerInterface;
import business.SystemController;
import util.DialogMessage;
import javax.swing.JTextArea;
import java.awt.Font;

public class Login extends JFrame {

	private final ControllerInterface sc = SystemController.INSTANCE;

	private JPanel contentPanel;
	private JTextField username;
	static Login frame = new Login();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setTitle("MIU LMS");
					centerFrameOnDesktop(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(650, 550);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uID = username.getText().trim();
				String pwd = passwordField.getText().trim();
				if (uID.length() == 0 || pwd.length() == 0) {
					DialogMessage.showDialog(frame, "Id and Password fields must be nonempty", "Warning");
				} else {
					try {
						sc.login(uID, pwd);
						frame.dispose();
						Main.entry();
					} catch (Exception ex) {
						ex.printStackTrace();
						DialogMessage.showDialog(new JFrame(), "Invalid credentials.", "Error");
					}
				}

			}
		});
		btnNewButton.setBounds(501, 209, 117, 29);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(409, 138, 80, 16);
		contentPanel.add(lblNewLabel);

		username = new JTextField();
		username.setBounds(501, 133, 130, 26);
		contentPanel.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(409, 176, 80, 16);
		contentPanel.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(501, 171, 130, 26);
		contentPanel.add(passwordField);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/docs/images/MicrosoftTeams-image.jpeg")));
		lblNewLabel_2.setBounds(0, -12, 377, 534);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("MIU");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_3.setBounds(482, 31, 61, 16);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Library Management");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_4.setBounds(423, 47, 171, 16);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("System");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_5.setBounds(482, 67, 61, 16);
		contentPanel.add(lblNewLabel_5);
	}
}
