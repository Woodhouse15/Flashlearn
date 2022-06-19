import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class MenuGUI extends JFrame {
	private String User = LoginGUI.usernameReturn();
	public static String choice;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI frame = new MenuGUI();
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
	public MenuGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		if(User.equals(null)) {
			//If no username is returned from the login page, the username must be called from the sign up page
			User = SignUpGUI.usernameReturn();
		}
		//Adding a scrollpane to enable scrolling if the information exceeds the size of the window
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		//Adding the panel to the scrollframe
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		//Ensuring the user is accessing their account
		JLabel Username = new JLabel("Welcome "+User);
		panel.add(Username);
		Username.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton Logout = new JButton("Log out");
		Logout.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(Logout);
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
			});
		
		//Button to add a new flashcard set
		JButton Addition = new JButton("Add new set");
		panel.add(Addition);
		Addition.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Addition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newSet();
			}
			});
		ArrayList<String> Lables = (ArrayList<String>) buttonLabels().clone();
		
		int length = Lables.size();
		//Making enough JButtons for each data item in the arraylist returned in buttonLabels()
		JButton[] buttons = new JButton[length];
		for(int i=0; i<=length-1;i++) {
			//Assigning the data items to each JButton
			buttons[i] = new JButton(Lables.get(i));
			buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 24));
			buttons[i].setSize(80, 80);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//The choice is the text on the JButton pressed
					choice = e.getActionCommand();
					buttonPressed();
					}
				});
			//Adding the JButtons to the panel displayed in the GUI
			panel.add(buttons[i]);
		}
	}
	public ArrayList<String> buttonLabels() {
		//Getting all of the flashcard sets associated with the user and storing them in an arraylist
		ArrayList<String> array = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from FlashcardSets WHERE Username=?"); //SQL statement selecting everything 
			preparedStatement.setString(1,User);//Inserting into the question mark
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Sets=resultset.getString("SetName"); //Column names, not actual data items
				array.add(Sets); //Adding each item into the arraylist
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		return array; //Returns arraylist for use
	}
	public void buttonPressed() {
		buttonLabels().clear(); //Clearing the array for use again
		FlashcardMenuGUI obj = new FlashcardMenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void logout() {
		LoginGUI obj = new LoginGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void newSet() {
		FlashcardCreatorGUI obj = new FlashcardCreatorGUI();
		obj.setVisible(true);
		this.dispose();
	}

}
