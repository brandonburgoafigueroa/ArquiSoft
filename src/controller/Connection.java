package controller;

import view.View;


public class Connection
{

/*seccion de variables agregadas*/
State state =new State();
    IState messageMenu=new MessageMenu();
    IState mailBoxMenu=new MailBoxMenu();
    IState changeGreeting=new ChangeGreeting();
    IState changePasscode=new ChangePasscode();
    IState login=new Login();
    Connect connect=new Connect();
    IState status;
/* end */
   public Connection(MailSystem s)
   {
       system = s;
       observers=new Observers();
   }

   public void startConnection()
   {
       currentRecording = "";
       state.setConnected();
       observers.updateObservables(INITIAL_PROMPT);
   }

   public void addObserver(View observable)
   {
      observers.addObserver(observable);
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            hangup();
        else if (isQuitCommand(input))
            return false;
        else if (isNumericalCommand(input))
            dial(input);
        else
            record(input);
        return true;
    }

    private void hangup()
    {
        if (state.IsRecording()) {
            currentMailbox.addMessage(new Message(currentRecording));
        }
        startConnection();
    }
   public void dial(String key)
   {
      if (isConnected()) {
          currentMailbox = connect.connect(key, system, state, observers);

      }
      else if (isMessageMenu())
        messageMenu.start(key, currentMailbox,state, observers);
      else
      {
          state.getStatus().start(key, currentMailbox, state, observers);
      }

   }


    public boolean isConnected() {
        return state.IsConnected();
   }

   public boolean isRecording() {
       return state.IsRecording();
   }

   public boolean isChangePassCode() {
       return state.IsChangePassCode();
   }

   public boolean isChangeGreeting() {
       return state.IsChangeGreeting();
   }

   public boolean isMailBoxMenu() {
       return state.IsMailBoxMenu();

   }

   public boolean isMessageMenu() {
       return state.IsMessageMenu();

   }

   private void record(String voice)
   {
      if (isRecording() || isChangeGreeting())
         currentRecording += voice;
   }

    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

    private boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase("Q");
    }

    private boolean isInputHangUpCommand(String input) {
        return input.equalsIgnoreCase("H");
    }
    private MailSystem system;
    private Mailbox currentMailbox;
    private String currentRecording;
    private Observers observers;
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";


}




