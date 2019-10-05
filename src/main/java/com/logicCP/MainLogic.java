package com.logicCP;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLogic {


   public void chooseView(String chooseView) throws IOException {
       switch (chooseView) {
           case "addChangeMainButton":
               openNewView("/addChangeVIEW.fxml");
               break;

           case "viewChangesButton":
               openNewView("/viewChangesVIEW.fxml");
               break;

           case "addChangeBoughtButton":
               openNewView("/addChangeBoughtVIEW.fxml");

               break;
           case "viewContractsButton":
               openNewView("/viewContractsVIEW.fxml");

               break;

           case "addContractButton":
               openNewView("/addContractVIEW.fxml");
               break;

           case "viewStatisticsButton":
               openNewView("/viewStatiscticsVIEW.fxml");
               break;

               default:
                   System.out.println("default");
                   break;

       }
   }

   private void openNewView(String pathView) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathView));
       Parent root1 = fxmlLoader.load();
       Stage stage = new Stage();
       stage.setScene(new Scene(root1));
       stage.show();
   }


}
