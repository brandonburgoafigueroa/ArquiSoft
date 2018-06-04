package com.arqui.Core;


import com.arqui.Interfaces.IPersistence;
import com.arqui.Models.Mailbox;
import com.arqui.Interfaces.IMailSystem;
import com.arqui.Repository.Database;
import com.arqui.Repository.Memory;

import java.util.ArrayList;

public class MailSystem implements IMailSystem
{
   private ArrayList<Mailbox> mailboxes;
   private int idCurrentMailbox;
   private IPersistence persistence;

   public MailSystem(int mailboxCount, IPersistence persistence)
   {
      this.persistence = persistence;
      mailboxes = new ArrayList();
      setupMailSystem(mailboxCount);
   }

   private void setupMailSystem(int mailboxCount) {
      ArrayList<Mailbox> mailboxOfDB= persistence.getAlMailbox();
      int Quantity=mailboxOfDB.size();
      if (Quantity==0)
      {
         setInitial(mailboxCount);
      }
      else
      {
         loadMailboxesOfPersistence(mailboxOfDB);
      }
   }

   private void loadMailboxesOfPersistence(ArrayList<Mailbox> mailboxOfDB) {
      for (Mailbox mailbox:mailboxOfDB) {
            mailboxes.add(mailbox);
      }
   }

   private void setInitial(int mailboxCount) {
      for (int i = 0; i < mailboxCount; i++)
      {
         String passcode = "" + (i + 1);
         String greeting = "You have reached mailbox " + (i + 1)
               + ". \nPlease leave a message now.";
         mailboxes.add(new Mailbox(passcode, greeting));
      }
      for (Mailbox mailbox:mailboxes) {
         persistence.addMailbox(mailbox);
      }
   }


   public Mailbox findMailbox(String ext)
   {
      int i = Integer.parseInt(ext);
      if (1 <= i && i <= mailboxes.size()) {
         idCurrentMailbox = i ;
         return mailboxes.get(idCurrentMailbox-1);
      }
      else return null;
   }

   @Override
   public IPersistence getPersistence() {
      return persistence;
   }

   @Override
   public int getMailBoxCount() {
      return mailboxes.size();
   }



   public void saveChanges(Mailbox mailbox){
      persistence.saveChanges(mailbox, idCurrentMailbox);
   }

}
