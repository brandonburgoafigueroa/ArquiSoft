package controller;

public class Login implements IState{
    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    Connection connection;
    @Override
    public void start(String key, Connection connection) {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(key))
        {
            if (currentMailbox.checkPasscode(accumulatedKeys))
            {
                connection.setStatus(new MailboxMenu());
                connection.updateObservables(MAILBOX_MENU_TEXT);
            }
            else {
                connection.updateObservables(INCORRECT_PASSCODE_MESSAGE);
            }
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
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
}
