package persistence;

import controller.Mailbox;
import controller.Message;
import controller.MessageQueue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBContext implements IPersistence {
    private DBConfiguration currentDBConfiguration;
    private String query;
    public DBContext(){
        currentDBConfiguration = new DBConfiguration();
        currentDBConfiguration.connect();
    }

    public void saveChanges(Mailbox mailbox, int idCurrentMailbox){
        MessageQueue kept=mailbox.getKeptMessages();
        saveMailBox(mailbox, idCurrentMailbox);
        int QuantityOfRecordsOnDB=findKeptMessagesOfMailBox(idCurrentMailbox).size();
        for (int i=QuantityOfRecordsOnDB; i<kept.size(); i++)
        {
            Message message=kept.getMessageOf(i);
            saveMessage(idCurrentMailbox, message);
        }
    }

    private void saveMailBox(Mailbox mailbox, int idCurrentMailbox) {
        String passcode=mailbox.getPasscode();
        String greeting=mailbox.getGreeting();
        query="UPDATE MailBox SET passcode='"+passcode+"','"+greeting+"' WHERE = id="+idCurrentMailbox+";";
        currentDBConfiguration.update(query);
    }
    public void addMailbox(Mailbox mailbox) {
        String passcode=mailbox.getPasscode();
        String greeting=mailbox.getGreeting();
        query="INSERT INTO MailBox(passcode,greeting) VALUES ('"+passcode+"','"+greeting+"';";
        currentDBConfiguration.update(query);
    }

    public void saveMessage(int idMailbox, Message message){
        query="INSERT INTO Message (idMailBox,message) VALUES ("+idMailbox+",'"+message.getText()+"');";
        currentDBConfiguration.insert(query);
    }
    public void sendMessage(String transmitter, String receiver, String message){
        query = "INSERT INTO Messages (transmitter,receiver,message) " +
                "VALUES ('"+transmitter+"','"+receiver+"','"+message+"');";
        currentDBConfiguration.insert(query);
    }
    public Mailbox getMailBoxWithMessages(String idMailBox){
        Mailbox mailbox;
        Mailbox mailboxResult;
        mailbox = findMailBox(idMailBox);
        if(mailbox!=null){
            mailboxResult=findMessagesOfMailBox(idMailBox,mailbox);
        }
        else{
            mailboxResult=null;
        }
        return mailboxResult;
    }
    private Mailbox findMailBox(String idMailBox) {
        Mailbox mailbox=null;
        try{
            query = "SELECT passcode, greeting FROM MailBox WHERE id="+idMailBox+";";
            ResultSet rs = currentDBConfiguration.select(query);

            while ( rs.next() ) {
                String  passcode = rs.getString("passcode");
                String  greeting = rs.getString("greeting");
                mailbox=new Mailbox(passcode,greeting);
            }
            currentDBConfiguration.closeSelect(rs);
            return mailbox;
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener el mailbox"+ex);
            return null;
        }
    }
    public List <Mailbox> getAlMailbox() {
        List<Mailbox> mailboxes=new ArrayList<>();
        Mailbox mailbox=null;
        try{
            query = "SELECT passcode, greeting FROM MailBox;";
            ResultSet rs = currentDBConfiguration.select(query);

            while ( rs.next() ) {
                String  passcode = rs.getString("passcode");
                String  greeting = rs.getString("greeting");
                mailbox=new Mailbox(passcode,greeting);
                mailboxes.add(mailbox);
            }
            currentDBConfiguration.closeSelect(rs);
            return mailboxes;
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener el mailbox"+ex);
            return null;
        }
    }
    private Mailbox findMessagesOfMailBox(String idMailBox, Mailbox mailbox) {
        try{
            query = "SELECT message  FROM Message WHERE idMailBox='"+idMailBox+"';";
            ResultSet rs = currentDBConfiguration.select(query);

            while ( rs.next() ) {
                Message message = new Message(rs.getString("message"));
                mailbox.addMessage(message);
            }
            currentDBConfiguration.closeSelect(rs);
            return mailbox;
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener el mailbox"+ex);
            return null;
        }

    }
    public List<Message> findKeptMessagesOfMailBox(int idMailBox) {
        try{
            query = "SELECT message  FROM Message WHERE idMailBox='"+idMailBox+"';";
            ResultSet rs = currentDBConfiguration.select(query);
            List<Message> kept=new ArrayList<>();
            while ( rs.next() ) {
                Message message = new Message(rs.getString("message"));
                kept.add(message);
            }
            currentDBConfiguration.closeSelect(rs);
            return kept;
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener el mailbox"+ex);
            return null;
        }

    }
    public List<Message> showAllMessages() {
        List<Message> messages=new ArrayList<Message>();
        try{

            query = "SELECT * FROM Messages;";
            ResultSet rs = currentDBConfiguration.select(query);
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("transmitter");
                int age  = rs.getInt("receiver");
                String  address = rs.getString("message");
                Message message=new Message(name, address);
                System.out.println( "ID = " + id );
                System.out.println( "TRANSMITTER = " + name );
                System.out.println( "RECEIVER = " + age );
                System.out.println( "MESSAGE = " + address );
                System.out.println();
                messages.add(message);
            }
            currentDBConfiguration.closeSelect(rs);
            return messages;
        }
        catch (SQLException ex){
            System.out.println("no se pudo obtener los datos"+ex);
            return null;
        }

    }


}
