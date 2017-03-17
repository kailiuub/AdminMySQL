package pack;

public class Main {

	public static void main(String[] args) {
		DBConnect connect = new DBConnect();
		connect.getData(); // retrieve data from table existing in DB
		connect.createTable("table2"); // create a new table in DB
		connect.insertRecord("table2"); // insert records
		
	}

}
