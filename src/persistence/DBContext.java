package persistence;

import controller.Mailbox;
import controller.Message;
import controller.MessageQueue;

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
        if (type==TypeOfMessage.New)
        {

        }
        if (type==TypeOfMessage.Kept)
        {

        }
    }
    //sql
    //devolver la cantidad de mensajes que tiene un mailbox y un tipo
    //ej: devolver todos los mensajes del mailbox id=1 de type=TypeOfMessage.Kept
    private int getQuantityOfMessagesType(TypeOfMessage type, int idMailbox) {
        if (type==TypeOfMessage.New)
        {

        }
        if (type==TypeOfMessage.Kept)
        {

        }
       return 0;
    }

    //sql
    //hacer un update a la info del mailbox con id=idMailbox
    private void saveChangesMailbox(int idMailbox, String passcode, String greeting) {

    }


    //sql
    //Insert en mailbox
    public void addMailbox(Mailbox mailbox) {


    }
    //sql
    //Devolver una lista con los mailbox con los greeting, passcode, keptMessages, newMessages cargados
    public ArrayList<Mailbox> getAlMailbox()
    {

        return null;
    }

    //sql
    //eliminar ultimo mensaje de tipo Type
    private void deleteMessageOf(int idMailbox) {

    }
}
