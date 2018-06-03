package com.arqui.Repository;

import com.arqui.Interfaces.IPersistence;
import com.arqui.Models.Mailbox;

import java.util.ArrayList;

public class OnMemory implements IPersistence {
    private ArrayList<Mailbox> mailboxes;
    public OnMemory()
    {
        mailboxes=new ArrayList<>();
    }
    @Override
    public void saveChanges(Mailbox mailbox, int idCurrentMailbox) {
        mailboxes.set(idCurrentMailbox, mailbox);
    }

    @Override
    public void addMailbox(Mailbox mailbox) {
        mailboxes.add(mailbox);
    }

    @Override
    public ArrayList<Mailbox> getAlMailbox() {
        return mailboxes;
    }
}
