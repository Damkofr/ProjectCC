package com.controllerCP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {


    public void start(Stage stage) throws Exception {


        AnchorPane pane = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();




        /*DbConfig.connectDataBase();
        DbConfig.closeConnect();*/


    }

    @Override
    public void init() {
        System.out.println("Init");
    }

    @Override
    public void stop() {
        System.out.println("Stop");
    }


    public static void main(String[] args) throws SQLException {

        launch(args);

        /*ContractsDAO contractsDAO = new ContractsDAO();
        contractsDAO.getContract();
        contractsDAO.viewRecord();*/





    }


}
