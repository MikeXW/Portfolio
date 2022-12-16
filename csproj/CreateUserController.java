/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package csproj;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author X1
 */
@SuppressWarnings("unchecked")
public class CreateUserController implements Initializable {

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
    private TextField lbl_username;
    @FXML
    private TextField lbl_password;
    @FXML
    private ChoiceBox cb_title;
    @FXML
    private CheckBox chk_man;
    @FXML
    private Button btn_create_user;
    @FXML
    private Button btn_cancel;
    @FXML
    private CheckBox chk_married;
    @FXML
    private TextField lbl_pay;
    @FXML 
    private CheckBox chk_employee;
    @FXML
    private TextField lbl_pet;
    @FXML
    private TextField lbl_last_phone;
    @FXML
    private TextField lbl_dads_middle;
    @FXML
    private TextField lbl_address;
    @FXML
    private TextField lbl_city;
    @FXML
    private TextField lbl_state;
    ObservableList<String> manOptions = FXCollections.observableArrayList("Senior Manager","Manager","Administration","Supervisor");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_title.getItems().addAll(manOptions);
        cb_title.setValue("Supervisor");
        btn_cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Menu.fxml", "Menu", null, 0);
            }
        });
        btn_create_user.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(chk_employee.isSelected()){
                int marry = 0;
                if (chk_married.isSelected()) marry = 1;
                if (!lbl_fname.getText().isEmpty() && !lbl_lname.getText().isEmpty() && !lbl_dept.getText().isEmpty() && !lbl_email.getText().isEmpty() && !lbl_title.getText().isEmpty() &&
                    !lbl_address.getText().isEmpty() && !lbl_city.getText().isEmpty() && !lbl_state.getText().isEmpty() &&
                        Util.isNumeric(lbl_phone.getText()) && Util.isNumeric(lbl_phone.getText()))
                {
                    Util.createEmployee(lbl_fname.getText(), lbl_lname.getText(), lbl_dept.getText(),lbl_title.getText(), lbl_email.getText(), 
                            lbl_phone.getText(), Float.parseFloat(lbl_pay.getText()), marry, lbl_address.getText(), lbl_city.getText(), lbl_state.getText());
                    lbl_fname.setText("");
                    lbl_lname.setText("");
                    lbl_dept.setText("");
                    lbl_title.setText("");
                    lbl_email.setText("");
                    lbl_phone.setText("");
                    lbl_pay.setText("");
                    lbl_address.setText("");
                    lbl_city.setText("");
                    lbl_state.setText("");
                    chk_married.setSelected(false);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Employee Created!");
                    alert.show();
                    
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Check inputs for employee creation!");
                    alert.show();
                }
            }
                if(chk_man.isSelected()){
                    
                    if(!lbl_username.getText().isEmpty() && !lbl_password.getText().isEmpty()){
                        if((!lbl_pet.getText().isEmpty() && !lbl_last_phone.getText().isEmpty()) ||
                           (!lbl_last_phone.getText().isEmpty() && !lbl_dads_middle.getText().isEmpty()) ||    
                           (!lbl_dads_middle.getText().isEmpty() && !lbl_pet.getText().isEmpty())){
                        String test = (String) cb_title.getValue();
                        int seclvl = 0;
                        if(test == "Supervisor") seclvl = 4;
                        else if(test == "Administration") seclvl = 3;
                        else if(test == "Manager") seclvl = 2;
                        else if(test == "Senior Manager") seclvl = 1;
                        String pet = "";
                        String phone = "";
                        String dad = "";
                        
                        
                        if (!lbl_pet.getText().isEmpty()){
                        pet = HashPassword.hash(lbl_pet.getText());
                        }
                        else pet = "";
                        
                        if (!lbl_last_phone.getText().isEmpty()){
                        phone = HashPassword.hash(lbl_last_phone.getText());
                        }
                        else phone = "";
                        if (!lbl_dads_middle.getText().isEmpty()){
                        dad = HashPassword.hash(lbl_dads_middle.getText());
                        }
                        else dad = "";
                        Util.createUser(lbl_username.getText(), HashPassword.hash(lbl_password.getText()), seclvl, pet, phone, dad);
                        lbl_username.setText("");
                        lbl_password.setText("");
                        lbl_pet.setText("");
                        lbl_last_phone.setText("");
                        lbl_dads_middle.setText("");
                        cb_title.setValue("Supervisor");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("User Created!");
                        alert.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Check inputs for security questions!");
                        alert.show();
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Check inputs for user creation!");
                        alert.show();
                    }
                }
                    
            }
            
        });
        if (Sec.secLvl==4){
            chk_man.setDisable(true);
            lbl_username.setDisable(true);
            lbl_password.setDisable(true);
            cb_title.setDisable(true);
            btn_create_user.setDisable(true);
            lbl_fname.setDisable(true);
            lbl_lname.setDisable(true);
            lbl_phone.setDisable(true);
            lbl_email.setDisable(true);
            lbl_dept.setDisable(true);
            lbl_title.setDisable(true);
            chk_married.setDisable(true);
            lbl_pay.setDisable(true);
            
        }
        if (Sec.secLvl==3){
            chk_man.setDisable(true);
            lbl_username.setDisable(true);
            lbl_password.setDisable(true);
            cb_title.setDisable(true);
        }
        if (Sec.secLvl==2){
            manOptions = FXCollections.observableArrayList("Senior Manager","Manager");
            cb_title.getItems().removeAll(manOptions);
        }
    }    
    
}
