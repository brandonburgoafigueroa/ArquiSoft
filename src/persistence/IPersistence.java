package persistence;

import controller.Message;

import java.util.List;

public interface IPersistence {
    void sendMessage(String transmitter, String receiver, String message);
    List<Message> showAllMessages();
}
