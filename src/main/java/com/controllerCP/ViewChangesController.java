package com.controllerCP;

import com.databaseCP.ChangeDAO;
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

public class ViewChangesController implements Initializable {


    @FXML
    private TableView mainTableChanges;

    @FXML
    private TableColumn<ChangeDAO, Integer> idColumn;

    @FXML
    private TableColumn<ChangeDAO, String> dateChangeColumn;

    @FXML
    private TableColumn<ChangeDAO, Integer> fromHourTable;

    @FXML
    private TableColumn<ChangeDAO, Integer> toHourTable;

    @FXML
    private TableColumn<ChangeDAO, Integer> numberHoursColumn;

    @FXML
    private TableColumn<ChangeDAO, Double> amountChangeColumn;

    @FXML
    private TableColumn<ChangeDAO, String> fromWhoColumn;

    @FXML
    private TableColumn<ChangeDAO, String> whenGetColumn;

    @FXML
    private TableColumn<ChangeDAO, String> boughtChangeColumn;

    @FXML
    private TableColumn<ChangeDAO, ChangeDAO> deleteChangeColumn;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Spinner<Integer> yearSpinner;

    ChangeDAO changeDAO = new ChangeDAO();
    ObservableList<ChangeDAO> list = FXCollections.observableArrayList(changeDAO.getChange());

    SpinnerValueFactory.IntegerSpinnerValueFactory mSpinner  = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,6);
    SpinnerValueFactory.IntegerSpinnerValueFactory ySpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(2018,2025,2019);

    public ViewChangesController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthSpinner.setValueFactory(mSpinner);
        monthSpinner.setEditable(true);

        yearSpinner.setValueFactory(ySpinner);
        yearSpinner.setEditable(true);

        viewResult(list);
        addButtonDelete();

    }

    private void viewResult(ObservableList<ChangeDAO> list) {


        idColumn.setCellValueFactory(new PropertyValueFactory<>("numberChange"));
        dateChangeColumn.setCellValueFactory(new PropertyValueFactory<>("dateChangeToStr"));
        fromHourTable.setCellValueFactory(new PropertyValueFactory<>("fromHourChange"));
        toHourTable.setCellValueFactory(new PropertyValueFactory<>("toHourChange"));
        numberHoursColumn.setCellValueFactory(new PropertyValueFactory<>("numberHours"));
        amountChangeColumn.setCellValueFactory(new PropertyValueFactory<>("amountChange"));
        fromWhoColumn.setCellValueFactory(new PropertyValueFactory<>("fromWho"));
        whenGetColumn.setCellValueFactory(new PropertyValueFactory<>("whenGet"));
        boughtChangeColumn.setCellValueFactory(new PropertyValueFactory<>("boughtChange"));


        for (int i = 0; i < list.size(); i++) {

            mainTableChanges.getColumns().clear();

            mainTableChanges.getColumns().addAll(idColumn,dateChangeColumn,fromHourTable,toHourTable,numberHoursColumn,amountChangeColumn,fromWhoColumn,whenGetColumn,boughtChangeColumn,deleteChangeColumn);

            mainTableChanges.getItems().add(list.get(i));



        }
    }


    public void addButtonDelete() {
        deleteChangeColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteChangeColumn.setCellFactory(param -> new TableCell<ChangeDAO, ChangeDAO>() {
            private Button deleteButton = new Button("DELETE");

            @Override
            protected void updateItem(ChangeDAO change, boolean empty) {
                super.updateItem(change, empty);

                if (change == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                setAlignment(Pos.CENTER);



                deleteButtonAction(deleteButton,change);

            }
        });
    }

    public void deleteButtonAction(Button deleteButton, ChangeDAO change) {
        deleteButton.addEventFilter(ActionEvent.ACTION, event -> {
            System.out.println("Wciśnięto przycisk: "+ change.getIdChange());

            try {
                changeDAO.deleteChange(change.getIdChange());
                deleteButton.setDisable(true);
                deleteButton.setText("DELETED");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }

    public void refreshButton() throws SQLException {
        ChangeDAO changeDAO2 = new ChangeDAO();
        ObservableList<ChangeDAO> list1 = FXCollections.observableArrayList(changeDAO2.getChange(monthSpinner.getValue(),yearSpinner.getValue()));
        mainTableChanges.getItems().clear();
        viewResult(list1);
        addButtonDelete();
    }
}
