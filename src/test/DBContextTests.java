package test;

import controller.Mailbox;
import persistence.IPersistence;

import java.util.ArrayList;

public class DBContextTests implements IPersistence {
    private final ArrayList<Mailbox> mailboxes;

    public DBContextTests(int quantity)
    {
        mailboxes=new ArrayList<Mailbox>();
        for(int i=0; i<quantity;i++)
        {
            mailboxes.add(new Mailbox("",""));
        }
    }
    @Override
    public void saveChanges(Mailbox mailbox, int idCurrentMailbox) {

    }

    @Override
    public void addMailbox(Mailbox mailbox) {

    }

    @Override
    public ArrayList<Mailbox> getAlMailbox() {
        return mailboxes;
    }
}
