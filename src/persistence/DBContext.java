package persistence;

import controller.Mailbox;
import controller.Message;
import controller.MessageQueue;
import controller.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBContext implements IPersistence {
    private DBConfiguration currentDBConfiguration;
    private String query;

    public DBContext(){
        currentDBConfiguration = new DBConfiguration();
        currentDBConfiguration.connect();

    }

    //listo
    public void saveChanges(Mailbox mailbox, int idCurrentMailbox) {
        updateMailbox(mailbox, idCurrentMailbox);
        updateMessages(mailbox, idCurrentMailbox);
    }
    //listo
    private void updateMessages(Mailbox mailbox, int idCurrentMailbox) {
        MessageQueue kept=mailbox.getKeptMessages();
        MessageQueue news=mailbox.getNewMessages();
        updateKeptMessages(idCurrentMailbox, kept);
        updateNewMessages(idCurrentMailbox, news);
    }
    //listo
    private void updateNewMessages(int idCurrentMailbox, MessageQueue news) {
        int quantityNewsOnMemory=news.size();
        int quantityNewsOnPersistence=getQuantityOfMessagesType(TypeOfMessage.New, idCurrentMailbox);
        if (quantityNewsOnMemory>quantityNewsOnPersistence)
        {
            for (int i=quantityNewsOnPersistence;i<quantityNewsOnMemory;i++)
            {
                Message message=news.getMessageOf(i);
                addMessage(idCurrentMailbox, message, TypeOfMessage.New);
            }
        }
        if (quantityNewsOnMemory<quantityNewsOnPersistence)
        {
            deleteMessageOf(idCurrentMailbox);
        }
    }


    //listo
    private void updateKeptMessages(int idCurrentMailbox, MessageQueue kept) {
        int quantityKeptOnMemory=kept.size();
        int quantityKetpOnPersistence=getQuantityOfMessagesType(TypeOfMessage.Kept, idCurrentMailbox);
        if (quantityKeptOnMemory>quantityKetpOnPersistence)
        {
            for (int i=quantityKetpOnPersistence;i<quantityKeptOnMemory;i++)
            {
                Message message=kept.getMessageOf(i);
                addMessage(idCurrentMailbox, message, TypeOfMessage.Kept);
            }
        }

    }
    //listo
    private void updateMailbox(Mailbox mailbox, int idMailbox) {
        String passcode=mailbox.getPasscode();
        String greeting=mailbox.getGreeting();
        saveChangesMailbox(idMailbox, passcode, greeting);
    }



    //sql
    //insert de un mensaje en la db con su type--- message.getText(), type=TypeOfMessage.Kept
    private void addMessage(int idCurrentMailbox, Message message, TypeOfMessage type) {
        if (type==TypeOfMessage.New) {
            insertMessageToMailBox(idCurrentMailbox, message, "New");
        }
        if (type==TypeOfMessage.Kept) {
            insertMessageToMailBox(idCurrentMailbox, message, "Kept");
        }
    }

    private void insertMessageToMailBox(int idCurrentMailbox, Message message,String type) {
        query = "INSERT INTO Message(idMailBox,message,state) VALUES(" + idCurrentMailbox + ",'" + message.getText() + "','" + type + "')";
        currentDBConfiguration.insert(query);
    }

    //sql
    //devolver la cantidad de mensajes que tiene un mailbox y un tipo
    //ej: devolver todos los mensajes del mailbox id=1 de type=TypeOfMessage.Kept
    private int getQuantityOfMessagesType(TypeOfMessage type, int idMailbox){

        if (type==TypeOfMessage.New)
        {
            return getTotalMessagesOf(idMailbox,"New");
        }
        if (type==TypeOfMessage.Kept)
        {
            return getTotalMessagesOf(idMailbox,"New");
        }
        return 0;
    }

    private int getTotalMessagesOf(int idMailbox,String state) {
        String total = "0";
        try {
            query = "SELECT COUNT(ME.state) FROM Message ME, MailBox MA  WHERE ME.idMailBox=MA.id AND MA.id=" + idMailbox + " AND ME.state='" + state + "';";
            ResultSet rs = currentDBConfiguration.select(query);
            while (rs.next()) {
                total = rs.getString("COUNT(ME.state)");
            }
            currentDBConfiguration.closeSelect(rs);
            return Integer.parseInt(total);
        } catch (SQLException ex) {
            System.out.println("no se pudo obtener el total de mensajes del id indicado" + ex);
            return 0;
        }
    }

    //sql
    //hacer un update a la info del mailbox con id=idMailbox
    private void saveChangesMailbox(int idMailbox, String passcode, String greeting)
    {
        query="UPDATE MailBox SET passcode='"+passcode+"', greeting = '"+greeting+"' WHERE id="+idMailbox+";";
        currentDBConfiguration.update(query);
    }


    //sql
    //Insert en mailbox
    public void addMailbox(Mailbox mailbox) {

        query="INSERT INTO MailBox(passcode,greeting) VALUES('"+mailbox.getPasscode()+"','"+mailbox.getGreeting()+"');";
        currentDBConfiguration.insert(query);
    }
    //sql
    //Devolver una lista con los mailbox con los greeting, passcode, keptMessages, newMessages cargados
    public ArrayList<Mailbox> getAlMailbox()
    {

       ArrayList<Mailbox> mailboxes = new ArrayList<>();
        try {
            query="SELECT * FROM MailBox;";
            ResultSet rs = currentDBConfiguration.select(query);
            while (rs.next()) {
                Mailbox mailbox = null;
                int ID = Integer.parseInt(rs.getString("id"));
                String passcode = rs.getString("passcode");
                String greeting = rs.getString("greeting");
                mailbox = new Mailbox(passcode,greeting);
                query="SELECT message, state FROM Message WHERE idMailBox="+ID;
                ResultSet rs2 = currentDBConfiguration.select(query);
                {
                    while (rs2.next()) {
                        Message m = null;
                        String message = rs2.getString("message");
                        String state = rs2.getString("state");
                        m = new Message(message);
                        if(state=="New")
                        {
                            mailbox.addMessage(m);
                        }
                        if(state=="Kept")
                        {
                            mailbox.addKeptMessage(m);
                        }
                    }
                }
                mailboxes.add(mailbox);
            }
            currentDBConfiguration.closeSelect(rs);
            return mailboxes;
        } catch (SQLException ex) {
            System.out.println("no se pudo obtener el total de mensajes" + ex);
            return null;
        }
    }
    private int getLastNewMessage(int idMailBox){
        String total = "0";
        try {
            query = "SELECT ME.id FROM Message ME, MailBox MA WHERE MA.id=ME.idMailBox AND ME.state='New' AND ME.idMailBox = "+idMailBox;
            ResultSet rs = currentDBConfiguration.select(query);
            while (rs.next()) {
                total = rs.getString("id");
            }
            currentDBConfiguration.closeSelect(rs);
            return Integer.parseInt(total);
        } catch (SQLException ex) {
            System.out.println("no se pudo obtener el ultimo mensaje del mailbox indicado" + ex);
            return 0;
        }
    }
    //sql
    //eliminar ultimo mensaje de tipo Type
    private void deleteMessageOf(int idMailbox) {

        int id = getLastNewMessage(idMailbox);
        String query = "DELETE FROM Message WHERE id="+id;
        currentDBConfiguration.delete(query);
    }
}
