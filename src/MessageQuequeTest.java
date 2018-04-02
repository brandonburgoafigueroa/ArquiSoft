import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class MessageQuequeTest {

	@Test
	public void A�adoALaColaUnMensajeYEliminoElPrimeroDeLaColaDeberiaDevolverElMismoMensaje() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.remove(), message);
	}
	@Test
	public void A�adoALaColaDosMensajesYEliminoElPrimeroDeLaColaDeberiaDevolverElPrimerMensajeQueEnvio() {
		Message firstMessage=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		Message secondMessage=new Message("Como estas?");
		messagequeue.add(secondMessage);
		Assert.assertEquals(messagequeue.remove(), firstMessage);
	}
	@Test
	public void A�adoALaColaUnMensajeYEliminoTama�oDeLaColaDeberiaDevolver2() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		message=new Message("Hola que tal?");
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.size(), 2);
	}
	@Test
	public void A�adoALaColaUnMensajeYObtengoElPrimeroDeLaColaDeberiaDevolverElMensajeA�adido() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.peek(), message);

	}
	@Test
	public void NoA�adoNadaALaColaYObtengoElPrimeroEnLaColaDeberiaDevolverNull() {
		MessageQueue messagequeue=new MessageQueue();
		Assert.assertEquals(messagequeue.peek(), null);

	}
}
