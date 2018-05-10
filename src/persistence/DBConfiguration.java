package persistence;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConfiguration {
    String url = "prueba.db";
    Connection dbConnection;
    Statement statement = null;
    public DBConfiguration() {

    }

    public void connect(){

        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            if (dbConnection != null){
                System.out.println("Opened database successfully");
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }

    }
    public void create(String query)  {
        try{
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //close();
        }
        catch(SQLException ex)
        {
            System.out.println("No se pudo realizar la consulta create: "+ex);
        }

    }
    public void insert(String query)  {
        try{

            dbConnection.setAutoCommit(true);
            statement = dbConnection.createStatement();
            statement.execute(query);
            statement.close();
            //dbConnection.commit();
            //dbConnection.close();
        }
        catch(SQLException ex)
        {
            System.out.println("No se pudo realizar la consulta insert: "+ex);
        }

    }
    public void update(String query)  {
        try{
            dbConnection.setAutoCommit(true);
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            //dbConnection.commit();
            //dbConnection.close();
        }
        catch(SQLException ex)
        {
            System.out.println("No se pudo realizar la consulta update: "+ex);
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
            System.out.println("No se pudo realizar la consulta delete: "+ex);
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
            System.out.println("No se pudo realizar la consulta select: "+ex);
            return null;
        }

    }
    public void closeSelect(ResultSet rs) throws SQLException {
        rs.close();
        statement.close();
        //dbConnection.close();
    }
    public void close(){
        try{
            dbConnection.close();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
