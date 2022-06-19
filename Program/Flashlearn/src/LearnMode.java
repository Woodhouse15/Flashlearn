import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LearnMode extends JFrame {

	private JPanel contentPane;
	public String setName = FlashcardMenuGUI.setName();
	public static int questionNumber = 1;
	private JTextField answerField;
	private String questionTerm = returnQuestion();
	public static int questionsCorrect = 0;
	public static ArrayList<String> incorrect = QuestionReservoir.incorrect;
	public static ArrayList<String> remainder = QuestionReservoir.remainder;
	public static ArrayList<String> unstudied = QuestionReservoir.unstudied;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnMode frame = new LearnMode();
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
	public LearnMode() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 36));
		exitButton.setBounds(46, 467, 108, 47);
		contentPane.add(exitButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 117, 876, 536);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel question = new JLabel(questionTerm);
		question.setFont(new Font("Tahoma", Font.PLAIN, 24));
		question.setBounds(58, 60, 769, 52);
		panel.add(question);
		
		answerField = new JTextField();
		answerField.setToolTipText("Enter answer here");
		answerField.setBounds(58, 239, 769, 69);
		panel.add(answerField);
		answerField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(58, 347, 112, 37);
		panel.add(btnNewButton);
		
		JLabel QuestionNumber = new JLabel("Question: "+questionNumber);
		QuestionNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		QuestionNumber.setBounds(42, 139, 148, 134);
		contentPane.add(QuestionNumber);
		
		JLabel FlashcardTitle = new JLabel(setName);
		FlashcardTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		FlashcardTitle.setBounds(520, 56, 45, 13);
		contentPane.add(FlashcardTitle);
	}
	
	public String returnQuestion() { //Returning the actual question used
		String Question = "";
		if (unstudied.size()!= 0) {
			Random r = new Random();
	        int randomNum = r.nextInt(((unstudied.size()-1) - 0) + 1) + 0; //Creating a random number within the bounds of the arraylist
	        Question = unstudied.get(randomNum);//Assigning the question to the string returned
	        unstudied.remove(randomNum);
		}if(incorrect.size()!=0) {
			Random r = new Random();
	        int randomNum = r.nextInt(((incorrect.size()-1) - 0) + 1) + 0; //Creating a random number within the bounds of the arraylist
	        Question = incorrect.get(randomNum); //Assigning the question to the string returned
	        incorrect.remove(randomNum);
		}else {
			if(remainder.size()!=0) {
				Random r = new Random();
		        int randomNum = r.nextInt(((remainder.size()-1) - 0) + 1) + 0; //Creating a random number within the bounds of the arraylist
		        Question = remainder.get(randomNum); //Assigning the question to the string returned
		        remainder.remove(randomNum);
			}
		}
		return Question;
	}
	public void exit() {
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void enter() {
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
            System.out.println("Connected Successfully");
            //Using SQL UPDATE query to update data in the table
            PreparedStatement preparedStatement=connection.prepareStatement("update"+setName+" set TimesStudied=TimesStudied+1 where Term=?");
            preparedStatement.setString(1,questionTerm);
            preparedStatement.executeUpdate();
            System.out.println("data updated successfully");
        }catch(Exception e){
            System.out.println("Error in connection");
        }
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName+" where Term=?"); //SQL statement selecting everything
			preparedStatement.setString(1,questionTerm);
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Definition=resultset.getString("Username"); //Column names, not actual data items
				if((answerField.getText()).equals(Definition)) {
					correct();
				}else {
					incorrect();
				}
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
	}
	public static int returnNumberCorrect(){
		return questionsCorrect;
	}
	public static int returnTotal() {
		return questionNumber;
	}
	public void correct() {
		questionsCorrect++;
		while(unstudied.size()!=0&&incorrect.size()!=0 && remainder.size()!=0) {
			LearnMode obj = new LearnMode();
			obj.setVisible(true);
			this.dispose();
		}
		ResultsGUI obj = new ResultsGUI();
		obj.setVisible(true);
		this.dispose();
	}
	public void incorrect() {
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
            System.out.println("Connected Successfully");
            //Using SQL UPDATE query to update data in the table
            PreparedStatement preparedStatement=connection.prepareStatement("update"+setName+" set Incorrect=Incorrect+1 where Term=?");
            preparedStatement.setString(1,questionTerm);
            preparedStatement.executeUpdate();
            System.out.println("data updated successfully");
        }catch(Exception e){
            System.out.println("Error in connection");
        }
		LearnMode obj = new LearnMode();
		obj.setVisible(true);
		this.dispose();
	}
}
