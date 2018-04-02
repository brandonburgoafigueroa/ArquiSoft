import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
public class MailBoxTest {
	private Mailbox mailBox;

	@Before
	public void init() {
		mailBox = new Mailbox("password","welcome!");
		
	}
	@Test
	public void verifyCorrectPasscode() {
		
		assertEquals(true,mailBox.checkPasscode("password"));
	}
	@Test
	public void verifySetCorrectPasscode() {
		mailBox.setPasscode("contrasena");
		assertEquals(true,mailBox.checkPasscode("contrasena"));
	}
	@Test
	public void verifyCorrectGreeting() {
		;
		assertEquals("welcome!",mailBox.getGreeting());
	}
	@Test
	public void verifySetGreeting() {
		mailBox.setGreeting("hello!");
		assertEquals("hello!",mailBox.getGreeting());
	}
	@Test
	public void isCorrectTheCurrentNewMessage() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void isCorrectTheCurrentKeptMessage() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void isEmptyQueueNewMessages() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.getCurrentMessage());
	}
	
	@Test
	public void isCorrectTheCurrentMessageWithMultipleNewMessages() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		Message message2 = new Message("are you okay?");
		mailBox.addMessage(message2);
		Message message3 = new Message("can you answer me?");
		mailBox.addMessage(message3);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void wasCorrectNewMessageRemovedSuccesfull() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		Message message2 = new Message("are you okay?");
		mailBox.addMessage(message2);
		Message message3 = new Message("can you answer me?");
		mailBox.addMessage(message3);
		mailBox.removeCurrentMessage();
		assertEquals(message2,mailBox.removeCurrentMessage());
	}
	@Test
	public void wasRemovedTheOnlyNewMessageSuccesfull() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.removeCurrentMessage());
	}
	@Test
	public void isEmptyMessageQueueWithMultipleNewMessages() {
		Message message = new Message("hello, how are you?");
		mailBox.addMessage(message);
		Message message2 = new Message("are you okay?");
		mailBox.addMessage(message2);
		Message message3 = new Message("can you answer me?");
		mailBox.addMessage(message3);
		mailBox.removeCurrentMessage();
		mailBox.removeCurrentMessage();
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.getCurrentMessage());
	}
}
