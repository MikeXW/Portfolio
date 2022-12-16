/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author X1
 */
public class LogInController implements Initializable{

    @FXML
    private Button btn_login;
    @FXML
    private Button btn_forgotPass;
    @FXML
    private TextField txtf_username;
    @FXML
    private PasswordField txtf_password;
    @FXML
    private Label lbl_response;
    @FXML
    private Label txt_sec1;
    @FXML
    private Label txt_sec2;
    @FXML
    private Label txt_reset;
    @FXML
    private TextField txtf_sec1;
    @FXML
    private TextField txtf_sec2;
    @FXML
    private TextField txtf_reset;
    @FXML
    private Button btn_reset;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_sec1.setVisible(false);
        txt_sec2.setVisible(false);
        txt_reset.setVisible(false);
        txtf_sec1.setVisible(false);
        txtf_sec2.setVisible(false);
        txtf_reset.setVisible(false);
        btn_reset.setVisible(false);
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if (!txtf_username.getText().trim().isEmpty() && !txtf_password.getText().trim().isEmpty()){
                Util.signInUser(event, txtf_username.getText(), HashPassword.hash(txtf_password.getText()));
                //}
//                else{
//                    System.out.println("Please fill in all information!");
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setContentText("Please fill in all the information!");
//                    alert.show();
//                }
            }
        });
        btn_forgotPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!txtf_username.getText().trim().isEmpty()){
                Util.resetPassword(txtf_username.getText(),txt_sec1, txt_sec2,txt_reset,txtf_sec1,txtf_sec2,txtf_reset,btn_reset);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter your username!");
                    alert.show();
                }
            }
        });
        btn_reset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(!txtf_username.getText().trim().isEmpty() && !txtf_sec1.getText().trim().isEmpty() && !txtf_sec2.getText().trim().isEmpty() &&
                    !txtf_reset.getText().trim().isEmpty()){
                Util.submitReset(txtf_username.getText(),txt_sec1, txt_sec2,txt_reset,txtf_sec1,txtf_sec2,txtf_reset);
                txt_sec1.setVisible(false);
                txt_sec2.setVisible(false);
                txt_reset.setVisible(false);
                txtf_sec1.setVisible(false);
                txtf_sec2.setVisible(false);
                txtf_reset.setVisible(false);
                btn_reset.setVisible(false);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter all fields!");
                    alert.show();
                }
            }
        });
        
    }
    
}

