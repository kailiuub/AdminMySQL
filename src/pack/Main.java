package pack;

public class Main {

	public static void main(String[] args) {
		try {
			
			DBConnect connect = new DBConnect();
			String[] arr = {"table1", "table2", "table3" , "table4"};
			for (String x : arr) {
				connect.dropTable(x);
			}
			
			connect.createTable("table1"); // create a new table in DB
			connect.insertRecord("table1"); // insert records
			connect.getData("table1");      
			
			connect.delRec("table1","3");  // delete record based on id
			connect.getData("table1");
			
			connect.copyTable("table2","table1");  // copy data to another table
			connect.getData("table2");
			
			connect.setValue("table2", "first", "\"Deltagamma\"", "1");  // set value of the field
			connect.getData("table2");
			
			connect.copyTable("table3","table1");  // copy data to another table
			//connect.dropCol("table3", "last");
			connect.getData("table3");
			
			connect.copyTable("table4","table1");
			connect.addCol("table4", "middle", "VARCHAR(10) NOT NULL AFTER first");
			connect.setValue("table4", "middle", "\"P.\"", "1");
			connect.getData("table4");
						
			connect.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			System.out.println("Running is done");
		}
	}

}
