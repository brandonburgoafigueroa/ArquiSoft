package com.arqui.Interfaces;

import com.arqui.Entities.Mailbox;

import java.util.ArrayList;

public interface IPersistence {
    void saveChanges(Mailbox mailbox, int idCurrentMailbox);
    void addMailbox(Mailbox mailbox);
    ArrayList<Mailbox> getAlMailbox();
}
