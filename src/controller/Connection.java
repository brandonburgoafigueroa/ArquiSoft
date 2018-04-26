package controller;

import view.View;


public class Connection
{

    private MailSystem system;
    private Mailbox currentMailbox;
    private String currentRecording;
    private Observers observers;
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";

    State state =new State();
    MailBoxMenu mailBoxMenu=new MailBoxMenu();
    Connect connect=new Connect();
    IState status;

    public void setStatus(IState status)
    {
        this.status=status;
    }
    public Mailbox getCurrentMailbox()
    {
        return currentMailbox;
    }
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
        if (state.isRecording()) {
            currentMailbox.addMessage(new Message(currentRecording));
        }
        startConnection();
    }
   public void dial(String key)
   {
      if (isConnected()) {
          currentMailbox = connect.connect(key, system, state, observers);

      }
      else if(isMailBoxMenu()) {
          mailBoxMenu.start(key,state, observers);
   }
      else{
          state.getStatus().start(key, currentMailbox, state, observers);
      }
   }


    public boolean isConnected() {
        return state.isConnected();
   }

   public boolean isRecording() {
       return state.isRecording();
   }

   public boolean isChangePassCode() {
       return state.isChangePassCode();
   }

   public boolean isChangeGreeting() {
       return state.isChangeGreeting();
   }

   public boolean isMailBoxMenu() {
       return state.isMailBoxMenu();

   }

   public boolean isMessageMenu() {
       return state.isMessageMenu();

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


}




