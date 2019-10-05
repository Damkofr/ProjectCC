package com.logicCP;

import com.controllerCP.AddContractController;
import com.databaseCP.AddContractDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class AddContractLogic {

    private double provisionValue;
    private LocalDate date;
    private Label labelStatus;
    private String textContract;
    private boolean isAgreement;
    private boolean isContract;
    private Alert alert;


    public  void setValueControls(AddContractController add) throws SQLException {

        date = add.getContractDatePicker().getValue();
        provisionValue = add.getProvisionSpinner().getValue();
        textContract = add.getNameContractField().getText();
        isAgreement = add.getAgreementRadioButton().isSelected();
        isContract = add.getContractRadioButton().isSelected();
        labelStatus = add.getStatusLabel();


        if(!validation())
        {
            return;
        }

        alertWindow();
    }

    public boolean validation(){


        if(date == null)
        {
            labelStatus.setText("Choose date");
            labelStatus.setTextFill(Color.web("#FF0000"));
            return false;
        }

        if(textContract.length()<5)
        {
            labelStatus.setText("Write more chars in Name Contract field");
            labelStatus.setTextFill(Color.web("#FF0000"));
            return false;
        }

        return true;
    }

    @FXML
    public void alertWindow() throws SQLException {


        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm change");
        alert.setHeaderText("Do you want add this change?");
        alert.setContentText("Date: "+date + " \nAgree/Contract: "+ (isAgreement?"Agreement":"Contract")+
                " \nType Contract: " + textContract+" \nProvision: "+provisionValue);


        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonConfirm){
            AddContractDAO.insertRecordAddChange(date,isAgreement,provisionValue,textContract);
        }
    }

}
