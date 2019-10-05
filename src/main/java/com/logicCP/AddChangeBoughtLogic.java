package com.logicCP;

import com.controllerCP.AddChangeBoughtController;
import com.databaseCP.AddChangeBoughtDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class AddChangeBoughtLogic {


     private LocalDate date;
     private Label labelStatus;
     private int fromHourValue;
     private int toHourValue;
     private double amountValue;
     private String textWho;
     private boolean isCash;
     private boolean isSalary;
     private boolean isBought;
     private boolean isSold;
     private Alert alert;






    public void setControls(AddChangeBoughtController add) throws SQLException {

        date =  add.getBhtDatePicker().getValue();

        fromHourValue = add.getFromHourSpinner().getValue();
        toHourValue = add.getTohourSpinner().getValue();
        amountValue = add.getAmountSpinner().getValue();

        textWho = add.getForWhoFiled().getText();

        isCash = add.getCashRadioButton().isSelected();
        isSalary = add.getSalaryRadioButton().isSelected();

        isBought = add.getBoughtRadioButton().isSelected();
        isSold = add.getSoldRadioButton().isSelected();

         labelStatus = add.getStatusLabel();



         if(!validation())
         {
          return;
         }

         alertWindow();



    }


    public boolean validation(){



    if(fromHourValue>=toHourValue)
    {
       labelStatus.setText("from hour number must be bigger than to hour number");
       labelStatus.setTextFill(Color.web("#FF0000"));
       return false;
    }

    if(date == null)
    {
       labelStatus.setText("Choose date");
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
        alert.setContentText("Date: "+date+" \nFrom Hour: "+fromHourValue+" \nTo Hour: "+toHourValue+
                " \nNumber of Hours: "+(toHourValue - fromHourValue) +" \nAmount: "+
                amountValue+" \nWho: "+textWho+" \nWhen Get: " + (isCash?"Cash":"Salary") + " \nBought/Sold: " +
                (isBought?"Bought":"Sold"));

        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonConfirm){
            AddChangeBoughtDAO.insertRecordAddChange(fromHourValue,toHourValue,date,textWho,amountValue,isCash,isBought);
        }
    }
}
