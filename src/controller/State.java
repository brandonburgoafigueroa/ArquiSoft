package controller;

public class State {
    private int state;
    IState status;
    private static final int CONNECTED = 1;
    private static final int RECORDING = 2;
    private static final int MAILBOX_MENU = 3;
    private static final int MESSAGE_MENU = 4;
    private static final int CHANGE_PASSCODE = 5;
    private static final int CHANGE_GREETING = 6;
    public State()
    {

    }
    public void setMailBoxMenu()
    {
        state=MAILBOX_MENU;
        status=new MailBoxMenu();
    }
    public void setMessageMenu()
    {
        state=MESSAGE_MENU;
        status=new MessageMenu();

    }
    public void setChangePassCode()
    {
        state=CHANGE_PASSCODE;
        status=new ChangePasscode();
    }
    public void setChangeGreeting()
    {
        state=CHANGE_GREETING;
        status=new ChangeGreeting();

    }
    public void setRecording() {
        state=RECORDING;
        status=new Login();
    }
    public boolean IsConnected(){
        return state==CONNECTED;
    }
    public void setConnected() {
        state=CONNECTED;
    }

    public boolean IsRecording() {
        return state==RECORDING;
    }
    public IState getStatus(){
        return status;
    }
    public boolean IsChangePassCode() {
        return state==CHANGE_PASSCODE;
    }

    public boolean IsChangeGreeting() {
        return state==CHANGE_GREETING;
    }

    public boolean IsMailBoxMenu() {
        return state==MAILBOX_MENU;
    }

    public boolean IsMessageMenu() {
        return state==MESSAGE_MENU;
    }



}
