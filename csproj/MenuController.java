/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package csproj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author X1
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_payroll;
    @FXML
    private Button btn_list;
    @FXML
    private Button btn_create;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_print;
    @FXML
    private Button btn_remove;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Sec.secLvl>=3) btn_payroll.setDisable(true);
        if (Sec.secLvl>=4) btn_edit.setDisable(true);
        if (Sec.secLvl>=4) btn_create.setDisable(true);
        if (Sec.secLvl>=4) btn_remove.setDisable(true);
        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "LogIn.fxml", "Log In", null, 0);
                
            }
        });
        btn_create.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "CreateUser.fxml", "Create User", null, 0);
            }
            
        });
        btn_remove.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "RemoveUser.fxml", "Remove User", null, 0);
            }
            
        });
        btn_list.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "ListUsers.fxml", "List Users", null, 0);
            }
            
        }));
        btn_edit.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "EditUser.fxml", "Edit Users", null, 0);
            }
            
        }));
        btn_payroll.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Payroll.fxml", "Payroll", null, 0);
            }
            
        });
        btn_print.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.toPDF();
            }
            
        });
    } 
    
}
