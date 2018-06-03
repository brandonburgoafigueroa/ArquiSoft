package com.arqui.Core;

import com.arqui.Interfaces.*;
import com.arqui.Models.Mailbox;
import com.arqui.Repository.Database;
import com.arqui.Repository.Memory;
import com.arqui.ResponseRequest.PersistenceResponse;
import com.arqui.States.*;

public class Connection implements IConnection
{

   public Connection(IMailSystem s, IPresenters observers)
   {
       system = s;
       this.observers = observers;

   }

   public void resetConnection()
   {
       currentMailbox=null;
       setPersistenceType();
       status=new Connect(this);
   }
   public void setDisplay(IDisplay display)
   {
        this.display=display;
        observers.setDisplay(this.display);
        observers.setDisplay(this.display);
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            return hangup();
        else if (isQuitCommand(input))
            return false;
        else if (input.equals("00000"))
            return changePeristence();
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
    public IMailSystem getMailboxSystem() {
        return system;
    }
    public void setMailbox(Mailbox mailbox) {
        this.currentMailbox = mailbox;
    }
    public void setStatus(IState state)
    {
        this.status=state;
    }
    private IMailSystem system;
    private IPresenters observers;
    private Mailbox currentMailbox;
    private IState status;
    private IDisplay display;
    public void setOptions() {
        observers.setOptions();
    }
    public void setError(String errorName)
    {
        observers.setError(errorName);
    }
    public void setInformation(String text)
    {
        observers.setInformation(text);
    }
    public void setTextPlain(String text) {
        observers.setTextPlain(text);
    }
    public void show() {
        observers.showView();
    }
    public void setModelView(IModelView modelView){
     observers.setModelView(modelView);
    }
    public void setError(IResponse error)
    {
        observers.setError(error);
    }
    public void setPersistenceType(){
        IPersistence persistence=system.getPersistence();
        IResponse response=new PersistenceResponse(persistence);
        observers.setPersistenceType(response);
    }

   public boolean changePeristence()
   {
       setNewPersistence();
       setPersistenceType();
       return true;
   }

    private void setNewPersistence() {
        IPersistence actualPersistence=system.getPersistence();
        int MAILBOX_COUNT=system.getMailBoxCount();
        IPersistence newPersistence=null;
        if (isADatabase(actualPersistence))
        {
         newPersistence=new Memory();
        }
        if (isAMemory(actualPersistence))
        {
            newPersistence=new Database();
        }
        system = new MailSystem(MAILBOX_COUNT,newPersistence);
    }

    private boolean isAMemory(IPersistence actualPersistence) {
        return actualPersistence instanceof Memory;
    }

    private boolean isADatabase(IPersistence actualPersistence) {
        return actualPersistence instanceof Database;
    }
}











