package test;

import controller.MailSystem;
import org.junit.Assert;
import org.junit.Test;
import persistence.DBContext;

public class MailSystemTest {
	@Test
	public void verifyIfTheMailSystemIsEmpty() {
		MailSystem mailSystem = new MailSystem(0, new DBContextTests());
		Assert.assertEquals(null, mailSystem.findMailbox("0"));
	}
}
