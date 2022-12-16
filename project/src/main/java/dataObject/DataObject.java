package dataObject;

import java.util.ArrayList;
import java.util.Arrays;



public class DataObject {

    public String [][] table1;
    public String [][] table2;
    public ArrayList<Table> tables;
    
    public DataObject() {
    	tables = new ArrayList<Table>();
    }

    
    public DataObject (String [][][] data){
    	Table table = new Table();
    	tables = new ArrayList<Table>();

    	for (int t = 0; t < data.length ; t ++) {
    	//for each table
    		
    		for (int r = 0; r < data[t].length ; r ++) {
    		//for each row
    			for (int c = 0; c < data[t][r].length ; c ++) {
	    		//for each cell
    				table.addValueRow(data[t][r][c]);
	    		}
    			table.insertRow();
    		}
    		tables.add(table.copy());
    		table.clear();
    	}
        table1 = data[0];
        table2 = data[1];
       
    }
    

    
    
    public void addTable(Table table) {
    	tables.add(table);
    }
   
    public Table table(int index) {
    	return tables.get(index);
    }
    
    public void print() {
    	for(Table table: tables) {
    		table.print();
    	}
    }
    
    
   
} 