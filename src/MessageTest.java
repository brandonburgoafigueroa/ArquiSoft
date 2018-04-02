import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class MessageTest {

	@Test
	public void EscribirMensajeYPidoTextoDeberiaDevolverElMismoQueEscribi() {
		Message message=new Message("Hola que tal, como estas?");
		Assert.assertEquals(message.getText(), "Hola que tal, como estas?");
	}
	
	@Test
	public void EscribirMensajeYPidoTextoNoDeberiaDevolverVacio() {
		Message message=new Message("Hola que tal, como estas?");
		Assert.assertNotSame(message.getText(), "");
	}
}
