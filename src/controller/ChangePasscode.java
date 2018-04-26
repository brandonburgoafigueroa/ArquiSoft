package controller;

public class ChangePasscode implements IState{
    private Mailbox currentMailbox;
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    String accumulatedKeys="";
    public void start(String key, Connection connection)
    {
        currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setPasscode(accumulatedKeys);
            connection.setStatus(new MessageMenu());
            //state.setMessageMenu();
            connection.updateObservers(MAILBOX_MENU_TEXT);
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }


    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

}
