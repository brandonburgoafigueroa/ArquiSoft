import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class MessageQuequeTest {

	@Test
	public void AñadoALaColaUnMensajeYEliminoElPrimeroDeLaColaDeberiaDevolverElMismoMensaje() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.remove(), message);
	}
	@Test
	public void AñadoALaColaDosMensajesYEliminoElPrimeroDeLaColaDeberiaDevolverElPrimerMensajeQueEnvio() {
		Message firstMessage=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(firstMessage);
		Message secondMessage=new Message("Como estas?");
		messagequeue.add(secondMessage);
		Assert.assertEquals(messagequeue.remove(), firstMessage);
	}
	@Test
	public void AñadoALaColaUnMensajeYEliminoTamañoDeLaColaDeberiaDevolver2() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		message=new Message("Hola que tal?");
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.size(), 2);
	}
	@Test
	public void AñadoALaColaUnMensajeYObtengoElPrimeroDeLaColaDeberiaDevolverElMensajeAñadido() {
		Message message=new Message("Hola que tal?");
		MessageQueue messagequeue=new MessageQueue();
		messagequeue.add(message);
		Assert.assertEquals(messagequeue.peek(), message);

	}
	@Test
	public void NoAñadoNadaALaColaYObtengoElPrimeroEnLaColaDeberiaDevolverNull() {
		MessageQueue messagequeue=new MessageQueue();
		Assert.assertEquals(messagequeue.peek(), null);

	}
}
