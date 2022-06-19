import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class MainMenuGUI extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI frame = new MainMenuGUI();
					frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
						}
				}
			});
		}
	public MainMenuGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFlashlearn = new JLabel("FlashLearn");
		lblFlashlearn.setForeground(Color.WHITE);
		lblFlashlearn.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblFlashlearn.setBounds(366, 88, 351, 131);
		contentPane.add(lblFlashlearn);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLoginPressed();
				}
			});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 47));
		btnLogin.setBounds(328, 447, 202, 65);
		contentPane.add(btnLogin);

		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 47));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSignUpPressed();
			}
			});
		btnNewButton.setBounds(608, 447, 202, 65);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Background.png"));
		lblNewLabel.setBounds(0, 0, 1084, 661);
		contentPane.add(lblNewLabel);

	}
	public void btnLoginPressed() {
		LoginGUI obj = new LoginGUI();
		obj.setVisible(true);
		this.dispose();
		}
	public void btnSignUpPressed() {
		SignUpGUI obj = new SignUpGUI();
		obj.setVisible(true);
		this.dispose();
	}
}
