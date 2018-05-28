package com.arqui;

public class MailboxMenu implements IState {


    private Connection connection;
    MailboxMenu(Connection connection){
        this.connection=connection;
        showMailboxMenuOptions();
    }
    public void dial(String command) {
        if ("1".equals(command)) {
            changeToMessageMenuState();

        } else if ("2".equals(command)) {
            changeToChangePasscodeState();


        } else if ("3".equals(command)) {
            changeToChangeGreetingState();

        }
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


    public void hangup() {
        connection.resetConnection();
    }
    private void showMailboxMenuOptions() {
        connection.updateObservers(MAILBOX_MENU_TEXT);
    }
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";


}
