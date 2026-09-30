package pack1;

import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataContext {

	
	public Connection getConnected() throws SQLException {
		 Properties props = new Properties();
		    try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
		        if (in != null) {
		            props.load(in);
		        } else {
		            throw new RuntimeException("db.properties dosyası bulunamadı!");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return DriverManager.getConnection(
		        props.getProperty("db.url"),
		        props.getProperty("db.user"),
		        props.getProperty("db.password")
		    );
	}
	
	public ResultSet getResultSet() throws SQLException {
		Statement st = getConnected().createStatement();
		return st.executeQuery("select * from users");
	}
	
	public ArrayList<User> getUsers() throws SQLException{
		ArrayList<User> tempUsers = new ArrayList<>();
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from users");
		while(rs.next()) {
			User u = new User();
			u.setUserId(rs.getInt(1));
			u.setName(rs.getString(2));
			u.setSurname(rs.getString(3));
			u.setUsername(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setUserRole(rs.getString(6));
			tempUsers.add(u);
		}
		return tempUsers;
	}
	
	public int saveUser(User u) {
	    String sql = "INSERT INTO users (name, surname, username, password, userRole) VALUES (?, ?, ?, ?, ?)";
	    int generatedId = 0;
	   
	    try (Connection conn = getConnected();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        ps.setString(1, u.getName());
	        ps.setString(2, u.getSurname());
	        ps.setString(3, u.getUsername());
	        ps.setString(4, u.getPassword());
	        ps.setString(5, u.getUserRole());
	        ps.executeUpdate();
	        
	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                generatedId = rs.getInt(1); 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return generatedId; 
	}
	
	public void updateUser(int id, String newRole) throws SQLException {
		String query = "update users set userRole=? where userId=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, newRole);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void deleteUser(int id) throws SQLException {
		String query = "delete from users where userId=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	
	
}
