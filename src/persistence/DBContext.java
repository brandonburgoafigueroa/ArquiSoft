package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContext {
    private DBConfiguration currentDBConfiguration;
    private String query;
    public DBContext(){
        currentDBConfiguration = new DBConfiguration();
        currentDBConfiguration.connect();
    }
    public void sendMessage(String transmitter, String receiver, String message){
        query = "INSERT INTO Messages (transmitter,receiver,message) " +
                "VALUES ('"+transmitter+"','"+receiver+"','"+message+"');";
        currentDBConfiguration.insert(query);
    }
    public void showAllMessages() {
        try{
            query = "SELECT * FROM Messages;";
            ResultSet rs = currentDBConfiguration.select(query);
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("transmitter");
                int age  = rs.getInt("receiver");
                String  address = rs.getString("message");

                System.out.println( "ID = " + id );
                System.out.println( "TRANSMITTER = " + name );
                System.out.println( "RECEIVER = " + age );
                System.out.println( "MESSAGE = " + address );
                System.out.println();
            }
            currentDBConfiguration.closeSelect(rs);
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener los datos"+ex);
        }

    }

}
