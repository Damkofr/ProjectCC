package com.controllerCP;

import com.logicCP.AddChangeBoughtLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddChangeBoughtController implements Initializable {

    @FXML
    private DatePicker bhtDatePicker;

    @FXML
    private Spinner<Integer> fromHourSpinner;

    @FXML
    private Spinner<Integer> tohourSpinner;

    @FXML
    private Spinner<Double> amountSpinner;

    @FXML
    private TextField forWhoFiled;

    @FXML
    private RadioButton cashRadioButton;

    @FXML
    private RadioButton salaryRadioButton;

    @FXML
    private RadioButton boughtRadioButton;

    @FXML
    private RadioButton soldRadioButton;

    @FXML
    private Label statusLabel;

    public DatePicker getBhtDatePicker() {
        return bhtDatePicker;
    }

    public Spinner<Integer> getFromHourSpinner() {
        return fromHourSpinner;
    }

    public Spinner<Integer> getTohourSpinner() {
        return tohourSpinner;
    }

    public Spinner<Double> getAmountSpinner() {
        return amountSpinner;
    }

    public TextField getForWhoFiled() {
        return forWhoFiled;
    }

    public RadioButton getCashRadioButton() {
        return cashRadioButton;
    }

    public RadioButton getSalaryRadioButton() {
        return salaryRadioButton;
    }

    public RadioButton getBoughtRadioButton() {
        return boughtRadioButton;
    }

    public RadioButton getSoldRadioButton() {
        return soldRadioButton;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> hourSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24,8);
        SpinnerValueFactory<Integer> hourSpinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24,8);
        SpinnerValueFactory<Double> amountSetSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,300,90);

        ToggleGroup toggleGroup1 = new ToggleGroup();
        cashRadioButton.setToggleGroup(toggleGroup1);
        salaryRadioButton.setToggleGroup(toggleGroup1);

        ToggleGroup toggleGroup2 = new ToggleGroup();
        boughtRadioButton.setToggleGroup(toggleGroup2);
        soldRadioButton.setToggleGroup(toggleGroup2);

        fromHourSpinner.setValueFactory(hourSpinner1);
        fromHourSpinner.setEditable(true);
        tohourSpinner.setValueFactory(hourSpinner2);
        tohourSpinner.setEditable(true);

        try {
            amountSpinner.setValueFactory(amountSetSpinner);
            amountSpinner.setEditable(true);
        }catch (Exception e)
        {
            e.getMessage();
        }

        amountSpinner.setValueFactory(amountSetSpinner);
        amountSpinner.setEditable(true);


        cashRadioButton.setSelected(true);
        boughtRadioButton.setSelected(true);

        bhtDatePicker.setEditable(false);

    }

    @FXML
    public void actionAfterClick() throws SQLException {
     AddChangeBoughtLogic logic = new AddChangeBoughtLogic();
     logic.setControls(this);
    }
}




