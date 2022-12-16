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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author X1
 */
public class RemoveUserController implements Initializable {

    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_remove_emp;
    @FXML
    private Button btn_remove_user;
    @FXML
    private TextField txtf_remove_emp;
    @FXML
    private TextField txtf_remove_user;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label lbl_remove_user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Sec.secLvl >= 3) {
            ap.getChildren().remove(txtf_remove_user);
            ap.getChildren().remove(btn_remove_user);
            ap.getChildren().remove(lbl_remove_user);
        }
        btn_exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Menu.fxml", "Menu", null, 0);
            }
            });
        btn_remove_user.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (!txtf_remove_user.getText().isEmpty()){
                    Util.removeUser(Integer.parseInt(txtf_remove_user.getText()));
                    
                    txtf_remove_user.setText("");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Input ID#");
                    alert.show();
                }
            }
            });
        btn_remove_emp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (!txtf_remove_emp.getText().isEmpty()){
                    Util.removeEmployee(Integer.parseInt(txtf_remove_emp.getText()));
                    
                    txtf_remove_emp.setText("");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Input ID#");
                    alert.show();
                }
            }
            });
    }    
    
}
