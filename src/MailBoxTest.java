

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class MailBoxTest {

	@Before
	public void init() {

		mailBox = new Mailbox(PASS_CODE,WELCOME_MESSAGE);
		
	}
	@Test
	public void verifyIfMailBoxHasTheCorrectPasscodeIntroduced() {
		
		assertEquals(true,mailBox.checkPasscode(PASS_CODE));
	}
	@Test
	public void verifyIfMailBoxHasTheCorrectPasswordAfterSetPasscode() {
		mailBox.setPasscode(PASS_CODE);
		assertEquals(true,mailBox.checkPasscode(PASS_CODE));
	}
	@Test
	public void verifyIfTheMailboxHasCorrectGreetingIntroduced() {
		assertEquals(WELCOME_MESSAGE,mailBox.getGreeting());
	}
	@Test
	public void verifyIfMailboxHasTheCorrectGreetingAfterSetGreeting() {
		String HI_MESSAGE_SHORT = "hello!";
		mailBox.setGreeting(HI_MESSAGE_SHORT);
		assertEquals(HI_MESSAGE_SHORT,mailBox.getGreeting());
	}
	@Test
	public void verifyIfTheMailBoxHasTheCorrectNewMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void verifyIfTheMailBoxHasTheCurrentKeptMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void verifyIfTheMailBoxIsEmptyAfterAddRemoveMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.getCurrentMessage());
	}
	
	@Test
	public void verifyIfTheMailBoxHasTheCurrentMessageAfterAddMultipleMessages() {

		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		Message message2 = new Message(FIRST_MESSAGE);
		mailBox.addMessage(message2);
		Message message3 = new Message(SECOND_MESSAGE);
		mailBox.addMessage(message3);
		assertEquals(message,mailBox.getCurrentMessage());
	}
	@Test
	public void verifyIfTheMailBoxHasTheCurrentMessageAfterAddMultipleMessagesAndRemoveAMessage() {
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
	public void verifyIfTheCurrentMessageIsEmptyAfterAddRemoveAMessage() {
		Message message = new Message(HI_MESSAGE_LONG);
		mailBox.addMessage(message);
		mailBox.removeCurrentMessage();
		assertEquals(null,mailBox.removeCurrentMessage());
	}
	@Test
	public void verifyIfTheQueueMessagesIsEmptyMessageAfterAddRemoveMultipleNewMessages() {
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
