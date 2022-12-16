package dataObject;

import java.util.ArrayList;

public class Row {
	public ArrayList<String> values;
	
	public Row(){
		values = new ArrayList<String>();
	}
	
	Row(ArrayList<String> values){
		this.values = values;
	}
	
	public void add(String str) {
		values.add(str);
	}
	
	public void clear() {
		values.clear();
	}
	
	public Row copy() {
		Row temp = new Row(new ArrayList<>(values));
		return temp;
	}
	
	public void print() {
		for(String value: values) {
			System.out.print(value + "  |  ");
		}
	}

}
