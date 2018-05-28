package com.arqui;

import com.arqui.DisplayState.DisplayConnect;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private MailSystem system;
    private String accumulatedKeys="";
    private Connection connection;
    DisplayConnect display;
    Connect(Connection connection) {
        this.connection=connection;
        this.system=connection.getMailboxSystem();
        display=new DisplayConnect();
        showInitialPromptMessage();
    }
    private void showInitialPromptMessage() {
        connection.ShowText(display.getText("InitialPrompt"));
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
        connection.ShowText(display.getError("Invalid"));
    }

    private void showGreetingMessage() {
        connection.ShowText(currentMailbox.getGreeting());
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
