package dbController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dataObject.*;


public class ContentDataController {

	static private Connection con;
	
	public ContentDataController(){
	}
	
	
	public  DataObject getData() {
		DataObject data = new DataObject();
		Table table = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from data_source");
			table = populateTable(rs);
			data.addTable(table.copy());
			table.print();
			table.clear();
			table.print();
			rs = stmt.executeQuery("Select * from data_target");
			table = populateTable(rs);
			table.print();
			data.addTable(table.copy());
		}catch(Exception e) {e.printStackTrace();}
		finally {try { con.close();}catch(Exception e) {e.printStackTrace();}}
		
		return data;
	}
	
	
	
	  Table populateTable(ResultSet rs) throws SQLException {
		Table table =new Table();
		while(rs.next()) {
			//populate each row, start at 2 because first value is the counter column
			int index = 2; 
			String value = rs.getString(index); //get first value
			while(value != null) { //while we still getting values => row not empty => continue
				table.addValueRow(value); // store new values in a temp row to be inserted later on
				index++;
				try {value = rs.getString(index); //new value to see if we continue looping
				}catch(Exception e) {break;}
			}
			table.insertRow(); //insert all the new values collected into table as a new row	
		}
		return table;	
	}
	 
	
	
	public void storeData(DataObject data1) {
		storeData(data1.tables.get(0), "data_source");
		storeData(data1.tables.get(1), "data_target");
	}
	
	
	public void storeData( Table table, String tableName) {
		//getting current date 
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ? VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");	
			
			ps.setString(1, tableName); //set first value PRIMARY KEY = current date
			ps.setString(2, date); //set first value(index 2) of query to PRIMARY KEY = current date
			
			int index = 2;
			for (Row row: table.values) { 
				//each country in the data object
				for(String value: row.values) {
					// each field value for each country
					index++;
					ps.setString(index, value);
				}	
			}
			
			ps.execute();		
		}catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	public boolean haveWeScrapped() {
		boolean bool = false;
	
		//getting current date 
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM data_source ORDER BY num DESC LIMIT 1;");
			while(rs.next()) {				
				if(rs.getString(2).equals(date)) {bool = true;}}
				
		}catch(Exception e) {e.printStackTrace();}
		finally {try { con.close();}catch(Exception e) {e.printStackTrace();}}
		return bool;
	}
	
	
	
	
	
	public DataObject getLastRecord() throws SQLException {
		DataObject data = new DataObject(); //data holder (2 tables)
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM data_source ORDER BY num DESC LIMIT 1;");
			data.addTable(populateTable(rs));
			rs = stmt.executeQuery("SELECT * FROM data_target ORDER BY num DESC LIMIT 1;");
			data.addTable(populateTable(rs));
				
		}catch(Exception e) {e.printStackTrace();}
		finally {try { con.close();}catch(Exception e) {e.printStackTrace();}}
		return data;
	}


			

		
	
	

}



