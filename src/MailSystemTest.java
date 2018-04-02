import static org.junit.Assert.*;


import org.junit.Test;
public class MailSystemTest {
	private MailSystem mailSystem;
	@Test
	public void verifyNullMailSystem() {
		mailSystem = new MailSystem(0);
		assertEquals(null,mailSystem.findMailbox("0"));
	}
}
