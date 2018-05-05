package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    String url = "prueba.db";
    Connection dbConnection;
    Statement statement = null;
    public DBContext() {

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
            close();
        }
        catch(SQLException ex)
        {
            System.out.println("No se pudo realizar la consulta: "+ex);
        }

    }
    public void insert(String query)  {
        try{
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            dbConnection.commit();
            dbConnection.close();
        }
        catch(SQLException ex)
        {
            System.out.println("No se pudo realizar la consulta: "+ex);
        }

    }
    public void close(){
        try{
            dbConnection.close();
        }
        catch(SQLException ex){
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
