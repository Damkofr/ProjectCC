package com.databaseCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsDAO {

    private String numberHoursAll = "SELECT SUM(to_hour - from_hour) FROM change_graphic;";
    private String NumberHoursGraphic ="SELECT SUM(to_hour-from_hour) FROM change_graphic WHERE for_who=\"ND\";";
    private String earningsTotal = "select (select sum(amount) from change_graphic)+(select SUM(provision) from contracts);";
    private String earningsGraphic = "select sum(amount) from change_graphic where amount=\"ND\";";
    private String earningsBought = "select sum(amount) from change_graphic where when_get=\"salary\" or when_get=\"cash\";";
    private String earningsProvision = "select sum(provision) from contracts;";



    public static double viewStatistics(String query) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        double numberHours;

        String sqlFromHour = query;


        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(sqlFromHour);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();


             numberHours = resultSet.getDouble(1);
            System.out.println(numberHours);
            return numberHours;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }
        return 0;
    }

    public static double viewStatistics(String query,String func,String column, int month,int years) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        double numberHours;

        String sqlFromHour = query+" "+func+" month("+column+")="+month+" and year("+column+")="+years+";";


        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(sqlFromHour);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();


            numberHours = resultSet.getDouble(1);
            System.out.println(numberHours);
            return numberHours;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }
        return 0;
    }

    public static double viewAllEarningsStatistics(int month, int years) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        double numberHours;

        String sqlFromHour = "select (select coalesce(sum(amount), 0) from change_graphic where month(change_date) = "+month+" and year(change_date) ="+years+") + (select coalesce(sum(provision), 0)  from contracts where accepted = 1 and month(date) ="+month+" and year(date) = "+years+")";



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(sqlFromHour);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();


            numberHours = resultSet.getDouble(1);
            System.out.println(numberHours);
            return numberHours;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }
        return 0;
    }


    public static double viewBoughtChangeStatistics(int month,int years) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        double numberHours;

        String sqlFromHour = "select sum(amount) from change_graphic where (when_get=\"salary\" or when_get=\"cash\") and month(change_date)="+month+" and year(change_date)="+years;



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(sqlFromHour);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();


            numberHours = resultSet.getDouble(1);
            System.out.println(numberHours);
            return numberHours;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }
        return 0;
    }

}
