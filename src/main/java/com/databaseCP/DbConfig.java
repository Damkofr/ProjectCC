package com.databaseCP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;


public class DbConfig {

    private static Connection con;
    private static String url ="jdbc:mysql://localhost/dawid?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=";
    private static String user ="root";
    private static String password ="Ligami12*";


    public static Connection connectDataBase(){

        Connection dbConnection = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


        }catch (ClassNotFoundException e){
            System.out.println(e);
        }

        try{
            dbConnection = DriverManager.getConnection(url + TimeZone.getDefault().getID(), user, password);
            System.out.println("Connected with databaseCP");
            return dbConnection;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }


}
