import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	{

	try( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserManagement", "root", "Darian0110*")){

		Statement statement = conn.createStatement();
	  ResultSet userLog = statement.executeQuery("Select * from user_log");
			} catch(SQLException e) {
				System.out.println("Couldn't make the connection");
			}
	}
}
