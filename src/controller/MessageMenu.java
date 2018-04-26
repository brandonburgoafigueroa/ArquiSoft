package controller;

public class MessageMenu implements IState{
    private Mailbox currentMailbox;
    private Connection connection;

    @Override
    public void start(String key, Connection connection) {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        switch (key) {
            case "1":
                String output = "";
                Message m = currentMailbox.getCurrentMessage();
                if (m == null) {
                    output += EMPTY_MAILBOX_MESSAGE + "\n";
                }
                else output += m.getText() + "\n";
                output += MESSAGE_MENU_TEXT;
                connection.updateObservables(output);
                break;
            case "2":
                currentMailbox.saveCurrentMessage();
                connection.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "3":
                currentMailbox.removeCurrentMessage();
                connection.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "4":
                connection.state = connection.MAILBOX_MENU;
                connection.updateObservables(MAILBOX_MENU_TEXT);
                break;
        }
    }

    @Override
    public void hangup() {
        connection.resetConnection();
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
