package controller;

public class ChangeGreeting implements IState{
    Mailbox currentMailbox;
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    String currentRecording="";
    public void start(String key, Connection connection)
    {
        currentMailbox=connection.getCurrentMailbox();
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setGreeting(currentRecording);
            currentRecording = "";
            connection.setStatus(new MailBoxMenu());
            //observers.updateObservables(MAILBOX_MENU_TEXT);
            connection.updateObservers(MAILBOX_MENU_TEXT);
        }
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }



}
