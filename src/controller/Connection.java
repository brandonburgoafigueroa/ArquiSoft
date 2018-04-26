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
    private boolean Recording;
    IState status;
    public void setRecording(boolean recording)
    {
        this.Recording=recording;
    }
    public void setCurrentMailbox(Mailbox currentMailbox){
        this.currentMailbox=currentMailbox;
    }
    public MailSystem getMailboxSystem()
    {
        return system;
    }
    public void setStatus(IState status)
    {
        this.status=status;
    }
    public Mailbox getCurrentMailbox()
    {
        return currentMailbox;
    }
    public void updateObservers(String text)
    {
        observers.updateObservables(text);
    }
   public Connection(MailSystem s)
   {
       system = s;
       observers=new Observers();
   }

   public void startConnection()
   {
       currentRecording = "";
       status=new Connect();
       observers.updateObservables(INITIAL_PROMPT);
   }

   public void addObserver(View observable)
   {
      observers.addObserver(observable);
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            status.start(input, this);
        else if (isQuitCommand(input))
            return false;
        else if (isNumericalCommand(input))
            status.start(input, this);
            //dial(input);
        else
            //status.start(input, this);
            record(input);
        return true;
    }

   public void dial(String key)
   {
       status.start(key, this);
   }


    public boolean isConnected() {
        return status instanceof Connect;
   }

   public boolean isRecording() {
       return Recording;
   }

   public boolean isChangePassCode() {
       return status instanceof ChangePasscode;
   }

   public boolean isChangeGreeting() {
       return status instanceof ChangeGreeting;
   }

   public boolean isMailBoxMenu() {
       return status instanceof MailBoxMenu;

   }

   public boolean isMessageMenu() {
       return status instanceof MessageMenu;

   }

   private void record(String voice)
   {
      if (isRecording() || isChangeGreeting()) {
          currentRecording += voice;
      }
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




