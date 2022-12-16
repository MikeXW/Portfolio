package dataObject;

import java.util.ArrayList;


public class Table {
	public ArrayList<Row> values;
	public Row row;
	
	public Table(){
		row = new Row();
		values = new ArrayList<Row>();
	}
	
	Table(ArrayList<Row> values){
		this.values = values;
	}
	
	
	Table(Table table){
		this.values = table.values;
	}
	
    public void addValueRow (String str){
    	row.add(str);
    }
    
    public void insertRow(){
    	values.add(row.copy());
    	row.clear();
    }
    
    public void print() {
    	for(Row row1 : values) {
    		row1.print();
    		System.out.println();
    	}
    }
    
    public void clear() {
    	values.clear();
    }
    
    public Table copy() {
		Table temp = new Table(new ArrayList<>(values));
		return temp;
	}
    

}
