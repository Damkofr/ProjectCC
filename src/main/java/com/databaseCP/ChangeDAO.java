package com.databaseCP;

import java.sql.*;
import java.util.ArrayList;

public class ChangeDAO {

    //extra field
    int sumHours;
    int sumAmount;

    //contructor field
    private int idChange;
    private int numberChange;
    private Date dateChange;
    private String dateChangeToStr;
    private int fromHourChange;
    private int toHourChange;
    private int numberHours;
    private double amountChange;
    private String fromWho;
    private String whenGet;
    private String boughtChange;

    public int getIdChange() {
        return idChange;
    }

    public int getNumberChange() {
        return numberChange;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public String getDateChangeToStr() {
        return dateChangeToStr;
    }

    public int getFromHourChange() {
        return fromHourChange;
    }

    public int getToHourChange() {
        return toHourChange;
    }

    public int getNumberHours() {
        return numberHours;
    }

    public double getAmountChange() {
        return amountChange;
    }

    public String getFromWho() {
        return fromWho;
    }

    public String getWhenGet() {
        return whenGet;
    }

    public String getBoughtChange() {
        return boughtChange;
    }

    public ChangeDAO(){

    }

    public ChangeDAO(int idChange, int numberChange, String dateChangeToStr, int fromHourChange, int toHourChange, int numberHours, double amountChange, String fromWho, String whenGet, String boughtChange) {
        this.idChange = idChange;
        this.numberChange = numberChange;
        this.dateChangeToStr = dateChangeToStr;
        this.fromHourChange = fromHourChange;
        this.toHourChange = toHourChange;
        this.numberHours = numberHours;
        this.amountChange = amountChange;
        this.fromWho = fromWho;
        this.whenGet = whenGet;
        this.boughtChange = boughtChange;
    }

    public ArrayList<ChangeDAO> getChange() throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        ArrayList<ChangeDAO> arrayChangeRecord = new ArrayList<>();



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("select * from change_graphic order by change_date;");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                numberChange++;
                idChange = resultSet.getInt("id");
                dateChange = resultSet.getDate("change_date");
                fromHourChange = resultSet.getInt("from_hour");
                toHourChange = resultSet.getInt("to_hour");
                amountChange = resultSet.getDouble("amount");
                fromWho = resultSet.getString("for_who");
                whenGet = resultSet.getString("when_get");
                boughtChange = resultSet.getString("bought_sold");

                dateChangeToStr = dateChange.toString();
                numberHours = toHourChange - fromHourChange;






                arrayChangeRecord.add(new ChangeDAO(idChange,numberChange,dateChangeToStr,fromHourChange,toHourChange,numberHours,amountChange,fromWho,whenGet,boughtChange));
            }

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
        return arrayChangeRecord;
    }

    public ArrayList<ChangeDAO> getChange(int month,int year) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        ArrayList<ChangeDAO> arrayChangeRecord = new ArrayList<>();



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("select * from change_graphic where month(change_date)="+month+" and year(change_date)="+year+" order by change_date;");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                numberChange++;
                idChange = resultSet.getInt("id");
                dateChange = resultSet.getDate("change_date");
                fromHourChange = resultSet.getInt("from_hour");
                toHourChange = resultSet.getInt("to_hour");
                amountChange = resultSet.getDouble("amount");
                fromWho = resultSet.getString("for_who");
                whenGet = resultSet.getString("when_get");
                boughtChange = resultSet.getString("bought_sold");

                dateChangeToStr = dateChange.toString();
                numberHours = toHourChange - fromHourChange;








                arrayChangeRecord.add(new ChangeDAO(idChange,numberChange,dateChangeToStr,fromHourChange,toHourChange,numberHours,amountChange,fromWho,whenGet,boughtChange));
            }

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
        return arrayChangeRecord;
    }

    public void deleteChange(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("DELETE FROM change_graphic WHERE id="+id+";");

            preparedStatement.executeUpdate();

            System.out.println("DELETED record id: "+id);

            getChange();

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

    }
}
