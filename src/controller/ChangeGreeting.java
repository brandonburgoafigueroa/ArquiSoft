package controller;

public class ChangeGreeting {
    String currentRecording="";
    public void changeGreeting(String key, Mailbox currentMailbox, State state, Observers observers)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setGreeting(currentRecording);
            currentRecording = "";
            state.setMailBoxMenu();
            observers.updateObservables(MAILBOX_MENU_TEXT);
        }
    }
    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }

    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";

}
