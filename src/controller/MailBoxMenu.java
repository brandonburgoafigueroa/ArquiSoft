package controller;

import view.View;

import java.util.List;

public class MailBoxMenu {
    private List<View> observables;
    private void updateObservables(String message)
    {

        for (View observer:observables) {
            observer.update(message);
        }
    }
    public void mailboxMenu(String key, State state, List<View> observables)
    {
        this.observables=observables;
        switch (key) {
            case "1":
                state.setMessageMenu();
                updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "2":
                state.setChangePassCode();
                updateObservables(ENTER_NEW_PASSCODE_MESSAGE);
                break;
            case "3":
                state.setChangeGreeting();
                updateObservables(ENTER_NEW_GREETING_MESSAGE);
                break;
        }
    }

    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";

}
