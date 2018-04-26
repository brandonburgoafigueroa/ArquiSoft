package controller;

public class Recording implements IState {
    String currentRecording="";
    @Override
    public void start(String key, Connection connection) {
            if (key=="h")
            {
                connection.getCurrentMailbox().addMessage(new Message(currentRecording));
                connection.setStatus(new Connect());
            }
            else if (isNumericalCommand(key))
            {
                connection.setStatus(new Login(key));
            }
            else
                record(key, connection);

    }
    private void record(String voice, Connection connection)
    {
        if (connection.isRecording() || connection.isChangeGreeting())
            currentRecording += voice;
    }
    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }
}
