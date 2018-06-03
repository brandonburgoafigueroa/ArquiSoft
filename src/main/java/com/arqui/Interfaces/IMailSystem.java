package com.arqui.Interfaces;

import com.arqui.Models.Mailbox;

public interface IMailSystem {
    void saveChanges(Mailbox mailbox);
    Mailbox findMailbox(String ext);
    IPersistence getPersistence();
    int getMailBoxCount();

    String getCurrentIdMailbox();
}
