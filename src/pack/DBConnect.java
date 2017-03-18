package pack;

import java.sql.*;

public class DBConnect {
	private Connection con; //connect to MySQL DB
	private Statement st;  // query after records are created
	private ResultSet rs; // hold results
	private PreparedStatement ps; // action before records are created
	// Constructor set up link to database
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
	// Create New Table
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
	// Copy Table
	public void copyTable(String newTable, String oldTable) {
		try {
			String query1 = "create table newT like oldT";
			query1 = query1.replace("newT", newTable);
			query1 = query1.replace("oldT", oldTable);
			ps = con.prepareStatement(query1);
			ps.executeUpdate();
			String query2 = "insert newT select * from oldT";
			query2 = query2.replace("newT", newTable);
			query2 = query2.replace("oldT", oldTable);
			ps = con.prepareStatement(query2);
			ps.executeUpdate();			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	// Drop Table
	public void dropTable(String tname) {
		try {
			String query = "DROP TABLE tablename";
			query = query.replace("tablename", tname);
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	// Insert Records
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
	// Delete Record
	public void delRec(String tname, String idn) {
		try {
			String query = "delete from tablename where id=iden";
			query = query.replace("tablename", tname);
			query = query.replace("iden", idn);
			ps = con.prepareStatement(query);
			ps.executeUpdate();			
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	// Set Field Value
	public void setValue(String tname, String colname, String val, String idn) {
		try {
			String query = "UPDATE tablename SET cname=value WHERE id=iden";
			query = query.replace("tablename", tname);
			query = query.replace("cname", colname);
			query = query.replace("value", val);
			query = query.replace("iden", idn);
			ps = con.prepareStatement(query);
			ps.executeUpdate();			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	// Drop Column
	public void dropCol(String tname, String cname) {
		try {
			String query = "ALTER TABLE tablename DROP colname";
			query = query.replace("tablename", tname);
			query = query.replace("colname", cname);
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	// Add Column
	public void addCol(String tname, String cname, String constraint) {
		try {
			String query = "ALTER TABLE tablename ADD colname constr";
			query = query.replace("tablename", tname);
			query = query.replace("colname", cname);
			query = query.replace("constr", constraint);
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	// Get Records
	public void getData(String tname) {
		try {
			String query = "select * from tablename";
			query = query.replace("tablename", tname);
			rs = st.executeQuery(query);
			System.out.println("Results from DB myjdb");
			while(rs.next()) {
				String iden = rs.getString("id");
				String firstname = rs.getString("first");
				String lastname = rs.getString("last");
				System.out.println(iden+" "+firstname+" "+lastname);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
