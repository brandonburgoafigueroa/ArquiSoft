package com.arqui.States;

import com.arqui.Interfaces.IConnection;
import com.arqui.Models.Mailbox;
import com.arqui.Models.Message;
import com.arqui.Interfaces.IState;

public class Recording implements IState {
    String message;
    private IConnection connection;
    private Mailbox currentMailbox;
    public Recording(IConnection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();

    }
    public boolean dial(String command) {
        if (isAMessage(command))
        {
            addMessage(command);
            return true;
        }
        if (isNumericalCommand(command))
        {
            changeStateToLogin();
            return executeCommand(command);
        }
return true;
    }

    private boolean executeCommand(String command) {
        return connection.executeCommand(command);
    }

    private void addMessage(String command) {
        message= command;
    }

    private boolean isAMessage(String key) {
        return key.length()>1;
    }

    private void changeStateToLogin() {
        connection.setStatus(new Login(connection));
    }


    public boolean hangup() {
        if (isNotTheMessageEmpty())
        {
            saveMessage();
        }
        connection.resetConnection();
        return true;
    }

    private boolean isNotTheMessageEmpty() {
        return message!=null;
    }

    private void saveMessage() {
        currentMailbox.addMessage(new Message(message));
        connection.saveChanges();
    }

    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

}
