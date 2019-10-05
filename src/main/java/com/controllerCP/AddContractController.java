package com.controllerCP;

import com.logicCP.AddContractLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddContractController implements Initializable {

    @FXML
    private DatePicker contractDatePicker;

    @FXML
    private RadioButton contractRadioButton;

    @FXML
    private RadioButton agreementRadioButton;

    @FXML
    private Spinner<Double> provisionSpinner;

    @FXML
    private TextField nameContractField;

    @FXML
    private Label statusLabel;

    @FXML
    private ToggleGroup toggleGroup1;



    SpinnerValueFactory.DoubleSpinnerValueFactory spinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,400,12.5);


    public DatePicker getContractDatePicker() {
        return contractDatePicker;
    }

    public RadioButton getContractRadioButton() {
        return contractRadioButton;
    }

    public RadioButton getAgreementRadioButton() {
        return agreementRadioButton;
    }

    public Spinner<Double> getProvisionSpinner() {
        return provisionSpinner;
    }

    public TextField getNameContractField() {
        return nameContractField;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public ToggleGroup getToggleGroup1() {
        return toggleGroup1;
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        provisionSpinner.setValueFactory(spinner);
        provisionSpinner.setEditable(true);

        toggleGroup1 = new ToggleGroup();
        contractRadioButton.setToggleGroup(toggleGroup1);
        agreementRadioButton.setToggleGroup(toggleGroup1);
        contractRadioButton.setSelected(true);
        contractDatePicker.setEditable(false);


    }

    @FXML
    public void actionAfterClick() throws SQLException {
        AddContractLogic logic = new AddContractLogic();
       logic.setValueControls(this);

    }
}

