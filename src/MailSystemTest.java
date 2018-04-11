

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class MailSystemTest {
	@Test
	public void verifyNullMailSystem() {
		MailSystem mailSystem = new MailSystem(0);
		assertEquals(null, mailSystem.findMailbox("0"));
	}
}
