import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserManagement {
	
	int idUser;
	String name;
	String lastName;
	String password = "Darian0110";
	String gmail = "ram.darian@gmail.com";
	int role;
	
	User user = new User(name, password, gmail, lastName);
	Scanner scanner = new Scanner(System.in);
	
	
	
	
	public String register(String gmail, String name, String lastName, String password) {
		System.out.println("**********************************");
		System.out.println("Welcome to our user management system");
		System.out.println("**********************************");
		System.out.println("To registe, please provide the following information: ");
		
		System.out.print("Enter your desired id (must begin with the current year: ");
		role = scanner.nextInt();
	
		System.out.print("Name: ");
		name = scanner.nextLine();
		
		if(name.equals(name)) {
			
		}
		
		System.out.print("Lastname: ");
		lastName = scanner.nextLine();
		
		System.out.print("Gmail: ");
		gmail = scanner.nextLine();
		
		System.out.print("Select your role: ");
		System.out.println("1- User");
		System.out.println("2- Moderator");
		System.out.println("3- Administrator");
		role = scanner.nextInt();
		
		switch(role) {
		case 1 -> System.out.println("You selected the user role");
		case 2 -> System.out.println("You selectd the moderator role");
		case 3 -> System.out.println("You selected the administrator role");
		default -> System.out.println("ERROR, please select an available rol");
		}
		
		System.out.print("Please create a password: ");
		password = scanner.nextLine();
		
		
		return "Welcome to our family!";
	}
	
	public String login(String gmail, String name, String password) {
		
		System.out.print("Email: ");
		gmail = scanner.nextLine();
		System.out.print("Password: ");
		password = scanner.nextLine();
		
		return "";
		
	}
	
	public String validation(){
		
		try( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserManagement", "root", "Darian0110*")){

			Statement statement = conn.createStatement();
		  ResultSet userLog = statement.executeQuery("Select * from user_log");
				} catch(SQLException e) {
					System.out.println("Couldn't make the connection");
				}
		
		return "";
		
	}
}
