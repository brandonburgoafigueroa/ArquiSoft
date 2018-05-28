package com.arqui;

import com.arqui.DisplayState.DisplayLogin;
import com.arqui.DisplayState.IDisplay;

public class Login implements IState{
    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    Connection connection;
    Login(Connection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        this.connection.setDisplay(new DisplayLogin());
    }
    public boolean dial(String command) {
        if (itIsANumeralCharacter(command))
        {
            return openMailboxMenu();
        }
        else {
            saveActualCommand(command);
            return true;
        }

    }

    private boolean openMailboxMenu() {
        if (isTheCorrectPasscodeOfCurrentMailbox())
        {
            changeToMailboxMenuState();
            return true;
        }
        else {
            showIncorrectPasscodeMessage();
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

    private void showIncorrectPasscodeMessage() {
        connection.showError("Invalid_Passcode");
    }



    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private boolean isTheCorrectPasscodeOfCurrentMailbox() {
        return currentMailbox.checkPasscode(accumulatedKeys);
    }

    public boolean hangup() {
        connection.resetConnection();
        return true;
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

}
