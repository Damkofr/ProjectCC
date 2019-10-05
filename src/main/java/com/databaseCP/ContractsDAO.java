package com.databaseCP;

import java.sql.*;
import java.util.ArrayList;

public class ContractsDAO {

    private int numberContract=0;
    private int idContract;
    private Date dateContract;
    private String typeContract;
    private  String nameContract;
    private String dateContractStr;
    private double amountContract;
    private boolean acceptedContract;

    public int getNumberContract() {
        return numberContract;
    }

    public int getIdContract() {
        return idContract;
    }

    public String getDateContractStr() {
        return dateContractStr;
    }

    public String getTypeContract() {
        return typeContract;
    }

    public String getNameContract() {
        return nameContract;
    }

    public double getAmountContract() {
        return amountContract;
    }

    public boolean isAcceptedContract() {
        return acceptedContract;
    }



    public ContractsDAO(){

    }

    public ContractsDAO(int idContract, int numberContract, String dateContractStr, String typeContract, String nameContract, double amountContract, boolean acceptedContract) {
        this.idContract = idContract;
        this.numberContract = numberContract;
        this.dateContractStr = dateContractStr;
        this.typeContract = typeContract;
        this.nameContract = nameContract;
        this.amountContract = amountContract;
        this.acceptedContract = acceptedContract;
    }

    public  ArrayList<ContractsDAO> getContract() throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        ArrayList<ContractsDAO> arrayContractsRecord = new ArrayList<>();



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("select * from contracts order by date;");
            resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            numberContract++;
            idContract = resultSet.getInt("id");
            dateContract = resultSet.getDate("date");
            typeContract = resultSet.getString("type_contract");
            nameContract = resultSet.getString("name_contract");
            amountContract = resultSet.getDouble("provision");
            acceptedContract = resultSet.getBoolean("accepted");

            dateContractStr = dateContract.toString();





            arrayContractsRecord.add(new ContractsDAO(idContract,numberContract,dateContractStr,typeContract,nameContract,amountContract,acceptedContract));
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
        return arrayContractsRecord;
    }

    public  ArrayList<ContractsDAO> getContract(int month, int year) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;
        ArrayList<ContractsDAO> arrayContractsRecord = new ArrayList<>();



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("select * from contracts where month(date)="+month+" and year(date)="+year+" order by date;");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                numberContract++;
                idContract = resultSet.getInt("id");
                dateContract = resultSet.getDate("date");
                typeContract = resultSet.getString("type_contract");
                nameContract = resultSet.getString("name_contract");
                amountContract = resultSet.getDouble("provision");
                acceptedContract = resultSet.getBoolean("accepted");

                dateContractStr = dateContract.toString();





                arrayContractsRecord.add(new ContractsDAO(idContract,numberContract,dateContractStr,typeContract,nameContract,amountContract,acceptedContract));
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
        return arrayContractsRecord;
    }





    public void viewRecord()throws SQLException{
        for(ContractsDAO x: getContract())
        {
            System.out.println(x.getDateContractStr()+" "+x.getTypeContract()+" "+x.getNameContract()+" "+x.getAmountContract()+
                    " "+x.isAcceptedContract());
        }
    }


    public void deleteContract(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("DELETE FROM contracts WHERE id="+id+";");

            preparedStatement.executeUpdate();

            System.out.println("DELETED record id: "+id);

            getContract();

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


    public void updateAcceptedContract(int id, boolean accepted) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        ResultSet resultSet = null;



        try{
            dbConnection = DbConfig.connectDataBase();
            preparedStatement = dbConnection.prepareStatement("UPDATE contracts SET accepted ="+accepted+" WHERE id="+id+";");

            preparedStatement.executeUpdate();

            System.out.println("UPDATE record id: "+id+"to: "+accepted);

            getContract();

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
