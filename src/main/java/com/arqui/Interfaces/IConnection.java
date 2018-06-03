package com.arqui.Interfaces;

import com.arqui.Models.Mailbox;

public interface IConnection {
    boolean executeCommand(String command);
    Mailbox getCurrentMailbox();
    void resetConnection();
    void setMailbox(Mailbox mailbox);
    void setStatus(IState state);
    void setDisplay(IDisplay display);

    void setInformation(String text);
    void setTextPlain(String text);
    void setError(String errorName);
    void setOptions();
    void show();

    void saveChanges();
    IMailSystem getMailboxSystem();
    void setModelView(IModelView modelView);

    void setError(IResponse connectResponseError);
}
