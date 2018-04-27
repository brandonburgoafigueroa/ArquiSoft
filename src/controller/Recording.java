package controller;

public class Recording implements IState{
    String message;
    IState last;
    private Connection connection;

    public Recording(IState state, Connection connection)
    {
        last=state;
        this.connection=connection;
    }
    @Override
    public void start(String key) {
        if (key.length()>1)
        {
            message=key;
        }
        if (key.equalsIgnoreCase("H"))
        {
            hangup();
        }
        if (isNumericalCommand(key))
        {
            connection.setStatus(new Login(connection));
            connection.executeCommand(key);
        }

    }

    @Override
    public void hangup() {
        if (message!=null)
        {
            connection.getCurrentMailbox().addMessage(new Message(message));
        }
        connection.resetConnection();
    }

    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

}
