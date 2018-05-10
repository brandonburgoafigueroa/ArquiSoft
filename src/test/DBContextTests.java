package test;

import controller.Mailbox;
import persistence.IPersistence;

import java.util.ArrayList;

public class DBContextTests implements IPersistence {
    @Override
    public void saveChanges(Mailbox mailbox, int idCurrentMailbox) {

    }

    @Override
    public void addMailbox(Mailbox mailbox) {

    }

    @Override
    public ArrayList<Mailbox> getAlMailbox() {
        return null;
    }
}
