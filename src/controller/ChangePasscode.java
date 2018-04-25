package controller;

public class ChangePasscode {
    String accumulatedKeys="";
    public void changePasscode(String key,Mailbox currentMailbox, State state,Observers observers)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setPasscode(accumulatedKeys);
            state.setMessageMenu();
            observers.updateObservables(MAILBOX_MENU_TEXT);
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }
    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
}
