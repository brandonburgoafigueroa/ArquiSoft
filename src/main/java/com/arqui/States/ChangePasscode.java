package com.arqui.States;

import com.arqui.Interfaces.IConnection;
import com.arqui.DisplayState.DisplayChangePasscode;
import com.arqui.Interfaces.IState;
import com.arqui.Models.Mailbox;

public class ChangePasscode implements IState {

    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    private IConnection connection;
    public ChangePasscode(IConnection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        this.connection.setDisplay(new DisplayChangePasscode());
        showEnterNewPasscodeMessage();

    }
    public boolean dial(String command) {
        if (itIsANumeralCharacter(command))
        {
            setNewPasscodeToCurrentMailbox();
            changeToMailboxMenuState();
        }
        else {
            saveCommand(command);
        }
        return true;
    }

    private void setNewPasscodeToCurrentMailbox() {
        currentMailbox.setPasscode(accumulatedKeys);
        connection.saveChanges();
    }

    private void saveCommand(String command) {
        accumulatedKeys += command;
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private void showEnterNewPasscodeMessage() {
        connection.setInformation("ChangePasscode");
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
