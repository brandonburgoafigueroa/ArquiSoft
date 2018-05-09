package test;

import controller.MailSystem;
import org.junit.Assert;
import org.junit.Test;

public class MailSystemTest {
	@Test
	public void verifyIfTheMailSystemIsEmpty() {
		MailSystem mailSystem = new MailSystem(0);
		Assert.assertEquals(null, mailSystem.findMailbox("0"));
	}
}
