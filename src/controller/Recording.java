package controller;

public class Recording implements IState{
    String message;
    IState last;
    private Connection connection;

    public Recording(IState state)
    {
        last=state;
    }
    public Recording()
    {}
    @Override
    public void start(String key, Connection connection) {
        this.connection=connection;
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
            connection.status=new Login();
            connection.executeCommand(key);
        }

    }

    @Override
    public void hangup() {
        connection.getCurrentMailbox().addMessage(new Message(message));
        connection.resetConnection();
    }

    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

}
