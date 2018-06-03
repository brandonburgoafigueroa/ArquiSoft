package com.arqui.state;

import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IMailSystem;
import com.arqui.DisplayState.DisplayConnect;
import com.arqui.Interfaces.IState;
import com.arqui.Entities.Mailbox;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private IMailSystem system;
    private String accumulatedKeys="";
    private IConnection connection;
    DisplayConnect display;
    public Connect(IConnection connection) {
        this.connection=connection;
        this.system=connection.getMailboxSystem();
        this.connection.setDisplay(new DisplayConnect());
        showInitialPromptMessage();
    }
    private void showInitialPromptMessage() {
        connection.setInformation("InitialPrompt");
        connection.show();
    }

    public boolean dial(String command) {

        if (itIsANumeralCharacter(command))
            return openMailbox();
        else
            saveActualCommand(command);
        return true;
    }

    private boolean openMailbox() {
        setupMailbox();
        if (isAValidMailbox())
        {
            setCurrentMailboxToConnection();
            showGreetingMessage();
            changeToRecordingState();
            return true;
        }
        else {
            showIncorrectMailboxMessage();
            cleanAccumulatedKeys();
            return false;
        }
    }

    private void saveActualCommand(String command) {
        accumulatedKeys += command;
    }

    private void cleanAccumulatedKeys() {
        accumulatedKeys = "";
    }

    private void setupMailbox() {
        currentMailbox = system.findMailbox(accumulatedKeys);
    }

    private void showIncorrectMailboxMessage() {
        connection.setError("Invalid");
        connection.show();
    }

    private void showGreetingMessage() {
        connection.setTextPlain(currentMailbox.getGreeting());
        connection.show();
    }

    private void setCurrentMailboxToConnection() {
        connection.setMailbox(currentMailbox);
    }

    private void changeToRecordingState() {
        connection.setStatus(new Recording(connection));
    }

    private boolean isAValidMailbox() {
        return currentMailbox != null;
    }

    public boolean hangup() {
        connection.resetConnection();
        return true;
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
}
