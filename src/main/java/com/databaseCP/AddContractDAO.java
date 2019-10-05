package com.databaseCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

public class AddContractDAO {

    public static void insertRecordAddChange(LocalDate date,boolean isAgreement,double provision,String nameContract) throws SQLException {

        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;


        String insertTableSQL = "INSERT INTO CONTRACTS"
                + "(DATE, TYPE_CONTRACT, PROVISION, NAME_CONTRACT, ADD_DATE, ACCEPTED) VALUES"
                + "(?,?,?,?,?,?)";

        try{

            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setDate(1,dateSql(date));
            preparedStatement.setString(2,(isAgreement)?"Agreement":"Contract");
            preparedStatement.setDouble(3,(isAgreement)?3:provision);
            preparedStatement.setString(4,(isAgreement)?"Zgoda - edokumenty":nameContract);
            preparedStatement.setTimestamp(5,new Timestamp(getCurrentDatetime()));
            preparedStatement.setBoolean(6,false);




            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into CONTRACTS table!");




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
