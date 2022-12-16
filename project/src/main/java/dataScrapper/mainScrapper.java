package dataScrapper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import dataObject.*;
import dbController.ContentDataController;


public class mainScrapper {
	
	public static DataObject run() throws SQLException {
		DataObject data = new DataObject();
		ContentDataController controller = new ContentDataController();
		
		if(controller.haveWeScrapped()) { 
			data = controller.getLastRecord();
		}else {
			data = Scrapper.getData();
			controller.storeData(data);
		}
		
		data.print();
		return data;

	}
	
	

}





