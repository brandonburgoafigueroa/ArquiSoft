package com.arqui;

import com.arqui.DisplayState.DisplayChangeGreeting;
import com.arqui.DisplayState.DisplayMailboxMenu;

public class ChangeGreating implements IState {
    private Mailbox currentMailbox;
    private String currentRecording="";
    private Connection connection;

    ChangeGreating(Connection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        this.connection.setDisplay(new DisplayChangeGreeting());
        showNewGreetingMessage();

    }

    private void showNewGreetingMessage() {
        connection.setInformation("ChangeGreeting");
        connection.show();
    }

    public boolean dial(String command) {

        if (itIsANumeralCharacter(command))
        {
            setNewGreeting();
            changeToMailboxMenuState();
        }
        else
        {
            saveCommand(command);

        }
        return true;
    }

    private void saveCommand(String command) {
        currentRecording= command;

    }

    private void setNewGreeting() {
        currentMailbox.setGreeting(currentRecording);
        connection.saveChanges();

    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
        connection.setDisplay(new DisplayMailboxMenu());
        connection.show();
    }

    public boolean hangup() {
        connection.resetConnection();
        return true;
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

}
