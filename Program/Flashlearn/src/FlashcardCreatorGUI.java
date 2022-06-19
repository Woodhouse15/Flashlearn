import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FlashcardCreatorGUI extends JFrame {

	private JPanel contentPane;
	private JTextField termField;
	private JTextField definitionField;
	private JTextField titleField;
	private String title = "placeholder";
	private String user = LoginGUI.usernameReturn();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlashcardCreatorGUI frame = new FlashcardCreatorGUI();
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
	public FlashcardCreatorGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Insert title below");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		titleLabel.setBounds(5, 5, 1076, 29);
		contentPane.add(titleLabel);
		//Creating a placeholder table for the data to be inserted into
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "CREATE TABLE "+title+"(ID AUTOINCREMENT, Term text, Definition text, TimesStudied number, Incorrect number)";//Table with five columns created
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Table created successfully");
		}catch(Exception e) {
			System.out.println("Error in table creation");
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(231, 83, 850, 575);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel termLabel = new JLabel("Term:");
		termLabel.setBounds(0, 31, 65, 29);
		termLabel.setHorizontalAlignment(SwingConstants.CENTER);
		termLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(termLabel);
		
		termField = new JTextField();
		termField.setBounds(0, 90, 754, 60);
		panel.add(termField);
		termField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Definition:");
		lblNewLabel_1.setBounds(0, 156, 754, 29);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblNewLabel_1);
		
		definitionField = new JTextField();
		definitionField.setBounds(0, 215, 754, 60);
		panel.add(definitionField);
		definitionField.setColumns(10);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(0, 281, 754, 37);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
				termField.setText("");
				definitionField.setText("");
				JOptionPane.showMessageDialog(null,"Flashcard added");
			}
		});
		insertButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(insertButton);
		
		JButton exitButton = new JButton("Back");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		exitButton.setBounds(61, 318, 85, 27);
		contentPane.add(exitButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		saveButton.setBounds(61, 236, 85, 27);
		contentPane.add(saveButton);
		
		titleField = new JTextField();
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titleField.setBounds(350, 44, 393, 29);
		contentPane.add(titleField);
		titleField.setColumns(10);
	}
	public void insert() {
		String term = termField.getText();
		String definition = definitionField.getText();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "INSERT INTO "+title+" (Term, Definition, TimesStudied, Incorrect) VALUES(?,?,?,?)";//SQL statement (term,term) is where the statements will be put in the database
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,term);
			preparedStatement.setString(2,definition);//Inserting into the second question mark
			preparedStatement.setInt(3,0);
			preparedStatement.setInt(4,0);
			preparedStatement.executeUpdate();
			System.out.println("Data inserted successfully");
		}catch(Exception e) {
			System.out.println("Error in insert");
		}
	}
	public void save() {
		String userTitle = titleField.getText();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "ALTER TABLE "+title+" RENAME TO " + userTitle;//renaming the database created to the seleted name of the user
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Table renamed successfully");
		}catch(Exception e) {
			System.out.println("Error in rename");
		}
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "INSERT INTO FlashcardSets (SetName, Username) VALUES(?,?)";//SQL statement (term,term) is where the statements will be put in the database
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userTitle);//Inserting into the first question mark
			preparedStatement.setString(2,user);//Inserting into the second question mark
			preparedStatement.executeUpdate();
			System.out.println("Data inserted successfully");
		}catch(Exception e) {
			System.out.println("Error in insert");
		}
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void back() {
		//Deletion of the placeholder table and its data
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("drop table placeholder" ); //SQL statement selecting table to be deleted
			preparedStatement.executeUpdate();//Executing change
			System.out.println("Data deleted successfully");//success
			}catch (Exception e) {
			System.out.println("Error in deletion");//Bitter disappointment
			}
		//Returning to menu
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
}
