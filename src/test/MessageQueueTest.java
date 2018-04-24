

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageQueueTest {
	private Message firstMessage;
	private Message secondMessage;
	@Before
	public void Init(){
		String FIRST_HELLO_MESSAGE = "Hello, How are you?";
		firstMessage =new Message(FIRST_HELLO_MESSAGE);
		String SECOND_HELLO_MESSAGE = ", How are you";
		secondMessage=new Message(SECOND_HELLO_MESSAGE);
	}

	@Test
	public void AddToTheTailAMessageAndEliminateTheFirstOfTheTailShouldReturnTheSameMessage() {

		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		assertEquals(messagequeue.remove(), firstMessage);
	}
	@Test
	public void IAddTheTailTwoMessagesAndEliminateTheFirstOfTheTailShouldReturnTheFirstMessageISent(){
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		messagequeue.add(secondMessage);
		assertEquals(messagequeue.remove(), firstMessage);
	}
	@Test
	public void AddTailAMessageAndEliminateTailSizeShouldReturn2(){
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		messagequeue.add(firstMessage);
		assertEquals(messagequeue.size(), 2);
	}
	@Test
	public void IAddATailAMessageAndIGetTheFirstOfTheTailIShouldReturnTheAddedMessage(){

		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		assertEquals(messagequeue.peek(), firstMessage);

	}
	@Test
	public void IDoNotAddAnythingToTheTailAndIGetTheFirstInTheTailIShouldReturnNull(){
		MessageQueue messagequeue=new MessageQueue();
		assertEquals(messagequeue.peek(), null);

	}
}