package controller;

import java.util.ArrayList;

public class MailSystem
{
   private ArrayList<Mailbox> mailboxes;

   public MailSystem(int mailboxCount)
   {
      mailboxes = new ArrayList();
      for (int i = 0; i < mailboxCount; i++)
      {
         String passcode = "" + (i + 1);
         String greeting = "You have reached mailbox " + (i + 1)
               + ". \nPlease leave a message now.";
         mailboxes.add(new Mailbox(passcode, greeting));
      }
   }
   public void setMailboxToPos(String pos,Mailbox mailbox)
   {
      int i = Integer.parseInt(pos);
      if(mailbox!=null) {
         mailboxes.set(i-1, mailbox);
      }
   }

   public boolean hasNewMessages(String pos){
      int i = Integer.parseInt(pos);
      return (mailboxes.get(i-1).hasNewMessages());
   }
   public Mailbox findMailbox(String ext)
   {
      int i = Integer.parseInt(ext);
      if (1 <= i && i <= mailboxes.size())
         return  mailboxes.get(i - 1);
      else return null;
   }


}
