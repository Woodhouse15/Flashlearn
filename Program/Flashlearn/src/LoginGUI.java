import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class LoginGUI extends JFrame {
	private JPanel contentPane;
	private JTextField txtusername;
	private static String username = "";
	private JPasswordField txtpassword;
	/**
	 ** Launch the application.
	 **/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButtonPressed();
			}
		});
		
		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 35));
		txtpassword.setBounds(179, 435, 294, 38);
		contentPane.add(txtpassword);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 47));
		btnBack.setBounds(843, 549, 184, 59);
		contentPane.add(btnBack);

		txtusername = new JTextField();
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 35));
		txtusername.setBounds(179, 260, 294, 37);
		contentPane.add(txtusername);
		txtusername.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblLogin.setBounds(453, 33, 178, 131);
		contentPane.add(lblLogin);

		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 47));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterButtonPressed();
			}
			});
		btnEnter.setBounds(140, 546, 184, 65);
		contentPane.add(btnEnter);

		JLabel lblEnterYourUsername = new JLabel("Enter your username");
		lblEnterYourUsername.setForeground(Color.WHITE);
		lblEnterYourUsername.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEnterYourUsername.setBounds(179, 196, 381, 65);
		contentPane.add(lblEnterYourUsername);

		JLabel lblEnterYourPassword = new JLabel("Enter your password");
		lblEnterYourPassword.setForeground(Color.WHITE);
		lblEnterYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEnterYourPassword.setBounds(179, 370, 377, 49);
		contentPane.add(lblEnterYourPassword);

		JLabel Background = new JLabel("New label");
		Background.setIcon(new ImageIcon("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Background.png"));
		Background.setBounds(0, 0, 1084, 661);
		contentPane.add(Background);
		}
	public void backButtonPressed() {
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void enterButtonPressed() {
		username = txtusername.getText();
		String password = txtpassword.getText();
		String Pass = "";
		try { //Searching for data
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			String sql = "SELECT Password FROM Users WHERE Username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql); //SQL statement selecting everything
			preparedStatement.setString(1,username);//Inserting into the question mark
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				Pass = resultset.getString("Password"); 
			}
			if(Pass.equals(password)) { //If the password entered is the same as the database the person logs in
				System.out.println("Logging in");
				MenuGUI obj = new MenuGUI();//Takes the user to the menu
				obj.setVisible(true);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Username or password incorrect");
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
	}
	public static String usernameReturn() {
		return username;
	}
}
