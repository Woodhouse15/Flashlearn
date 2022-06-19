import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class FlashcardMenuGUI extends JFrame {
	//Carrying over the choice made in the previous GUI to this GUI
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlashcardMenuGUI frame = new FlashcardMenuGUI();
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
	public FlashcardMenuGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		String set = MenuGUI.choice;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel setName = new JLabel(set);
		setName.setBounds(10, 21, 259, 103);
		setName.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(setName);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editButtonPressed();
			}
		});
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editButton.setBounds(90, 167, 85, 37);
		contentPane.add(editButton);
		
		JButton deleteButton = new JButton("Delete set");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
					System.out.println("Connected Successfully");//Database connection
					PreparedStatement preparedStatement = connection.prepareStatement("drop table " + set ); //SQL statement selecting area where item is deleted
					preparedStatement.executeUpdate();//Executing change
					System.out.println("Data deleted successfully");//success
					}catch (Exception f) {
					System.out.println("Error in deletion");//Bitter disappointment
					}
				deleteButtonPressed();
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deleteButton.setBounds(66, 342, 128, 37);
		contentPane.add(deleteButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(279, 5, 802, 653);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton learnButton = new JButton("Learn");
		learnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				learnButtonPressed();
			}
		});
		learnButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		learnButton.setBounds(90, 251, 85, 37);
		contentPane.add(learnButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButtonPressed();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backButton.setBounds(90, 530, 85, 37);
		contentPane.add(backButton);
		
		
		//Creating arraylists to store data from the database for use
		ArrayList<String> terms = new ArrayList<String>();
		ArrayList<String> definitions = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+set); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Term=resultset.getString("Term"); //Column names, not actual data items
				String Definition=resultset.getString("Definition"); //This searches through data items and returns those in these columns
				//Adding the items to their arraylists
				terms.add(Term);
				definitions.add(Definition);
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		int length = terms.size();
		//Creating Jlabels
		JLabel[] flashcardSet = new JLabel[length];
		for(int i=0; i<=length-1;i++) {
			//Getting the data from the arraylists and assigning each one a JLabel
			flashcardSet[i] = new JLabel(terms.get(i) + " = " + definitions.get(i));
			flashcardSet[i].setFont(new Font("Tahoma", Font.PLAIN, 24));
			flashcardSet[i].setSize(80, 80);
			//Adding the JLabels to the panel displayed in the GUI
			panel.add(flashcardSet[i], BorderLayout.CENTER);
		}
	}
	public void backButtonPressed() {
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void learnButtonPressed() {
		LearnMode obj = new LearnMode();
		obj.setVisible(true);
		this.dispose();
	}
	public void editButtonPressed() {
		FlashcardEditorGUI obj = new FlashcardEditorGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void deleteButtonPressed() {
		String set = MenuGUI.choice;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connect = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			PreparedStatement prepStatement = connect.prepareStatement("DELETE FROM FlashcardSets WHERE SetName = ?"); //SQL statement selecting area where item is deleted
			prepStatement.setString(1,set);
			prepStatement.executeUpdate();//Executing change
			System.out.println("Data deleted successfully");//success
			}catch (Exception e) {
			System.out.println("Error in deletion");//Bitter disappointment
			}
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public static String setName() {
		String set = MenuGUI.choice;
		return set;
	}
}
