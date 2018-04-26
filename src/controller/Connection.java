package controller;

import view.View;

import java.util.ArrayList;
import java.util.List;


public class Connection
{

   public Connection(MailSystem s)
   {
       system = s;
       observables=new ArrayList();
   }

   public void resetConnection()
   {
       currentMailbox=null;
       status=new Connect(this);
       updateObservables(INITIAL_PROMPT);
   }

   public void addObservable(View observable)
   {
      observables.add(observable);

   }

   public void updateObservables(String message)
   {
      for (View observer:observables) {
       observer.update(message);
      }
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
    private Mailbox currentMailbox;
    private List<View> observables;
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
    private IState status;

}











