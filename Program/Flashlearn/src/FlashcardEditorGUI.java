import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class FlashcardEditorGUI extends JFrame {

	private JPanel contentPane;
	public String setName = MenuGUI.choice;
	private static JTextField txtSetname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlashcardEditorGUI frame = new FlashcardEditorGUI();
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
	public FlashcardEditorGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 105, 921, 553);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel Term = new JLabel("Term");
		Term.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(Term);
		
		JLabel lblNewLabel = new JLabel("Definition");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblNewLabel);
		
		
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		backButton.setBounds(10, 454, 104, 37);
		contentPane.add(backButton);	
		
		txtSetname = new JTextField();
		txtSetname.setText(setName);
		txtSetname.setFont(new Font("Tahoma", Font.PLAIN, 36));
		txtSetname.setBounds(327, 44, 500, 37);
		contentPane.add(txtSetname);
		txtSetname.setColumns(10);
		
		ArrayList<String> lables = (ArrayList<String>) flashcards().clone();
		int length = lables.size();
		//Making enough JTextFields for each data item in the arraylist returned in flashcards()
		JTextField[] cardEditor = new JTextField[length];
		for(int i=0; i<=length-1;i++) {
			//Assigning the data items to each JTextfield
			cardEditor[i] = new JTextField(lables.get(i));
			cardEditor[i].setFont(new Font("Tahoma", Font.PLAIN, 24));
			cardEditor[i].setSize(80, 80);

			//Adding the JButtons to the panel displayed in the GUI
			panel.add(cardEditor[i]);
		}
		//Creating the save button
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		saveButton.setBounds(10, 246, 104, 37);
		contentPane.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<cardEditor.length;i++) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
						System.out.println("Connected Successfully");//Database connection
						PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName); //SQL statement selecting everything 
						ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
						while(resultset.next()) {
							String Term=resultset.getString("Term"); //Column names, not actual data items
							String Definition=resultset.getString("Definition");
							if((cardEditor[i].getText()).equals(Term)) {
								try{
						            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						            Connection connect= DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
						            System.out.println("Connected Successfully");
						            //Using SQL UPDATE query to update data in the table
						            PreparedStatement prepStatement=connect.prepareStatement("update"+setName+" set Term=? where ID="+i);
						            prepStatement.setString(1,cardEditor[i].getText());
						            prepStatement.executeUpdate();
						            System.out.println("data updated successfully");
						        }catch(Exception g){
						            System.out.println("Error in connection");
						        }
							}if((cardEditor[i+1].getText()).equals(Definition)) {
								try{
						            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						            Connection connected= DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
						            System.out.println("Connected Successfully");
						            //Using SQL UPDATE query to update data in the table
						            PreparedStatement prepState=connected.prepareStatement("update"+setName+" set Definition=? where ID="+i);
						            prepState.setString(1,cardEditor[i+1].getText());
						            prepState.executeUpdate();
						            System.out.println("data updated successfully");
						        }catch(Exception g){
						            System.out.println("Error in connection");
						        }
							}
							i++;
						}
					} catch (Exception f) {
						System.out.println("Error in retrieval");
				}
				}
				save();
			}
		});
	}
	public ArrayList<String> flashcards() { //Returning an arraylist containing the contents of the flashcard set
		ArrayList<String> cards = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Term=resultset.getString("Term"); //Column names, not actual data items
				String Definition = resultset.getString("Definition");
				cards.add(Term); //Adding each item into the arraylist
				cards.add(Definition);
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		return cards; //Returns arraylist for use
	}
	public void save() {
		String userTitle = txtSetname.getText();
		if(userTitle!=setName) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
				Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
				System.out.println("Connected Successfully");
				String sql = "ALTER TABLE "+setName+" RENAME TO " + userTitle;//renaming the database created to the seleted name of the user
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Table renamed successfully");
			}catch(Exception e) {
				System.out.println("Error in rename");
			}
		}
		
	}
	public void back() {
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
}
