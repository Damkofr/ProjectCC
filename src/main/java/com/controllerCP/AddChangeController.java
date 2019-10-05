package com.controllerCP;

import com.logicCP.AddChangeLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddChangeController implements Initializable {

    @FXML
    private Button addChangeButton;

    @FXML
    private DatePicker dateChangePicker;


    @FXML
    private Spinner<Integer> fromHourSpinner;

    @FXML
    private Spinner<Integer> toHourSpinner;

    @FXML
    private Label statusLabel;




    public DatePicker getDateChangePicker() {
        return dateChangePicker;
    }

    public Spinner<Integer> getFromHourSpinner() {
        return fromHourSpinner;
    }

    public Spinner<Integer> getToHourSpinner() {
        return toHourSpinner;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24,8);
        SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24,15);
        fromHourSpinner.setValueFactory(spinner1);
        fromHourSpinner.setEditable(true);
        toHourSpinner.setValueFactory(spinner2);
        toHourSpinner.setEditable(true);
        dateChangePicker.setEditable(false);
    }

    @FXML
    public void tempDownload() throws SQLException {
        AddChangeLogic logic;
        logic = new AddChangeLogic();
        logic.valueControls(this);

    }
}
