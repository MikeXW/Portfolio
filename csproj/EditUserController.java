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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author X1
 */
public class EditUserController implements Initializable {

    @FXML
    private TextField lbl_fname;
    @FXML
    private TextField lbl_lname;
    @FXML
    private TextField lbl_dept;
    @FXML
    private TextField lbl_title;
    @FXML
    private TextField lbl_email;
    @FXML
    private TextField lbl_phone;
    @FXML
    private TextField lbl_pay;
    @FXML
    private TextField lbl_id;
    @FXML
    private TextField lbl_address;
    @FXML
    private TextField lbl_city;
    @FXML
    private TextField lbl_state;
    @FXML 
    private Button btn_cancel;
    @FXML 
    private CheckBox chk_married;
    @FXML 
    private CheckBox chk_change_married;
    @FXML
    private Button btn_edit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Sec.secLvl==4){
            chk_change_married.setDisable(true);
            lbl_fname.setDisable(true);
            lbl_lname.setDisable(true);
            lbl_phone.setDisable(true);
            lbl_email.setDisable(true);
            lbl_dept.setDisable(true);
            lbl_title.setDisable(true);
            chk_married.setDisable(true);
            lbl_pay.setDisable(true);
            lbl_id.setDisable(true);
            btn_edit.setDisable(true);
            lbl_address.setDisable(true);
            lbl_city.setDisable(true);
            lbl_state.setDisable(true);
        }
        btn_cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Menu.fxml", "Menu", null, 0);
            }
        });
        btn_edit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String married = "";
                if(chk_change_married.isSelected()){
                    if(chk_married.isSelected()) married = "1";
                    else married = "0";
                }
                if (!Util.isNumeric(lbl_id.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please input correct Employee ID!");
                    alert.show();
                }else{
                    Util.editEmployee(Integer.parseInt(lbl_id.getText()), lbl_fname.getText(), lbl_lname.getText(), lbl_dept.getText(), lbl_title.getText(),
                        lbl_email.getText(), lbl_phone.getText(), lbl_pay.getText(), married, lbl_address.getText(), lbl_city.getText(), lbl_state.getText());
                    lbl_fname.setText("");
                    lbl_lname.setText("");
                    lbl_dept.setText("");
                    lbl_title.setText("");
                    lbl_email.setText("");
                    lbl_phone.setText("");
                    lbl_address.setText("");
                    lbl_city.setText("");
                    lbl_state.setText("");
                    chk_change_married.setSelected(false);
                    
                }
            }
            
            
        });
    }    
    
}
