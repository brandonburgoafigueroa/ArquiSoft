package controller;

public class ChangeGreating implements IState {
    private final Observers observers;
    private Mailbox currentMailbox;
    private String currentRecording="";
    private Connection connection;
    ChangeGreating(Connection connection)
    {
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        this.observers=connection.getObservers();
        showNewGreetingMessage(connection);

    }

    private void showNewGreetingMessage(Connection connection) {
        observers.updateObservables(ENTER_NEW_GREETING_MESSAGE);
    }

    @Override
    public void start(String command) {

        if (itIsANumeralCharacter(command))
        {
            setNewGreeting();
            changeToMailboxMenuState();
        }
        else
        {
            saveCommand(command);
        }

    }

    private void saveCommand(String command) {
        currentRecording= command;
    }

    private void setNewGreeting() {
        currentMailbox.setGreeting(currentRecording);
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    @Override
    public void hangup() {
        connection.resetConnection();
    }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";

}
