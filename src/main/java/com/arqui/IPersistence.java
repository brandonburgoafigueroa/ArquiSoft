package com.arqui;

import com.arqui.Mailbox;

import java.util.ArrayList;

public interface IPersistence {
    void saveChanges(Mailbox mailbox, int idCurrentMailbox);
    void addMailbox(Mailbox mailbox);
    ArrayList<Mailbox> getAlMailbox();
}
