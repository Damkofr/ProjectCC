package com.controllerCP;

import com.databaseCP.ContractsDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewContractsController implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<ContractsDAO, Integer> numberContract;

    @FXML
    private TableColumn<ContractsDAO, String> dateContractColumn;

    @FXML
    private TableColumn<ContractsDAO, String> typeContract;

    @FXML
    private TableColumn<ContractsDAO, String> nameContract;

    @FXML
    private TableColumn<ContractsDAO, Integer> amountContract;

    @FXML
    private TableColumn<ContractsDAO, ContractsDAO> acceptedContract;

    @FXML
    private TableColumn<ContractsDAO, ContractsDAO> deleteContractColumn;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Spinner<Integer> yearSpinner;


    ContractsDAO contractsDAO = new ContractsDAO();
    ObservableList<ContractsDAO> list = FXCollections.observableArrayList(contractsDAO.getContract());

    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerMonth = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,6);
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerYear = new SpinnerValueFactory.IntegerSpinnerValueFactory(2018,2025,2019);

    public ViewContractsController() throws SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthSpinner.setValueFactory(spinnerMonth);
        monthSpinner.setEditable(true);

        yearSpinner.setValueFactory(spinnerYear);
        yearSpinner.setEditable(true);

        viewResult(list);
        addButtonDelete();
        addCheckBoxAccepted();

    }


    private void viewResult(ObservableList<ContractsDAO> list) {



            numberContract.setCellValueFactory(new PropertyValueFactory<>("numberContract"));
            dateContractColumn.setCellValueFactory(new PropertyValueFactory<>("dateContractStr"));
            typeContract.setCellValueFactory(new PropertyValueFactory<>("typeContract"));
            nameContract.setCellValueFactory(new PropertyValueFactory<>("nameContract"));
            amountContract.setCellValueFactory(new PropertyValueFactory<>("amountContract"));
            acceptedContract.setCellValueFactory(new PropertyValueFactory<>("acceptedContract"));


            for (int i = 0; i < list.size(); i++) {

                tableView.getColumns().clear();

                tableView.getColumns().addAll(numberContract, dateContractColumn, typeContract, nameContract, amountContract, acceptedContract, deleteContractColumn);

                tableView.getItems().add(list.get(i));

            }



    }

    public void addButtonDelete() {
        deleteContractColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteContractColumn.setCellFactory(param -> new TableCell<ContractsDAO, ContractsDAO>() {
            private Button deleteButton = new Button("DELETE");

            @Override
            protected void updateItem(ContractsDAO contract, boolean empty) {
                super.updateItem(contract, empty);

                if (contract == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                setAlignment(Pos.CENTER);



                deleteButtonAction(deleteButton,contract);

            }
        });
    }

    public void deleteButtonAction(Button deleteButton, ContractsDAO contract) {
        deleteButton.addEventFilter(ActionEvent.ACTION, event -> {
            System.out.println("Wciśnięto przycisk: "+contract.getIdContract());

            try {
                contractsDAO.deleteContract(contract.getIdContract());
                deleteButton.setDisable(true);
                deleteButton.setText("DELETED");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }


    public void addCheckBoxAccepted() {
        acceptedContract.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        acceptedContract.setCellFactory(param -> new TableCell<ContractsDAO, ContractsDAO>() {
            private CheckBox checkBoxAccepted = new CheckBox("ACC");

            @Override
            protected void updateItem(ContractsDAO contract, boolean empty) {
                super.updateItem(contract, empty);

                if (contract == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(checkBoxAccepted);

                if(contract.isAcceptedContract()){
                    checkBoxAccepted.setSelected(true);
                }
                else
                {
                    checkBoxAccepted.setSelected(false);
                }



                checkBoxAcceptedAction(checkBoxAccepted,contract);
            }
        });



    }


    public void checkBoxAcceptedAction(CheckBox checkBoxAccepted, ContractsDAO contract) {
        checkBoxAccepted.addEventFilter(ActionEvent.ACTION, event -> {
            System.out.println("Wciśnięto przycisk: "+contract.getIdContract());

            if(checkBoxAccepted.isSelected())
            {
                try {
                    contractsDAO.updateAcceptedContract(contract.getIdContract(),true);
                    System.out.println("Zaznaczony");
                    System.out.println("-------------------------");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    contractsDAO.updateAcceptedContract(contract.getIdContract(),false);
                    System.out.println("NIe Zaznaczony");
                    System.out.println("-------------------------");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void refreshButton() throws SQLException {
        ContractsDAO contractsDAO2 = new ContractsDAO();
        ObservableList<ContractsDAO> list1 = FXCollections.observableArrayList(contractsDAO2.getContract(monthSpinner.getValue(),yearSpinner.getValue()));
        tableView.getItems().clear();
        viewResult(list1);
        addCheckBoxAccepted();
        addButtonDelete();
    }



}


