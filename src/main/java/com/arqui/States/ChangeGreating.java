package com.arqui.States;

import com.arqui.Interfaces.IConnection;
import com.arqui.ModelViews.ChangeGreetingModelView;
import com.arqui.Interfaces.IState;
import com.arqui.Models.Mailbox;

public class ChangeGreating implements IState {
    private Mailbox currentMailbox;
    private String currentRecording="";
    private IConnection connection;
    public ChangeGreating(IConnection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        showNewGreetingMessage();

    }

    private void showNewGreetingMessage() {
        connection.setModelView(new ChangeGreetingModelView());
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
    }

    public boolean hangup() {
        connection.resetConnection();
        return true;
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

}
