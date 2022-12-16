package dataScrapper;

import java.util.Arrays;
import dataObject.DataObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dataObject.DataObject;


public class Scrapper {
    static public DataObject data;
   
    static public DataObject getData(){
        final String url = "https://horizon.netscout.com/?atlas=summary";
        String[][][] masterTable = new String[2][5][3]; // 2 tables (origen & destination), 5 rows x table, 3 columns per row
        String value;
       
        try{
            final Document document = Jsoup.connect(url).get(); //HTML download
            final Elements tables = document.getElementsByClass("summary-air-ui--topcountries--table summary-air-ui--table--table"); //two tables: 1=origin 2=destination
           
            int t = 0; //table count
            for(Element table : tables){
                int c = 0;  //cell count
               
                for(Element cell : table.select("span")){
                    if(c % 4 == 0){ c ++; continue;} //if first element of the row (flag symbol) => discard
                    if (c % 4 == 3){ value = cell.text().split("%")[0];} //discard %
                    else {value = cell.text();}
                    masterTable[t][c/4][(c%4)-1] = value; //adding elements to master table ( c/4 for 4 elements per row )
                    c ++;
                }
               
                t++;}}
        catch(Exception ex){
            ex.printStackTrace();}
       
        data = new DataObject(masterTable);
        //System.out.println(Arrays.deepToString(masterTable));
        return data;
    }
}




