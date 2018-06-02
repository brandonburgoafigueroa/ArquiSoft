package com.arqui;

import com.arqui.DisplayState.DisplayMailboxMenu;
import com.arqui.DisplayState.IDisplay;
import com.arqui.state.ChangeGreating;
import com.arqui.state.ChangePasscode;
import com.arqui.state.MessageMenu;

public class MailboxMenu implements IState {


    private Connection connection;
    private IDisplay display;
    public MailboxMenu(Connection connection){
        this.connection=connection;
        this.connection.setDisplay(new DisplayMailboxMenu());
        showMailboxMenuOptions();
    }
    public boolean dial(String command) {
        if ("1".equals(command)) {
            changeToMessageMenuState();

        } else if ("2".equals(command)) {
            changeToChangePasscodeState();


        } else if ("3".equals(command)) {
            changeToChangeGreetingState();

        }
        return true;
    }

    private void changeToChangeGreetingState() {
        connection.setStatus(new ChangeGreating(connection));
    }

    private void changeToChangePasscodeState() {
        connection.setStatus(new ChangePasscode(connection));
    }

    private void changeToMessageMenuState() {
        connection.setStatus(new MessageMenu(connection));
    }


    public boolean hangup() {
        connection.resetConnection();
        return true;
    }
    private void showMailboxMenuOptions() {

        connection.setOptions();
        connection.show();
    }
}
