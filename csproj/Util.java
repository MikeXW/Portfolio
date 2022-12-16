/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;




import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author X1
 */
@SuppressWarnings("unchecked")
public class Util {
    public static String url = "jdbc:mysql://localhost:3306/pinetree";
    public static String pass = "";
    public static Connection con = null;
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, int adminLevel){
        Parent root = null;
        
        try {
            FXMLLoader loader = new FXMLLoader(Util.class.getResource(fxmlFile));
            root = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000,800));
        stage.show();
    }
    
    public static void signInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psUserCheck = null;
        ResultSet rs = null;
        ResultSet resultSet = null;
        
        try{
            connection = DriverManager.getConnection(url, "root", pass);
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users ");
            resultSet = psCheckUserExists.executeQuery();
            int count = 0;
            while(resultSet.next()){
                count++;
            }
            if (count==0){
                changeScene(event, "Menu.fxml", "Menu",null,1);
            }
            else {
                psUserCheck = connection.prepareStatement("SELECT password, adminLevel FROM users WHERE username = ?");
                psUserCheck.setString(1, username);
                rs = psUserCheck.executeQuery();
                
                if (!rs.isBeforeFirst()){
                    System.out.println("User not found in the database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();
                }
                else{
                    while(rs.next()){
                        String retrieveP = rs.getString("password");
                        int retrieveA = rs.getInt("adminLevel");
                        if (retrieveP.equals(password)){
                            Sec.secLvl=retrieveA;
                            changeScene(event, "Menu.fxml","Menu",username,retrieveA);
                            
                        }
                        else{
                            System.out.println("Passwords not matching");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("The passwords do not match!");
                            alert.show();
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
            if (psInsert != null){
                try {
                    psInsert.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
            if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
            if (psUserCheck != null){
                try {
                    psUserCheck.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
            if (connection != null){
                try {
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
        }
        
    }
    public static boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        }catch(NumberFormatException e){  
            return false;  
        }  
    }
    
    public static void createEmployee(String fname, String lname, String dept, String title, String email, String phone, float pay, int married, String address, String city, String state){
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url,"root",pass);
            ps = con.prepareStatement("INSERT INTO employees (fname,lname,dept,title,email,phone,pay,married,address,city,state)\n" +
"VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, dept);
            ps.setString(4, title);
            ps.setString(5, email);
            ps.setString(6,phone);
            ps.setFloat(7, pay);
            ps.setInt(8, married);
            ps.setString(9,address);
            ps.setString(10,city);
            ps.setString(11,state);
            ps.executeUpdate();
            
            ps3 = con.prepareStatement("SELECT idemp FROM employees ORDER BY idemp DESC LIMIT 1; ");
            rs = ps3.executeQuery();
            rs.next();
            int count = rs.getInt("idemp");
            
            ps2 = con.prepareStatement("INSERT INTO payroll (idemp, hours_worked) VALUES (?, null) ;");
            ps2.setInt(1, count);
            ps2.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (ps != null){
                try{
                    ps.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (ps2 != null){
                try{
                    ps2.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (ps3 != null){
                try{
                    ps3.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public static void createUser(String username, String password, int secLvl, String sec1, String sec2, String sec3){
        PreparedStatement ps = null;
        try{
            con = DriverManager.getConnection(url,"root",pass);
            ps = con.prepareStatement("INSERT INTO users (username,password,adminLevel,pet,last_phone,dads_middle)\n" +
"VALUES(?,?,?,?,?,?) ;");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, secLvl);
            ps.setString(4, sec1);
            ps.setString(5, sec2);
            ps.setString(6, sec3);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (ps != null){
                try{
                    ps.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    public static ObservableList<User> showUsers(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<User> list = FXCollections.observableArrayList();
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps= con.prepareStatement("SELECT users.idusers, users.username FROM users");
            rs=ps.executeQuery();
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("idusers"));
                String username = rs.getString("username");
                list.add(new User(id,username));
               
        }
            
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }    }    return list; }
    
    public static ObservableList<Employee> showEmployees(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps= con.prepareStatement("SELECT * FROM employees");
            rs=ps.executeQuery();
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("idemp"));
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String dept = rs.getString("dept");
                String title = rs.getString("title");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pay = "$"+Float.toString(rs.getFloat("pay"));
                String married = Integer.toString(rs.getInt("married"));
                if (Integer.parseInt(married) == 1) married = "Yes";
                else married = "No";
                list.add(new Employee(id,fname,lname,dept,title,email,phone,pay,married));
               
        }
            
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }    }    return list; }
    public static void editEmployee(int id, String fname, String lname, String dept, String title, String email, String phone, String pay, String married, String address, String city, String state){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT employees.idemp FROM employees WHERE idemp = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                if(!fname.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET fname = ? WHERE idemp = ? ");
                    ps2.setString(1, fname);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!lname.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET lname = ? WHERE idemp = ?");
                    ps2.setString(1, lname);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!dept.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET dept = ? WHERE idemp = ?");
                    ps2.setString(1, dept);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!title.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET title = ? WHERE idemp = ?");
                    ps2.setString(1, title);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!email.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET email = ? WHERE idemp = ?");
                    ps2.setString(1, email);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!phone.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET phone = ? WHERE idemp = ?");
                    ps2.setString(1, phone);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(Util.isNumeric(pay)){
                    ps2 = con.prepareStatement("UPDATE employees SET pay = ? WHERE idemp = ?");
                    ps2.setFloat(1, Float.parseFloat(pay));
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(Util.isNumeric(married)){
                    ps2 = con.prepareStatement("UPDATE employees SET married = ? WHERE idemp = ?");
                    ps2.setFloat(1, Integer.parseInt(married));
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!address.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET addresss = ? WHERE idemp = ?");
                    ps2.setString(1, address);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!city.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET city = ? WHERE idemp = ?");
                    ps2.setString(1, city);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                if(!state.isBlank()){
                    ps2 = con.prepareStatement("UPDATE employees SET state = ? WHERE idemp = ?");
                    ps2.setString(1, state);
                    ps2.setInt(2, id);
                    ps2.executeUpdate();
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Employee Edited!");
                alert.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            
        }
        
        
    }}
    public static ObservableList<PayrollUser> showPayroll(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<PayrollUser> list = FXCollections.observableArrayList();
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps= con.prepareStatement("SELECT employees.idemp, employees.pay, payroll.hours_worked FROM employees INNER JOIN payroll ON employees.idemp = payroll.idemp; ");
            rs=ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idemp");
                float pay = rs.getFloat("pay");
                float hours = rs.getFloat("hours_worked");
                list.add(new PayrollUser(id,pay,hours));
               
        }
            
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }    }    return list; }
    public static void payrollAdd(int id, float hours){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT payroll.hours_worked FROM payroll WHERE idemp = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                ps2 = con.prepareStatement("UPDATE payroll SET hours_worked = IFNULL(hours_worked, 0) + ? WHERE idemp = ?; ");
                ps2.setFloat(1, hours);
                ps2.setInt(2, id);
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Hours Added!");
                alert.show();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            
        }
        
        
    }}
    public static void payrollRemove(int id, float hours){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT payroll.hours_worked FROM payroll WHERE idemp = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                ps2 = con.prepareStatement("UPDATE payroll SET hours_worked = hours_worked - ? WHERE idemp = ?; ");
                ps2.setFloat(1, hours);
                ps2.setInt(2, id);
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Hours Removed!");
                alert.show();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
    }}}
    public static void payrollSet(int id, float hours){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT payroll.hours_worked FROM payroll WHERE idemp = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                ps2 = con.prepareStatement("UPDATE payroll SET hours_worked = ? WHERE idemp = ?; ");
                ps2.setFloat(1, hours);
                ps2.setInt(2, id);
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Hours Set!");
                alert.show();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
    }}}
    public static void payrollRun(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT employees.idemp, employees.pay, employees.fname, employees.lname, employees.email, employees.phone,"
                    + "employees.address, employees.city, employees.state, payroll.hours_worked "
                    + "FROM employees INNER JOIN payroll ON employees.idemp = payroll.idemp;");
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Payroll Has already been ran!");
                alert.show();
            }
            else{
                ps3 = con.prepareStatement("DELETE FROM past_payroll ;");
                ps3.executeUpdate();
                System.out.println("Trying!");
                while (rs.next()){
                    ps2 = con.prepareStatement("INSERT INTO past_payroll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;");
                    int id = rs.getInt("idemp");
                    float pay = rs.getFloat("pay");
                    float hours = rs.getFloat("hours_worked");
                    double total = pay * hours;
                    double federal = total * 0.078;
                    double social = total * 0.062;
                    double medicare = total * 0.015;
                    double total_with = federal + social + medicare;
                    double gross = total-total_with;
                    ps2.setInt(1, id);
                    ps2.setFloat(2, pay);
                    ps2.setFloat(3, hours);
                    ps2.setDouble(4, total);
                    ps2.setDouble(5, social);
                    ps2.setDouble(6, federal);
                    ps2.setDouble(7, medicare);
                    ps2.setDouble(8, total_with);
                    ps2.setDouble(9, gross);
                    ps2.setString(10, rs.getString("fname"));
                    ps2.setString(11, rs.getString("lname"));
                    ps2.setString(12, rs.getString("email"));
                    ps2.setString(13, rs.getString("phone"));
                    ps2.setString(14, rs.getString("address"));
                    ps2.setString(15, rs.getString("city"));
                    ps2.setString(16, rs.getString("state"));
                    ps2.executeUpdate();
                }
                ps4 = con.prepareStatement("UPDATE payroll SET hours_worked = null ;");
                ps4.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps3 != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps4 != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
    }}}
    
    public static void toPDF(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dest = "C:\\Users\\X1\\Documents\\1test.pdf";
        try{
            con = DriverManager.getConnection(url,"root",pass);
            ps = con.prepareStatement("SELECT * FROM past_payroll ;");
            rs = ps.executeQuery();
            
            
            try{
                PDDocument doc = new PDDocument();
                PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                doc.addPage(page);
                
                int pageHeight = (int) page.getTrimBox().getHeight();
                int pageWidth = (int) page.getTrimBox().getWidth();
                PDPageContentStream contentStream = new PDPageContentStream(doc,page);
                contentStream.setStrokingColor(Color.BLACK);
                contentStream.setLineWidth(1);
                
                int initX = 30;
                int initY = pageHeight-80;
                int cellHeight = 20;
                int cellWidth = 50;
                
                
                contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("ID");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("SALARY");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("HOURS");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("GROSS_PAY");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("SS");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("FED");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("MEDI");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("WH");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("NET_PAY");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("FIRST_NAME");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("LAST_NAME");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("EMAIL");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("PHONE");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("ADDRESS");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("CITY");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 5);
                        contentStream.showText("STATE");
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        initX = 30;
                        initY -= cellHeight;
                    while(rs.next()){
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Integer.toString(rs.getInt("idemp")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("pay")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("hours_worked")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("gross_pay")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("social_security")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("federal_income")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("medicare")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("total_withheld")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(Float.toString(rs.getFloat("net_pay")));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("fname"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("lname"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("email"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("phone"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("address"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("city"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        contentStream.addRect(initX, initY, cellWidth, cellHeight);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(initX+5, initY+5);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 5);
                        contentStream.showText(rs.getString("state"));
                        contentStream.endText();
                        initX+=cellWidth;
                        
                        initX = 30;
                        initY -= cellHeight;
                    }
                    
                
                contentStream.stroke();
                contentStream.close();
                
                doc.save(new File("C:\\Users\\X1\\Documents\\NetbeansPDF\\test.pdf"));
                doc.close();
                System.out.println("Document Created");

                
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
    }}
    }
    public static void resetPassword(String username, Label sec1, Label sec2, Label reset, TextField tsec1, TextField tsec2, TextField tsec3, Button btsec){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT users.idusers, users.pet, users.last_phone, users.dads_middle FROM users WHERE username = ?;");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                while(rs.next()){
                    String hold1 = rs.getString("pet");
                    String hold2 = rs.getString("last_phone");
                    String hold3 = rs.getString("dads_middle");
                    if(hold1.isBlank()){
                        sec1.setText("What are four digits of a memorable phone number?");
                        sec1.setVisible(true);
                        tsec1.setVisible(true);
                        sec2.setText("What is your dad's middle name?");
                        sec2.setVisible(true);
                        tsec2.setVisible(true);
                        reset.setVisible(true);
                        tsec3.setVisible(true);
                        btsec.setVisible(true);
                    }
                    else if(hold2.isBlank()){
                        sec1.setText("What is your pets name?");
                        sec1.setVisible(true);
                        tsec1.setVisible(true);
                        sec2.setText("What is your dad's middle name?");
                        sec2.setVisible(true);
                        tsec2.setVisible(true);
                        reset.setVisible(true);
                        tsec3.setVisible(true);
                        btsec.setVisible(true);
                    }
                    else{
                        sec1.setText("What is your pets name?");
                        sec1.setVisible(true);
                        tsec1.setVisible(true);
                        sec2.setText("What are four digits of a memorable phone number?");
                        sec2.setVisible(true);
                        tsec2.setVisible(true);
                        reset.setVisible(true);
                        tsec3.setVisible(true);
                        btsec.setVisible(true);
                    }
                    
                }
                    
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            
        }    }    }
    public static void submitReset(String username, Label sec1, Label sec2, Label reset, TextField tsec1, TextField tsec2, TextField tsec3){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT users.username, users.pet, users.last_phone, users.dads_middle FROM users WHERE username = ?;");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                while(rs.next()){
                    String hold1 = rs.getString("pet");
                    String hold2 = rs.getString("last_phone");
                    String hold3 = rs.getString("dads_middle");
                    if(hold1.isBlank()){
                        if (HashPassword.hash(tsec1.getText()).equals(hold2) && HashPassword.hash(tsec2.getText()).equals(hold3)){
                            ps2 = con.prepareStatement("UPDATE users SET password = ? WHERE username = ?;");
                            ps2.setString(1, HashPassword.hash(tsec3.getText()));
                            ps2.setString(2, username);
                            ps2.executeUpdate();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Password Reset!");
                            alert.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Incorrect Answers");
                            alert.show();
                        }
                    }
                    else if(hold2.isBlank()){
                        if (HashPassword.hash(tsec1.getText()).equals(hold1) && HashPassword.hash(tsec2.getText()).equals(hold3)){
                            ps2 = con.prepareStatement("UPDATE users SET password = ? WHERE username = ?;");
                            ps2.setString(1, HashPassword.hash(tsec3.getText()));
                            ps2.setString(2, username);
                            ps2.executeUpdate();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Password Reset!");
                            alert.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Incorrect Answers");
                            alert.show();
                        }
                    }
                    else{
                        if (HashPassword.hash(tsec1.getText()).equals(hold1) && HashPassword.hash(tsec2.getText()).equals(hold2)){
                            ps2 = con.prepareStatement("UPDATE users SET password = ? WHERE username = ?;");
                            ps2.setString(1, HashPassword.hash(tsec3.getText()));
                            ps2.setString(2, username);
                            ps2.executeUpdate();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Password Reset!");
                            alert.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Incorrect Answers");
                            alert.show();
                        }
                    }
                    
                }
                    
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            
        }    }    }
    public static void removeEmployee(int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT employees.idemp FROM employees WHERE idemp = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No employee with that ID!");
                alert.show();
            }
            else{
                ps2 = con.prepareStatement("DELETE FROM payroll WHERE idemp = ? ;");
                ps2.setInt(1, id);
                ps2.executeUpdate();
                ps2 = con.prepareStatement("DELETE FROM employees WHERE idemp = ? ;");
                ps2.setInt(1, id);
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Employee Removed!");
                alert.show();
                }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
    }}
    public static void removeUser(int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        try{
            con = DriverManager.getConnection(url, "root", pass);
            ps = con.prepareStatement("SELECT users.idusers FROM users WHERE idusers = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No user with that ID!");
                alert.show();
            }
            else{
                ps2 = con.prepareStatement("DELETE FROM users WHERE idusers = ? ;");
                ps2.setInt(1, id);
                ps2.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("User Removed!");
                alert.show();
                }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (rs != null){
                try {
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps != null){
                try {
                    ps.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (ps2 != null){
                try {
                    ps2.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }if (con != null){
                try {
                    con.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
        }
    }}
}
