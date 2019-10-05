package com.logicCP;

import com.controllerCP.AddChangeController;
import com.databaseCP.AddChangeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class AddChangeLogic {

    private LocalDate date;
    private int fromHourValue;
    private int toHourValue;
    private Label labelStatus;
    private Alert alert;
    private double amountForHour = 12.5;


    public void valueControls (AddChangeController add) throws SQLException {

        date = add.getDateChangePicker().getValue();
        fromHourValue = add.getFromHourSpinner().getValue();
        toHourValue = add.getToHourSpinner().getValue();
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

            return  false;
        }

        return true;
    }

    @FXML
    public void alertWindow() throws SQLException {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm change");
        alert.setHeaderText("Do you want add this change?");
        alert.setContentText("Date: "+date+" \nFrom Hour: "+fromHourValue+" \nTo Hour: "+toHourValue+" \nNumber of Hours: "+
                (toHourValue-fromHourValue));

        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonConfirm){
            AddChangeDAO.insertRecordAddChange(fromHourValue,toHourValue,date,(toHourValue-fromHourValue),amountForHour);
        }
    }


}
