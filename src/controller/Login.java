package controller;

public class Login implements IState{
    private Mailbox currentMailbox;
    private String accumulatedKeys="";
    Connection connection;

    Login(Connection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
    }
    @Override
    public void start(String command) {
        if (itIsANumeralCharacter(command))
        {
            openMailboxMenu();
        }
        else {
            saveActualCommand(command);
        }
    }

    private void openMailboxMenu() {
        if (isTheCorrectPasscodeOfCurrentMailbox())
        {
            changeToMailboxMenuState();
            showMailboxMenuText();
        }
        else {
            showIncorrectPasscodeMessage();
            cleanAccumulatedKeys();
        }
    }

    private void saveActualCommand(String command) {
        accumulatedKeys += command;
    }

    private void cleanAccumulatedKeys() {
        accumulatedKeys = "";
    }

    private void showIncorrectPasscodeMessage() {
        connection.updateObservables(INCORRECT_PASSCODE_MESSAGE);
    }

    private void showMailboxMenuText() {
        connection.updateObservables(MAILBOX_MENU_TEXT);
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private boolean isTheCorrectPasscodeOfCurrentMailbox() {
        return currentMailbox.checkPasscode(accumulatedKeys);
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
}
