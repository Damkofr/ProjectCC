package com.databaseCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

public class AddChangeBoughtDAO {

    public static void insertRecordAddChange(int fromHour, int toHour, LocalDate date, String forWho, double amount,boolean cash,boolean bought) throws SQLException {

        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;

        String mess = "ND";

        String insertTableSQL = "INSERT INTO CHANGE_GRAPHIC"
                + "(FROM_HOUR, TO_HOUR, CHANGE_DATE, ADD_DATE,AMOUNT,FOR_WHO,WHEN_GET,BOUGHT_SOLD) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        try{

            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1,fromHour);
            preparedStatement.setInt(2, toHour);
            preparedStatement.setDate(3,dateSql(date));
            preparedStatement.setTimestamp(4,new Timestamp(getCurrentDatetime()));
            preparedStatement.setDouble(5,amount);
            preparedStatement.setString(6,forWho);
            preparedStatement.setString(7,(cash)?"cash":"salary");
            preparedStatement.setString(8,(bought)?"bought":"sold");






            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into CHANGE_GRAPHIC table!");




        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }

    }

    private static java.sql.Date dateSql(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    private static long getCurrentDatetime() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();
        long time = currentTime.getTime();
        return time;

    }

}
