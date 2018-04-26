package controller;

public class MailBoxMenu implements IState{
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
    public void start(String key, Connection connection)
    {
        switch (key) {
            case "1":
                connection.setStatus(new MessageMenu());
                //state.setMessageMenu();
                connection.updateObservers(MESSAGE_MENU_TEXT);
                break;
            case "2":
                //state.setChangePassCode();
                connection.setStatus(new ChangePasscode());
                connection.updateObservers(ENTER_NEW_PASSCODE_MESSAGE);
                break;
            case "3":
                //state.setChangeGreeting();
                connection.setStatus(new ChangeGreeting());
                connection.updateObservers(ENTER_NEW_GREETING_MESSAGE);
                break;
        }
    }



}
