package controller;

public class MessageMenu implements IState{
    private Mailbox currentMailbox;
    private Connection connection;
    MessageMenu(Connection connection){
        this.connection=connection;
    }
    @Override
    public void start(String key) {
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
                connection.setStatus(new MailboxMenu(connection));
                connection.updateObservables(MAILBOX_MENU_TEXT);
                break;
        }
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }

    private String EMPTY_MAILBOX_MESSAGE = "No messages.";
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
