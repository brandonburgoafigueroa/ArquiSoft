

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class MailSystemTest {
	private MailSystem mailSystem;
	@Test
	public void verifyNullMailSystem() {
		mailSystem = new MailSystem(0);
		assertEquals(null,mailSystem.findMailbox("0"));
	}
}
