package controller;

public class MailBoxMenu{
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
    public void start(String key, State state, Observers observers)
    {
        switch (key) {
            case "1":
                state.setMessageMenu();
                observers.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "2":
                state.setChangePassCode();
                observers.updateObservables(ENTER_NEW_PASSCODE_MESSAGE);
                break;
            case "3":
                state.setChangeGreeting();
                observers.updateObservables(ENTER_NEW_GREETING_MESSAGE);
                break;
        }
    }



}
