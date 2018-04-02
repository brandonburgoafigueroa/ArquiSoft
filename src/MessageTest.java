import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class MessageTest {

	@Test
	public void WriteMessageAndIRequestTextShouldReturnTheSameIWrote() {
		Message message=new Message("Hello, How are you?");
		Assert.assertEquals(message.getText(), "Hello, How are you?");
	}
	
	@Test
	public void WriteMessageAndIRequestTextShouldNotReturnEmpty() {
		Message message=new Message("Hello, How are you?");
		Assert.assertNotSame(message.getText(), "");
	}
}
