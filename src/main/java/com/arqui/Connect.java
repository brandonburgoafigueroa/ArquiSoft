package com.arqui;

public class Connect implements IState {
    private Mailbox currentMailbox;
    private MailSystem system;
    private String accumulatedKeys="";
    private Connection connection;
    Connect(Connection connection) {
        this.connection=connection;
        this.system=connection.getMailboxSystem();
        showInitialPromptMessage();
    }
    private void showInitialPromptMessage() {
        connection.updateObservers(INITIAL_PROMPT);
    }

    public boolean dial(String command) {

        if (itIsANumeralCharacter(command))
            return openMailbox();
        else
            saveActualCommand(command);
        return true;
    }

    private boolean openMailbox() {
        setupMailbox();
        if (isAValidMailbox())
        {
            setCurrentMailboxToConnection();
            showGreetingMessage();
            changeToRecordingState();
            return true;
        }
        else {
            showIncorrectMailboxMessage();
            cleanAccumulatedKeys();
            return false;
        }
    }

    private void saveActualCommand(String command) {
        accumulatedKeys += command;
    }

    private void cleanAccumulatedKeys() {
        accumulatedKeys = "";
    }

    private void setupMailbox() {
        currentMailbox = system.findMailbox(accumulatedKeys);
    }

    private void showIncorrectMailboxMessage() {
        connection.updateObservers(INCORRECT_MAILBOX_MESSAGE);
    }

    private void showGreetingMessage() {
        connection.updateObservers(currentMailbox.getGreeting());
    }

    private void setCurrentMailboxToConnection() {
        connection.setMailbox(currentMailbox);
    }

    private void changeToRecordingState() {
        connection.setStatus(new Recording(connection));
    }

    private boolean isAValidMailbox() {
        return currentMailbox != null;
    }

    public boolean hangup() {
        connection.resetConnection();
        return true;
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
}
