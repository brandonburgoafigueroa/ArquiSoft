package controller;

public class Login implements IState{
    Mailbox currentMailbox;
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";
    public Login(String key){
        accumulatedKeys=key;
    }
    private static String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    String accumulatedKeys;
    public void start(String key, Connection connection)
    {

        this.currentMailbox=connection.getCurrentMailbox();
        /*connection.updateObservers(currentMailbox.getGreeting());
        MAILBOX_MENU_TEXT=currentMailbox.getGreeting()+"\n"+MAILBOX_MENU_TEXT;*/
        if (itIsANumeralCharacter(key))
        {
            if (currentMailbox.checkPasscode(accumulatedKeys))
            {
                connection.setStatus(new MailBoxMenu());
                //state.setMailBoxMenu();
                connection.updateObservers(MAILBOX_MENU_TEXT);
            }
            else {
                connection.updateObservers(INCORRECT_PASSCODE_MESSAGE);
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
