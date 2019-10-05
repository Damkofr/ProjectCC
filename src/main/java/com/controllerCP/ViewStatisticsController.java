package com.controllerCP;

import com.databaseCP.StatisticsDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewStatisticsController implements Initializable {

    @FXML
    private Label numberHoursLabel;

    @FXML
    private Label numberHgraphicLabel;

    @FXML
    private Label numberHboughtLabel;

    @FXML
    private Label earningsLabel;

    @FXML
    private Label earningsBoughtLabel;

    @FXML
    private Label earningsGraphicLabel;

    @FXML
    private Label earningsProvisionLabel;

    @FXML
    private Label earningsAcceptedProvisionLabel;

    @FXML
    private Label earningsNotAcceptedProvisionLabel;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Spinner<Integer> yearSpinner;

    SpinnerValueFactory<Integer> spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,6);
    SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2018,2025,2019);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthSpinner.setValueFactory(spinner1);
        monthSpinner.setEditable(true);
        yearSpinner.setValueFactory(spinner2);
        yearSpinner.setEditable(true);
        try {


            double numberHours = StatisticsDAO.viewStatistics("SELECT SUM(to_hour - from_hour) FROM change_graphic");
            String a = Double.toString(numberHours);
            numberHoursLabel.setText(a);

            double numberHoursGraphic = StatisticsDAO.viewStatistics("SELECT SUM(to_hour-from_hour) FROM change_graphic WHERE for_who=\"ND\"");
            String b = Double.toString(numberHoursGraphic);
            numberHgraphicLabel.setText(b);

            double numberHoursBought = StatisticsDAO.viewStatistics("SELECT SUM(to_hour-from_hour) FROM change_graphic WHERE for_who!=\"ND\"");
            String c = Double.toString(numberHoursBought);
            numberHboughtLabel.setText(c);

            double numberEarnings = StatisticsDAO.viewStatistics("select (select coalesce(sum(amount),0) from change_graphic)+(select coalesce(SUM(provision),0)  from contracts where accepted =1);");
            String d = Double.toString(numberEarnings);
            earningsLabel.setText(d);

            double earningsGraphic = StatisticsDAO.viewStatistics("select sum(amount) from change_graphic where for_who=\"ND\"");
            String e = Double.toString(earningsGraphic);
            earningsGraphicLabel.setText(e);

            double earningsBought = StatisticsDAO.viewStatistics("select sum(amount) from change_graphic where when_get=\"salary\" or when_get=\"cash\"");
            String f = Double.toString(earningsBought);
            earningsBoughtLabel.setText(f);

            double earningsAllProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts");
            String g = Double.toString(earningsAllProvision);
            earningsProvisionLabel.setText(g);

            double earningsAcceptedProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts where accepted = 1");
            String h = Double.toString(earningsAcceptedProvision);
            earningsAcceptedProvisionLabel.setText(h);

            double earningsNotAcceptedProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts where accepted = 0");
            String i = Double.toString(earningsNotAcceptedProvision);
            earningsNotAcceptedProvisionLabel.setText(i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewScopeStatistics() throws SQLException {
        //number of hours with scope
        double numberHours = StatisticsDAO.viewStatistics("SELECT SUM(to_hour - from_hour) FROM change_graphic","where","change_date",monthSpinner.getValue(),yearSpinner.getValue());
        String a = Double.toString(numberHours);
        numberHoursLabel.setText(a);

        //number Hours graphic with scope
        double numberHoursGraphic = StatisticsDAO.viewStatistics("SELECT SUM(to_hour-from_hour) FROM change_graphic WHERE for_who=\"ND\"","and","change_date",monthSpinner.getValue(),yearSpinner.getValue());
        String b = Double.toString(numberHoursGraphic);
        numberHgraphicLabel.setText(b);

        //number hours bought with scope
        double numberHoursBought = StatisticsDAO.viewStatistics("SELECT SUM(to_hour-from_hour) FROM change_graphic WHERE for_who!=\"ND\"","and","change_date",monthSpinner.getValue(),yearSpinner.getValue());
        String c = Double.toString(numberHoursBought);
        numberHboughtLabel.setText(c);

        //all earnings
        double allEarinigs = StatisticsDAO.viewAllEarningsStatistics(monthSpinner.getValue(),yearSpinner.getValue());
        String d = Double.toString(allEarinigs);
        earningsLabel.setText(d);


        //earnings with graphic
        double earningsGraphic = StatisticsDAO.viewStatistics("select sum(amount) from change_graphic where for_who=\"ND\"","and","change_date",monthSpinner.getValue(),yearSpinner.getValue());
        String e = Double.toString(earningsGraphic);
        earningsGraphicLabel.setText(e);

        //earnings with bought
        double earnigsBought = StatisticsDAO.viewBoughtChangeStatistics(monthSpinner.getValue(),yearSpinner.getValue());
        String f = Double.toString(earnigsBought);
        earningsBoughtLabel.setText(f);

        //earnings with all provision
        double earningsAllProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts","where","date",monthSpinner.getValue(),yearSpinner.getValue());
        String g = Double.toString(earningsAllProvision);
        earningsProvisionLabel.setText(g);

        //earnings with accepted provision
        double earningsAcceptedProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts where accepted = 1","and","date",monthSpinner.getValue(),yearSpinner.getValue());
        String h = Double.toString(earningsAcceptedProvision);
        earningsAcceptedProvisionLabel.setText(h);

        //earnings with not accepted provision
        double earningsNotAcceptedProvision = StatisticsDAO.viewStatistics("select sum(provision) from contracts where accepted = 0","and","date",monthSpinner.getValue(),yearSpinner.getValue());
        String i = Double.toString(earningsNotAcceptedProvision);
        earningsNotAcceptedProvisionLabel.setText(i);

    }








}
