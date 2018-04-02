import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class MessageQuequeTest {

	@Test
	public void AddToTheTailAMessageAndEliminateTheFirstOfTheTailShouldReturnTheSameMessage() {
		Message message=new Message("Hello, How are you?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.remove(), message);
	}
	@Test
	public void IAddTheTailTwoMessagesAndEliminateTheFirstOfTheTailShouldReturnTheFirstMessageISent(){
		Message firstMessage=new Message("Hello");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		Message secondMessage=new Message(", How are you?");
		messagequeue.add(secondMessage);
		Assert.assertEquals(messagequeue.remove(), firstMessage);
	}
	@Test
	public void AddTailAMessageAndEliminateTailSizeShouldReturn2(){
		Message message=new Message("Hello, How are you?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		message=new Message("Hello, How are you?");
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.size(), 2);
	}
	@Test
	public void IAddATailAMessageAndIGetTheFirstOfTheTailIShouldReturnTheAddedMessage(){
		Message message=new Message("Hello, How are you?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.peek(), message);

	}
	@Test
	public void IDoNotAddAnythingToTheTailAndIGetTheFirstInTheTailIShouldReturnNull(){
		MessageQueue messagequeue=new MessageQueue();
		Assert.assertEquals(messagequeue.peek(), null);

	}
}
