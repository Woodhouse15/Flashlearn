import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class SignUpGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private static String username = "";
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
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
	public SignUpGUI() {
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

		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblSignUp.setBounds(420, 32, 246, 131);
		contentPane.add(lblSignUp);

		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 47));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterButtonPressed();
			}
			});
		btnEnter.setBounds(140, 546, 184, 65);
		contentPane.add(btnEnter);

		JLabel lblEnterYourUsername = new JLabel("Enter a username");
		lblEnterYourUsername.setForeground(Color.WHITE);
		lblEnterYourUsername.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEnterYourUsername.setBounds(179, 196, 381, 65);
		contentPane.add(lblEnterYourUsername);

		JLabel lblEnterYourPassword = new JLabel("Enter your password");
		lblEnterYourPassword.setForeground(Color.WHITE);
		lblEnterYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEnterYourPassword.setBounds(179, 370, 381, 49);
		contentPane.add(lblEnterYourPassword);
		
		JLabel lblpasswordAdviser = new JLabel("(must be at least 8 characters long)");
		lblpasswordAdviser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblpasswordAdviser.setForeground(Color.WHITE);
		lblpasswordAdviser.setBounds(549, 373, 333, 59);
		contentPane.add(lblpasswordAdviser);
		
		JLabel Background = new JLabel("New label");
		Background.setIcon(new ImageIcon("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Background.png"));
		Background.setBounds(0, 0, 1084, 661);
		contentPane.add(Background);
	}
	public void backButtonPressed() {
		MainMenuGUI obj = new MainMenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void enterButtonPressed() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "INSERT INTO Users (Username, Password) VALUES(?,?)";//SQL statement (Username, Password) is where the statements will be put in the database
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String usernames = txtusername.getText(); //Assigning the data to variables
			String password = txtpassword.getText();
			boolean digits = false;
			//Checking whether the password contains a digit
			for(char c : password.toCharArray()) {
			    if(Character.isDigit(c)) {
			        digits = true;
			    }
			}
			if(password.length()>=8 && digits == true) {
				preparedStatement.setString(1,usernames);//Inserting into the first question mark
				preparedStatement.setString(2,password);//Inserting into the second question mark
				preparedStatement.executeUpdate();
				System.out.println("Data inserted successfully");
			}
			username = usernames;
		}catch(Exception e) {
			System.out.println("Error in insert");
		}
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public static String usernameReturn() {
		//Returns the username for use in other interfaces
		return username;
	}
}
