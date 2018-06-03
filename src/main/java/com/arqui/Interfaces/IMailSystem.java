package com.arqui.Interfaces;

import com.arqui.Entities.Mailbox;

public interface IMailSystem {
    void saveChanges(Mailbox mailbox);
    Mailbox findMailbox(String ext);
}
