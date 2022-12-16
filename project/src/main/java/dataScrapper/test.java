package dataScrapper;
import dataScrapper.*;
import dataObject.*;
import dbController.*;
import java.sql.SQLException;


public class test {
	
	public static void main (String args[]) {
		DataObject data = new DataObject();
		ContentDataController controller = new ContentDataController();
		try {
			data = controller.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Table sourceTable = data.table(0);
		Table targetTable = data.table(1); 
		sourceTable.print();

		
	}
	
}
