package controller;

public class Recording implements IState{
    String message;
    IState last;
    public Recording(IState state)
    {
        last=state;
    }
    public Recording()
    {}
    @Override
    public void start(String key, Connection connection) {
        if (key.length()>1)
        {
            message=key;
        }
        if (key.equalsIgnoreCase("H"))
        {
            connection.getCurrentMailbox().addMessage(new Message(message));
            connection.resetConnection();
        }
        if (isNumericalCommand(key))
        {
            connection.status=new Login();
            connection.executeCommand(key);
        }

    }
    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }


    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

}
