package controller;

public class Recording implements IState{
    String message;
    private Connection connection;

    public Recording(Connection connection)
    {
        this.connection=connection;
    }
    @Override
    public void start(String command) {
        if (isAMessage(command))
        {
            message= command;
        }
        if (isInputHangUpCommand(command))
        {
            hangup();
        }
        if (isNumericalCommand(command))
        {
            changeStateToLogin();
            connection.executeCommand(command);
        }
    }

    private boolean isAMessage(String key) {
        return key.length()>1;
    }

    private boolean isInputHangUpCommand(String input) {
        return input.equalsIgnoreCase("H");
    }
    private void changeStateToLogin() {
        connection.setStatus(new Login(connection));
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
