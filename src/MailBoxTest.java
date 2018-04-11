

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class MailBoxTest {

	@Before
	public void init() {

		mailBox = new Mailbox(PASS_CODE,WELCOME_MESSAGE);
		
	}
	@Test
	public void verifyCorrectPasscode() {
		
		assertEquals(true,mailBox.checkPasscode(PASS_CODE));
	}
	@Test
	public void verifySetCorrectPasscode() {
		mailBox.setPasscode(PASS_CODE);
		assertEquals(true,mailBox.checkPasscode(PASS_CODE));
	}
	@Test
	public void verifyCorrectGreeting() {
		assertEquals(WELCOME_MESSAGE,mailBox.getGreeting());
	}
	@Test
	public void verifySetGreeting() {
		String HI_MESSAGE_SHORT = "hello!";
		mailBox.setGreeting(HI_MESSAGE_SHORT);
		assertEquals(HI_MESSAGE_SHORT,mailBox.getGreeting());
	}
	@Test
	public void isCorrectTheCurrentNewMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void isCorrectTheCurrentKeptMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void isEmptyQueueNewMessages() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.getCurrentMessage());
	}
	
	@Test
	public void isCorrectTheCurrentMessageWithMultipleNewMessages() {

		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		Message message2 = new Message(FIRST_MESSAGE);
		mailBox.addMessage(message2);
		Message message3 = new Message(SECOND_MESSAGE);
		mailBox.addMessage(message3);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void wasCorrectNewMessageRemovedSuccesfull() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		Message message2 = new Message(FIRST_MESSAGE);
		mailBox.addMessage(message2);
		Message message3 = new Message(SECOND_MESSAGE);
		mailBox.addMessage(message3);
		mailBox.removeCurrentMessage();
		assertEquals(message2,mailBox.removeCurrentMessage());
	}
	@Test
	public void wasRemovedTheOnlyNewMessageSuccesfull() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.removeCurrentMessage());
	}
	@Test
	public void isEmptyMessageQueueWithMultipleNewMessages() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		Message message2 = new Message(FIRST_MESSAGE);
		mailBox.addMessage(message2);
		Message message3 = new Message(SECOND_MESSAGE);
		mailBox.addMessage(message3);
		mailBox.removeCurrentMessage();
		mailBox.removeCurrentMessage();
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.getCurrentMessage());
	}

	private Mailbox mailBox;
	private String HI_MESSAGE_LONG="hello, how are you?";
	private String PASS_CODE ="passsword";
	private String WELCOME_MESSAGE="welcome!";
	private String FIRST_MESSAGE="are you okay?";
	private String SECOND_MESSAGE="can you answer me?";
}
