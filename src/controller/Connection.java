package controller;

import view.View;

import java.util.ArrayList;
import java.util.List;


public class Connection
{
    public IState status;
   public Connection(MailSystem s)
   {
       status=new Connect();
       system = s;
       observables=new ArrayList();
   }

   public void resetConnection()
   {
       currentMailbox=null;
       status=new Connect();
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
          status.start(key, this);
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
    private MailSystem system;
    private Mailbox currentMailbox;
    private List<View> observables;
    public static final int CONNECTED = 1;
    public static final int RECORDING = 2;
    public static final int MAILBOX_MENU = 3;
    public static final int MESSAGE_MENU = 4;
    public static final int CHANGE_PASSCODE = 5;
    public static final int CHANGE_GREETING = 6;
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }

    public MailSystem getMailboxSystem() {
        return system;
    }

    public void setMailbox(Mailbox mailbox) {
        this.currentMailbox = mailbox;
    }
}











