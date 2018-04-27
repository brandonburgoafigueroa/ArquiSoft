package controller;

public class ChangePasscode implements IState {

    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    private Connection connection;
    ChangePasscode(Connection connection)
    {
        this.connection=connection;
        connection.updateObservables(ENTER_NEW_PASSCODE_MESSAGE);
    }
    @Override
    public void start(String command) {
        this.currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(command))
        {
            currentMailbox.setPasscode(accumulatedKeys);
            connection.setStatus(new MailboxMenu(connection));
            connection.updateObservables(MAILBOX_MENU_TEXT);
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += command;
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
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";

}
