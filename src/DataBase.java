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
		
		UserValidation userValidation = new UserValidation();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			conn = DriverManager.getConnection(path, username, dbpassword);
			
			String sqlValue = "select password from user_log where gmail = ?";
			
			prepStmt = conn.prepareStatement(sqlValue);
			
			prepStmt.setString(1, email);
			
			ResultSet rs = prepStmt.executeQuery();
			
			
			if(rs.next()) {
				
				String hashedPassword = rs.getString("password");
				
				if(userValidation.viewPassword(password, hashedPassword)) {
					String updateTime = "update user_log set last_login = now() where gmail = ?";
					prepStmt = conn.prepareStatement(updateTime);
					prepStmt.setString(1, email);
					prepStmt.executeUpdate();
					System.out.println("Log in successful.");
				} else {
					System.out.println("Invalid information, unable to log in.");
				}

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
	
	public void LogOut(String email) {
		 Connection conn = null;
		 PreparedStatement prepStmt = null;
		 
		 try{
			 
			String path = "jdbc:mysql://localhost:3306/UserManagement";
			String username = "root";
			String dbpassword = "Darian0110*";
			
			conn = DriverManager.getConnection(path, username, dbpassword);
			
			String sqlUpdate = "update user_log set last_logout = now() where gmail = ?";
			
			prepStmt = conn.prepareStatement(sqlUpdate);
			
			prepStmt.setString(1, email);
			int rows = prepStmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Successfully logged out: " + email);
			} else {
				System.out.println("No active session: " + email);
			}
			 
		 } catch(SQLException e) {
			 System.out.println("ERROR! Unable to process the request.");
		 } finally {
			try {
				 if(prepStmt != null) {
					 prepStmt.close();
				 }
				 
				 if (conn != null) {
					 conn.close();
				 }
 
			} catch(SQLException e) {
				System.out.println("SOMETHING WENT WRONG! Clossing the connection with the database.");
			}
		 }
		 
	}
}