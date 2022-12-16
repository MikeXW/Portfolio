package controller;

import jakarta.servlet.ServletException;
import dataScrapper.*;
import dataObject.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class tableDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public tableDisplay() {
        super();
        DataObject data = new DataObject();
		try {
			data = mainScrapper.run();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Table sourceTable = data.table(0);
		Table sourceTarget = data.table(1);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataObject data = new DataObject();
		try {
			data = mainScrapper.run();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Table sourceTable = data.table(0);
		Table sourceTarget = data.table(1);
		
		
		doGet(request, response);
	}

}
