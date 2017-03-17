package pack;

import java.sql.*;

public class DBConnect {
	private Connection con; //connect to MySQL DB
	private Statement st;  // query after records are created
	private ResultSet rs; // hold results
	private PreparedStatement ps; // action before records are created
	
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
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void insertRecord(String tname) {
		try{
			String query = "insert into tablename values (null, 'Jaky','Chen'),(null,'Mike','Johnson'),(null, 'Jenny', 'Lopez')";
			query = query.replace("tablename", tname);
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
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
