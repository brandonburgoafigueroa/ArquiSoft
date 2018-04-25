package controller;

public class Login implements IState{
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";

    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    String accumulatedKeys="";
    public void start(String key, Mailbox currentMailbox, State state, Observers observers)
    {
        if (itIsANumeralCharacter(key))
        {
            if (currentMailbox.checkPasscode(accumulatedKeys))
            {
                state.setMailBoxMenu();
                observers.updateObservables(MAILBOX_MENU_TEXT);
            }
            else {
                observers.updateObservables(INCORRECT_PASSCODE_MESSAGE);
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
