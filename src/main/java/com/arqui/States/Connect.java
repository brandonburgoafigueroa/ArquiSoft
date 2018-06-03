package com.arqui.States;

import com.arqui.Interfaces.IModelView;
import com.arqui.ModelViews.ConnectModelView;
import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IMailSystem;
import com.arqui.Interfaces.IState;
import com.arqui.ModelViews.RecordingModelView;
import com.arqui.Models.Mailbox;
import com.arqui.Responses.ConnectResponseError;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private IMailSystem system;
    private String accumulatedKeys="";
    private IConnection connection;
    public Connect(IConnection connection) {
        this.connection=connection;
        this.system=connection.getMailboxSystem();
        showInitialPromptMessage();
    }
    private void showInitialPromptMessage() {
        this.connection.setModelView(new ConnectModelView());
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
        connection.setError(new ConnectResponseError());
    }

    private void showGreetingMessage() {
        IModelView modelView=new RecordingModelView();
        modelView.setInformation(currentMailbox.getGreeting());
       connection.setModelView(modelView);
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
