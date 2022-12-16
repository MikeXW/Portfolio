/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package csproj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author X1
 */
public class ListUsersController implements Initializable {

    @FXML
    private TableView<Employee> tb_emp;
    @FXML
    private TableView<User> tb_user;
    @FXML
    private Button btn_exit;
    @FXML
    private TableColumn<User, String> user_id;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<Employee, String> emp_id;
    @FXML
    private TableColumn<Employee, String> fname;
    @FXML
    private TableColumn<Employee, String> lname;
    @FXML
    private TableColumn<Employee, String> dept;
    @FXML
    private TableColumn<Employee, String> title;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, String> phone;
    @FXML
    private TableColumn<Employee, String> pay;
    @FXML
    private TableColumn<Employee, String> married;
    @FXML
    private AnchorPane ap;
    @FXML Label lbl_user_list;
    ObservableList<User> list = Util.showUsers();
    ObservableList<Employee> list2 = Util.showEmployees();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Sec.secLvl!=1) {
            ap.getChildren().remove(tb_user);
            ap.getChildren().remove(lbl_user_list);
        }
        else{
        user_id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        tb_user.setItems(list);
        }
        emp_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lname"));
        dept.setCellValueFactory(new PropertyValueFactory<Employee, String>("dept"));
        title.setCellValueFactory(new PropertyValueFactory<Employee, String>("title"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        pay.setCellValueFactory(new PropertyValueFactory<Employee, String>("pay"));
        married.setCellValueFactory(new PropertyValueFactory<Employee, String>("married"));
        tb_emp.setItems(list2);
        
        btn_exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Menu.fxml", "Menu", null, 0);
            }
            
        });
    }    
    
}
