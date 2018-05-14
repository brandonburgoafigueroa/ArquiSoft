package test;

import controller.MailSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MailSystemTestWithDB {
    @Before
    public void init ()
    {
        Mock DBContextMocked;
    }
    @Test
    public void verifyIfTheMailSystemIsEmpty() {
        MailSystem mailSystem = new MailSystem(0, new DBContextTests(0));
        Assert.assertEquals(null, mailSystem.findMailbox("0"));
    }

    @Test
    public void verifyIfTheMailSystemIsNotEmpty() {
        MailSystem mailSystem = new MailSystem(0, new DBContextTests(1));
        Assert.assertNotEquals(null, mailSystem.findMailbox("1"));
    }

    @Test
    public void setInitialMailSystemIfArrayIfDBProviderIsNotEmpty() {
        MailSystem mailSystem = new MailSystem(20, new DBContextTests(0));
        Assert.assertNotEquals(null, mailSystem.findMailbox("1"));
    }
}
