package com.arqui.Core;

import com.arqui.Entities.Mailbox;

public interface IConnection {
    boolean executeCommand(String command);
    Mailbox getCurrentMailbox();
    void resetConnection();
}
