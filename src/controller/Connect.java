package controller;

public class Connect {
    Mailbox currentMailbox;
    String accumulatedKeys="";
    public Mailbox connect(String key, MailSystem system, State state, Observers observers)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox = system.findMailbox(accumulatedKeys);
            if (currentMailbox != null)
            {
                state.setRecording();
                observers.updateObservables(currentMailbox.getGreeting());
            }
            else {
                observers.updateObservables(INCORRECT_MAILBOX_MESSAGE);
            }
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
        return currentMailbox;
    }
    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
}
