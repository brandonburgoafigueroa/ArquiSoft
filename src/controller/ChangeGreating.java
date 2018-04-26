package controller;

public class ChangeGreating implements IState {
    private Mailbox currentMailbox;
    private String currentRecording="";
    private Connection connection;

    @Override
    public void start(String key, Connection connection) {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setGreeting(currentRecording);
            currentRecording = "";
            connection.setStatus(new MailboxMenu());
            connection.updateObservables(MAILBOX_MENU_TEXT);
        }
        else
        {
            currentRecording=key;
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
}
