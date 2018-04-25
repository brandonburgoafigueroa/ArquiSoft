package controller;

public class MessageMenu implements IState{
    public MessageMenu(){}
    public void start(String key,Mailbox currentMailbox, State state, Observers observers)
    {
        switch (key) {
            case "1":
                String output = "";
                Message m = currentMailbox.getCurrentMessage();
                if (m == null) {
                    output += EMPTY_MAILBOX_MESSAGE + "\n";
                }
                else output += m.getText() + "\n";
                output += MESSAGE_MENU_TEXT;
                observers.updateObservables(output);
                break;
            case "2":
                currentMailbox.saveCurrentMessage();
                observers.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "3":
                currentMailbox.removeCurrentMessage();
                observers.updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "4":
                state.setMailBoxMenu();
                observers.updateObservables(MAILBOX_MENU_TEXT);
                break;
        }

    }

    private String EMPTY_MAILBOX_MESSAGE = "No messages.";
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
}
