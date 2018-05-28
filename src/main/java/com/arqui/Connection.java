package com.arqui;

import com.arqui.DisplayState.IDisplay;

import java.util.ArrayList;

public class Connection
{

   public Connection(MailSystem s, IObservers observers)
   {
       system = s;
       this.observers = observers;
       this.persistence = persistence;
   }

   public void resetConnection()
   {
       currentMailbox=null;
       status=new Connect(this);
   }
   public void setDisplay(IDisplay display)
   {
        this.display=display;
        observers.sendDisplay(this.display);
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            return hangup();
        else if (isQuitCommand(input))
            return false;
        else
            return dial(input);
    }
    public void saveChanges() {
       system.saveChanges(currentMailbox);
    }

    private Boolean hangup()
    {
        return status.hangup();
    }
   public boolean dial(String key)
   {
          return status.dial(key);
   }
    public void ShowText(String text)
    {
        observers.showText(text);
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
    private IPersistence persistence;
    private Mailbox currentMailbox;
    private IState status;
    private IDisplay display;
    public void showOptions() {
        observers.showOptions();
    }
    public void showError(String errorName)
    {
        observers.showError(errorName);
    }

    public void show(String text) {
        observers.show(text);
    }
}











