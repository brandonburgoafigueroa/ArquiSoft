package controller;

public class MailboxMenu implements IState {

    private Connection connection;
    MailboxMenu(Connection connection){
        this.connection=connection;
        showMailboxMenuOptions();
    }
    @Override
    public void start(String command) {
        switch (command) {
            case "1":
                connection.setStatus(new MessageMenu(connection));
                connection.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "2":
                connection.setStatus(new ChangePasscode(connection));
                connection.updateObservables(ENTER_NEW_PASSCODE_MESSAGE);
                break;
            case "3":
                connection.setStatus(new ChangeGreating(connection));

                connection.updateObservables(ENTER_NEW_GREETING_MESSAGE);
                break;
        }
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }
    private void showMailboxMenuOptions() {
        connection.updateObservables(MAILBOX_MENU_TEXT);
    }
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";

}
