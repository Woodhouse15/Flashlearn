import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionReservoir {
	public static String setName = FlashcardMenuGUI.setName();
	public static ArrayList<String> remainder = new ArrayList<>(RemainderQuestions());
	public static ArrayList<String> unstudied = new ArrayList<>(UnstudiedQuestions());
	public static ArrayList<String> incorrect = new ArrayList<>(IncorrectQuestions());

	public static void main(String[] args) {
		//This class essentially allows the retrieval of questions from the database in question
	}
	public static ArrayList<String> RemainderQuestions() { //This selects and returns all questions that remain after the two other functions
		ArrayList<String> Remainingterms = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName+" where TimesStudied!=0 AND Incorrect<=1"); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Question=resultset.getString("Term"); //Column names, not actual data items
				Remainingterms.add(Question); //Adding results to arraylist
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		return Remainingterms;
	}
	public static ArrayList<String> UnstudiedQuestions() { //This selects and returns all questions that have been unstudied
		ArrayList<String> Unstudiedterms = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName+" where TimesStudied=0"); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Question=resultset.getString("Term"); //Column names, not actual data items
				Unstudiedterms.add(Question); //Adding results to arraylist
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		return Unstudiedterms;
	}
	public static ArrayList<String> IncorrectQuestions(){ //Getting the questions which have been answered incorrect the most
		ArrayList<String> IncorrectTerms = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from "+setName+"where Incorrect>1"); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Question=resultset.getString("Term"); //Column names, not actual data items
				IncorrectTerms.add(Question); //Adding results to arraylist
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
		return IncorrectTerms;
	}

}
