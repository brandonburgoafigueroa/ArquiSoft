package persistence;

import controller.Mailbox;
import controller.Message;

import java.util.List;

public interface IPersistence {
    void sendMessage(String transmitter, String receiver, String message);
    Mailbox getMailBoxWithMessages(String idMailBox);
    List<Message> showAllMessages();
}
