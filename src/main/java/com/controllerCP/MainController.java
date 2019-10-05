package com.controllerCP;

import com.logicCP.MainLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {

    @FXML
    private Button addChangeMainButton;

    @FXML
    private Button viewChangesButton;

    @FXML
    private Button addChangeBoughtButton;

    @FXML
    private Button viewContractsButton;

    @FXML
    private Button addContractButton;

    @FXML
    private Button viewStatisticsButton;




    @FXML
    void chooseViewAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        MainLogic mainl = new MainLogic();
        mainl.chooseView(buttonId);

    }

}
