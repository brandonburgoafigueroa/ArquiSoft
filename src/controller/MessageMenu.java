package controller;

public class MessageMenu implements IState{
    private Mailbox currentMailbox;
    private Connection connection;

    MessageMenu(Connection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        showMessageMenuOptions();
    }
    @Override
    public void start(String command) {

        switch (command) {
            case "1":
                String MessageText = getTextOfLastMessage();
                showMessageText(MessageText);
                break;
            case "2":
                saveCurrentMessage();
                showMessageMenuOptions();
                break;
            case "3":
                removeCurrentMessage();
                showMessageMenuOptions();
                break;
            case "4":
                changeToMailboxMenuState();
                break;
        }
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private void removeCurrentMessage() {
        currentMailbox.removeCurrentMessage();
    }

    private void saveCurrentMessage() {
        currentMailbox.saveCurrentMessage();
    }

    private void showMessageText(String messageText) {
        connection.updateObservables(messageText);
    }

    private String getTextOfLastMessage() {
        String output="";
        Message m = currentMailbox.getCurrentMessage();
        if (m == null) {
            output += EMPTY_MAILBOX_MESSAGE + "\n";
        }
        else output += m.getText() + "\n";
        output += MESSAGE_MENU_TEXT;
        return output;
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }
    private void showMessageMenuOptions() {
        connection.updateObservables(MESSAGE_MENU_TEXT);
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
