import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {


	public void insertUser(String name, String lastName, String email, String password, int idUser) {
		try{
			
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			Connection conn = DriverManager.getConnection(path, username, dbpassword);
			
			String insertSql = "Insert into user_log(id_user, name, last_name, gmail, password) values (?, ? , ?, ?, ?)";
			PreparedStatement prepStmt = conn.prepareStatement(insertSql);
			prepStmt.setInt(1, idUser);
			prepStmt.setString(2, name);
			prepStmt.setString(3, lastName);
			prepStmt.setString(4, email);
			prepStmt.setString(5, password);
			
			prepStmt.close();
			
				} catch(SQLException e) {
					System.out.println("Couldn't make the connection");
				}
	}


}
   