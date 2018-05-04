package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    String url = "";
    Connection dbConnection;
    public void connect(){
        try {
                dbConnection= DriverManager.getConnection("jdbc:sqlite:"+url);
                if (dbConnection!=null){
                    System.out.println("Conectado");
                }
            }
        catch(SQLException ex) {
            System.out.println("Fallo:" + ex);
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
