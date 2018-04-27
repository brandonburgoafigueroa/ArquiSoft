package controller;

public class ChangeGreating implements IState {
    private Mailbox currentMailbox;
    private String currentRecording="";
    private Connection connection;
    ChangeGreating(Connection connection)
    {
        this.connection=connection;
        connection.updateObservables(ENTER_NEW_GREETING_MESSAGE);
    }
    @Override
    public void start(String command) {
        this.currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(command))
        {
            currentMailbox.setGreeting(currentRecording);
            currentRecording = "";
            connection.setStatus(new MailboxMenu(connection));
            connection.updateObservables(MAILBOX_MENU_TEXT);
        }
        else
        {
            currentRecording= command;
        }

    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";

}
