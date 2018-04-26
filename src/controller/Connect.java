package controller;

public class Connect implements IState{
    Mailbox currentMailbox;
    String accumulatedKeys="";
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
    public void start(String key, Connection connection)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox = connection.getMailboxSystem().findMailbox(accumulatedKeys);
            if (currentMailbox != null)
            {
                //state.setRecording();
                connection.setStatus(new Recording());
                connection.setRecording(true);
                connection.updateObservers(currentMailbox.getGreeting());
                connection.setCurrentMailbox(currentMailbox);
            }
            else {
                connection.updateObservers(INCORRECT_MAILBOX_MESSAGE);
            }
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }
    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }


}
