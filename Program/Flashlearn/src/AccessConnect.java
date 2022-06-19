import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccessConnect {
	public static void main(String[] args) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FlashcardSets WHERE SetName=?"); //SQL statement selecting area where item is deleted
			preparedStatement.setString(1,"Geography");
			preparedStatement.executeUpdate();//Executing change
			System.out.println("Data deleted successfully");//success
			}catch (Exception e) {
			System.out.println("Error in deletion");//Pain
			}
		
	}	
	public static void insert() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");
			String sql = "INSERT INTO GCSEMaths (Term, Definition) VALUES(?,?)";//SQL statement (term,term) is where the statements will be put in the database
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,"Area of a square");//Inserting into the first question mark
			preparedStatement.setString(2,"A=bxh");//Inserting into the second question mark
			preparedStatement.executeUpdate();
			System.out.println("Data inserted successfully");
		}catch(Exception e) {
			System.out.println("Error in insert");
		}
	}
	public static void retrieve() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Users"); //SQL statement selecting everything 
			ResultSet resultset = preparedStatement.executeQuery(); //Creating ResultSet JAVA object
			while(resultset.next()) {
				String Username=resultset.getString("Username"); //Column names, not actual data items
				String Password=resultset.getString("Password"); //This searches through data items and returns those in these columns
				System.out.println(Username+" "+Password); //Printing results
			}
		} catch (Exception e) {
			System.out.println("Error in retrieval");
	}
	}
	public static void delete() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
			System.out.println("Connected Successfully");//Database connection
			PreparedStatement preparedStatement = connection.prepareStatement("delete from Users where Username=?"); //SQL statement selecting area where item is deleted
			preparedStatement.setString(1,"Testing");//Data item to be deleted
			preparedStatement.executeUpdate();//Executing change
			System.out.println("Data deleted successfully");//success
			}catch (Exception e) {
			System.out.println("Error in deletion");//Bitter disappointment
	}
	}
	public static void update() {
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://G:\\Ellie\\Work\\ProgrammingProject\\Program\\abc123.accdb");
            System.out.println("Connected Successfully");
            //Using SQL UPDATE query to update data in the table
            PreparedStatement preparedStatement=connection.prepareStatement("update Student set City=? where Username=?");
            preparedStatement.setString(1,"Kanke");
            preparedStatement.setString(2,"Mehtab");
            preparedStatement.executeUpdate();
            System.out.println("data updated successfully");

        }catch(Exception e){
            System.out.println("Error in connection");

        }
	}

}
