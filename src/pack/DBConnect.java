package pack;

import java.sql.*;

public class DBConnect {
	private Connection con; //connect to MySQL DB
	private Statement st;  // query
	private ResultSet rs; // hold results
	
	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myjdb","guest","A1B2C3D4");
			st=con.createStatement();		
		}
		catch (Exception e) {
			System.out.println("Error:"+e);
		}
	}
	
	public void createTable(String tname) {
		try {
			String query = "CREATE TABLE IF NOT EXISTS tablename (id int NOT Null AUTO_INCREMENT, first VARCHAR(20) NOT NULL, last VARCHAR(20) NOT NULL, PRIMARY KEY (id))";
			query = query.replace("tablename",tname);    // replace with user-defined table name
			System.out.println(query); 
			PreparedStatement create = con.prepareStatement(query);
			create.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("newtable1 was created");
		}
	}
	
	public void getData() {
		try {
			String query = "select * from newtable";
			rs = st.executeQuery(query);
			System.out.println("Results from DB myjdb");
			while(rs.next()) {
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				System.out.println(firstname+" "+lastname+"--"+email);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
