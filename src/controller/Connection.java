package controller;

public class Connection
{

   public Connection(MailSystem s, IObservers observers)
   {
       system = s;
       this.observers = observers;
   }

   public void resetConnection()
   {
       currentMailbox=null;
       status=new Connect(this);

   }

    public IObservers getObservers() {
        return observers;
    }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            hangup();
        else if (isQuitCommand(input))
            return false;
        else
            dial(input);
        return true;
    }

    private void hangup()
    {
        status.hangup();
    }
   public void dial(String key)
   {
          status.start(key);
   }

   public boolean isConnected() {
	   return status instanceof Connect;
   }

   public boolean isRecording() {
	   return status instanceof Recording;
   }

   public boolean isChangePassCode() {
	   return status instanceof ChangePasscode;
   }

   public boolean isChangeGreeting() {
	   return status instanceof ChangeGreating;
   }

   public boolean isMailBoxMenu() {
	   return status instanceof MailboxMenu;
   }

   public boolean isMessageMenu() {
	   return status instanceof MessageMenu;
   }


    private boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase("Q");
    }

    private boolean isInputHangUpCommand(String input) {
        return input.equalsIgnoreCase("H");
    }
    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }
    public MailSystem getMailboxSystem() {
        return system;
    }
    public void setMailbox(Mailbox mailbox) {
        this.currentMailbox = mailbox;
    }
    public void setStatus(IState state)
    {
        this.status=state;
    }
    private MailSystem system;
    private IObservers observers;
    private Mailbox currentMailbox;
    private IState status;

}











