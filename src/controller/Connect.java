package controller;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private MailSystem system;
    private String accumulatedKeys="";

    @Override
    public void start(String key, Connection connection) {
        this.system=connection.getMailboxSystem();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox = system.findMailbox(accumulatedKeys);
            if (currentMailbox != null)
            {
                connection.status=new Recording();
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

    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
}
