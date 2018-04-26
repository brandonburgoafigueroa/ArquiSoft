package controller;

public class ChangePasscode implements IState {

    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    private Connection connection;

    @Override
    public void start(String key, Connection connection) {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setPasscode(accumulatedKeys);
            connection.state = connection.MAILBOX_MENU;
            connection.status=new MailboxMenu();
            connection.updateObservables(MAILBOX_MENU_TEXT);
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private String EMPTY_MAILBOX_MESSAGE = "No messages.";
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";

}
