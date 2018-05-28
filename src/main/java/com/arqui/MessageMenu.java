package com.arqui;

public class MessageMenu implements IState{
    private Mailbox currentMailbox;
    private Connection connection;

    MessageMenu(Connection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        showMessageMenuOptions();
    }
    public boolean dial(String command) {

        if ("1".equals(command)) {
            String MessageText = getTextOfLastMessage();
            showMessageText(MessageText);

        } else if ("2".equals(command)) {
            saveCurrentMessage();
            showMessageMenuOptions();

        } else if ("3".equals(command)) {
            removeCurrentMessage();
            showMessageMenuOptions();

        } else if ("4".equals(command)) {
            changeToMailboxMenuState();

        }
        return true;
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private void removeCurrentMessage() {
        currentMailbox.removeCurrentMessage();
        connection.saveChanges();
    }

    private void saveCurrentMessage() {
        currentMailbox.saveCurrentMessage();
        connection.saveChanges();
    }

    private void showMessageText(String messageText) {
        connection.ShowText(messageText);
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
    public boolean hangup() {
        connection.resetConnection();
        return true;
    }
    private void showMessageMenuOptions() {
        connection.ShowText(MESSAGE_MENU_TEXT);
    }

    private String EMPTY_MAILBOX_MESSAGE = "No messages.";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
}
