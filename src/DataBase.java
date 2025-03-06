import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {

	int idUser;
	String name;
	String lastName;
	String password;
	String email;
	String role;
	
	public void insertUser(String name, String lastName, String email, String password, int idUser) {
		
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try{
			
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			conn = DriverManager.getConnection(path, username, dbpassword);
			
			String insertSql = "Insert into user_log(id_user, name, last_name, gmail, password) values (?, ? , ?, ?, ?)";
			prepStmt = conn.prepareStatement(insertSql);
			
			prepStmt.setInt(1, idUser);
			prepStmt.setString(2, name);
			prepStmt.setString(3, lastName);
			prepStmt.setString(4, email);
			prepStmt.setString(5, password);
			
			int rows = prepStmt.executeUpdate();
			System.out.println(rows + " rows inserted into the database");
			
			
				} catch(SQLException e) {
					System.out.println("Couldn't insert the information "+ e.getMessage());
				} finally {
					
					try {
						if(prepStmt != null) {
							prepStmt.close();
						}
						
						if (conn != null) {
							conn.close();
						}
						
					} catch (SQLException e) {
						System.out.println("ERROR FOUND! Closing all resourses "+ e.getMessage());
					}
				}
	}

	
	public void insertRole (String roleName) {
		
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
		String path = "jdbc:mysql://localhost:3306/UserManagement";
		String username = "root";
		String dbpassword = "Darian0110*";
		
		conn = DriverManager.getConnection(path, username, dbpassword);
		
		String insertvalue = "insert into role_user (role_name) values(?) ";
		
		prepStmt = conn.prepareStatement(insertvalue);
		
		prepStmt.setString(1, roleName);
		
		int rowsAffected = prepStmt.executeUpdate();
		System.out.println(rowsAffected + " has been  affected");
		
		} catch (SQLException e) {
			System.out.println("Couldn't make the connection");
		} finally {
			
			try {
				if (prepStmt != null) {
					prepStmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				System.out.println("ERROR FOUND! Unable to connect "+ e.getMessage());
			}
		}
	}
	
	public void LogIn(String email, String password) {
		
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			conn = DriverManager.getConnection(path, username, dbpassword);
			
			String sqlValue = "select from user_log (gmail, password) where gmail = ? and password = ?";
			
			prepStmt = conn.prepareStatement(sqlValue);
			
			prepStmt.setString(1, email);
			prepStmt.setString(2, password);
			
			
			ResultSet rs = prepStmt.executeQuery();
			
			rs.close();
			
			if(rs.next()) {
				System.out.println("log in successful");
			} else {
				System.out.println("Invalid information, please check email or password");
			}
			
		} catch (SQLException e) {
			
			System.out.println("Unable to connect to the database: " + e.getMessage());
			
		} finally {
			
			try {
			if(prepStmt != null) {
				prepStmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
			
			
			} catch (SQLException e) {
				System.out.println("ERROR FOUND! unable to connect" + e.getMessage());
			}
		}
		
		
		
		
	}
	
	public void LogInHistory() {
		
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			conn = DriverManager.getConnection(path, username, dbpassword);
			
			String selectId = "select id_user from user_log where id = ?";
			prepStmt = conn.prepareStatement(selectId);
			prepStmt.setString(1, email);
			ResultSet rs = prepStmt.executeQuery();
			
			int idUser = -1;
			if(rs.next()) {
				idUser = rs.getInt("idUser");
			}
			
			if(idUser != -1) {
				String sqlQuery = "insert into session_history (id_user, login_time, logout_time) values ( ?, now(), null)";
				prepStmt = conn.prepareStatement(sqlQuery);
				prepStmt.setInt(1, idUser);
				prepStmt.executeUpdate();
			} else {
				System.err.println("Unable to find the user");
			}
			
			
		} catch(SQLException e) {
			
			System.out.println("ERROR! Couldn't save the information");
		} finally {
			
			try {
			if(conn != null) {
				conn.close();
			}
			
			if(prepStmt != null) {
				prepStmt.close();
			}
			
			} catch(SQLException e) {
				System.out.println("UNABLE TO MAKE THE CONNECTION! Clossing all resources " + e.getMessage());
			}
			
		}
	}

}
   