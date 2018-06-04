package com.arqui.Repository;

import java.sql.*;
import java.sql.Connection;

public class DatabaseConfiguration {
    String url = "prueba.db";
    Connection dbConnection;
    Statement statement = null;
    public DatabaseConfiguration() {

    }
    
    public void connect(){

        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
    public void create(String query)  {
        try{
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    public void insert(String query)  {
        try{

            dbConnection.setAutoCommit(true);
            statement = dbConnection.createStatement();
            statement.execute(query);
            statement.close();

        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
        }

    }
    public void update(String query)  {
        try{
            dbConnection.setAutoCommit(true);
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    public void delete(String query){
        try{
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            dbConnection.commit();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }


    }
    public ResultSet select(String query){
        try{
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery( query);
            return rs;
        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
            return null;
        }
    }

    public void closeSelect(ResultSet rs) throws SQLException {
        rs.close();
        statement.close();

    }
    public void close(){
        try{
            dbConnection.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
