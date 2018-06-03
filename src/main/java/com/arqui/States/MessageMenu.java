package com.arqui.States;

import com.arqui.Interfaces.IConnection;
import com.arqui.DisplayState.DisplayMessageMenu;
import com.arqui.Interfaces.IDisplay;
import com.arqui.Models.Mailbox;
import com.arqui.Models.Message;
import com.arqui.Interfaces.IState;

public class MessageMenu implements IState {
    private Mailbox currentMailbox;
    private IConnection connection;
    private IDisplay display;
    public MessageMenu(IConnection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        this.connection.setDisplay(new DisplayMessageMenu());
        showMessageMenuOptions();
    }
    public boolean dial(String command) {

        if ("1".equals(command)) {
            showMessageText();
            showMessageMenuOptions();
        } else if ("2".equals(command)) {
            saveCurrentMessage();
            showMessageMenuOptions();

        } else if ("3".equals(command)) {
            removeCurrentMessage();
            showMessageMenuOptions();

        } else if ("4".equals(command)) {
            changeToMailboxMenuState();

        }
        return true;
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private void removeCurrentMessage() {
        currentMailbox.removeCurrentMessage();
        connection.saveChanges();
    }

    private void saveCurrentMessage() {
        currentMailbox.saveCurrentMessage();
        connection.saveChanges();
    }

    private void showMessageText() {

        connection.setTextPlain(getTextOfLastMessage());
    }

    private String getTextOfLastMessage() {
        String output="";
        Message m = currentMailbox.getCurrentMessage();
        if (m == null) {
            connection.setInformation("Empty");
        }
        else {
            output = m.getText();
        }
        return output;
    }
    public boolean hangup() {
        connection.resetConnection();
        return true;
    }
    private void showMessageMenuOptions() {
        connection.setOptions();
        connection.show();
    }

}
