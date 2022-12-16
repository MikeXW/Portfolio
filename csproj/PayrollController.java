/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package csproj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author X1
 */
public class PayrollController implements Initializable {

    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_run;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_remove;
    @FXML
    private Button btn_set;
    @FXML
    private TextField txtf_id;
    @FXML
    private TextField txtf_hours;
    @FXML
    private TableView tb_table;
    @FXML
    private TableColumn<PayrollUser,Integer> clm_id;
    @FXML
    private TableColumn<PayrollUser,Float> clm_wage;
    @FXML
    private TableColumn<PayrollUser,Float> clm_hours;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PayrollUser> list = Util.showPayroll();
        clm_id.setCellValueFactory(new PropertyValueFactory<PayrollUser, Integer>("id"));
        clm_wage.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("pay"));
        clm_hours.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("hours"));
        tb_table.setItems(list);
        
        btn_cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.changeScene(event, "Menu.fxml", "Menu", null, 0);
            }
        });
       
        btn_add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(!txtf_id.getText().isBlank() && !txtf_hours.getText().isBlank()){
                Util.payrollAdd(Integer.parseInt(txtf_id.getText()), Float.parseFloat(txtf_hours.getText()));
                ObservableList<PayrollUser> list = Util.showPayroll();
                txtf_hours.setText("");
                txtf_id.setText("");
                clm_id.setCellValueFactory(new PropertyValueFactory<PayrollUser, Integer>("id"));
                clm_wage.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("pay"));
                clm_hours.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("hours"));
                tb_table.setItems(list);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Fill All Fields!");
                    alert.show();
                }
            }
        });
        btn_remove.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(!txtf_id.getText().isBlank() && !txtf_hours.getText().isBlank()){
                Util.payrollRemove(Integer.parseInt(txtf_id.getText()), Float.parseFloat(txtf_hours.getText()));
                ObservableList<PayrollUser> list = Util.showPayroll();
                txtf_hours.setText("");
                txtf_id.setText("");
                clm_id.setCellValueFactory(new PropertyValueFactory<PayrollUser, Integer>("id"));
                clm_wage.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("pay"));
                clm_hours.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("hours"));
                tb_table.setItems(list);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Fill All Fields!");
                    alert.show();
                }
            }
        });
        btn_set.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(!txtf_id.getText().isBlank() && !txtf_hours.getText().isBlank()){
                Util.payrollSet(Integer.parseInt(txtf_id.getText()), Float.parseFloat(txtf_hours.getText()));
                ObservableList<PayrollUser> list = Util.showPayroll();
                txtf_hours.setText("");
                txtf_id.setText("");
                clm_id.setCellValueFactory(new PropertyValueFactory<PayrollUser, Integer>("id"));
                clm_wage.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("pay"));
                clm_hours.setCellValueFactory(new PropertyValueFactory<PayrollUser, Float>("hours"));
                tb_table.setItems(list);
            }
            else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Fill All Fields!");
                    alert.show();
                }
            }
        });
        btn_run.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Util.payrollRun();
            }
            
        });
    }    
    
}
