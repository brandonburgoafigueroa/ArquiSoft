package controller;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private MailSystem system;
    private String accumulatedKeys="";
    private Connection connection;
    Connect(Connection connection) {
        this.connection=connection;
        connection.updateObservables(INITIAL_PROMPT);
    }

    @Override
    public void start(String key) {
        this.system=connection.getMailboxSystem();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox = system.findMailbox(accumulatedKeys);
            if (currentMailbox != null)
            {
                connection.setStatus(new Recording(this, connection));
                connection.setMailbox(currentMailbox);
               connection.updateObservables(currentMailbox.getGreeting());
            }
            else {
                connection.updateObservables(INCORRECT_MAILBOX_MESSAGE);
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
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
}
