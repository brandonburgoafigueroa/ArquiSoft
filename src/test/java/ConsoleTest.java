import com.arqui.Controller.Controller;
import com.arqui.Core.Connection;
import com.arqui.Core.MailSystem;
import com.arqui.Interfaces.IController;
import com.arqui.Models.Mailbox;
import com.arqui.Interfaces.IPersistence;
import com.arqui.Presenters.PresentersManagerManager;
import com.arqui.Views.Console;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleTest {
	private IPersistence persistenceMocked;
	@Before
	public void init()
	{
		persistenceMocked=mock(IPersistence.class);
		ArrayList<Mailbox> mails = SetupArrayListMailboxes();
		when(persistenceMocked.getAlMailbox()).thenReturn(mails);
	}
	@Test
	public void EnterAChainInPhoneAndPrintTheChainShouldReturnMeSame() {
		Scanner scanner=new Scanner(System.in);
		PresentersManagerManager presentersManager =new PresentersManagerManager();
		Connection connection=new Connection(new MailSystem(20, persistenceMocked), presentersManager);
		IController controller=new Controller(connection);
		Console telephone=new Console(scanner, controller);
		String ENTER_MAILBOX_MESSAGE = "Enter mailbox number followed by #";
		String output= ENTER_MAILBOX_MESSAGE;
		Assert.assertEquals(telephone.speakT(output), ENTER_MAILBOX_MESSAGE);
	}
	@Test
	public void IncomeLetterHAndICheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("H");
		MailSystem mailSystem=new MailSystem(20,persistenceMocked);
		Connection connection=new Connection(mailSystem, new PresentersManagerManager());
		IController controller=new Controller(connection);
		Console telephone=new Console(scanner, controller);
		connection.resetConnection();
		Assert.assertEquals(connection.isConnected(), true);
	}

	@Test
	public void IncomeLetterQAndICheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Q");
		MailSystem mailSystem=new MailSystem(20,persistenceMocked);
		Connection connection=new Connection(mailSystem, new PresentersManagerManager());
		IController controller=new Controller(connection);
		Console telephone=new Console(scanner, controller);
		connection.resetConnection();
		Assert.assertEquals(true, connection.isConnected());
	}
	
	@Test
	public void EnterAStringAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Hola");
		MailSystem mailSystem=new MailSystem(20,persistenceMocked);
		Connection connection=new Connection(mailSystem, new PresentersManagerManager());
		IController controller=new Controller(connection);
		Console telephone=new Console(scanner, controller);
		connection.resetConnection();
		Assert.assertEquals(connection.isRecording(), false);
	}
	
	@Test
	public void EnterTwoLettersAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("#");

		MailSystem mailSystem=new MailSystem(20,persistenceMocked);
		Connection connection=new Connection(mailSystem, new PresentersManagerManager());
		IController controller=new Controller(connection);
		Console telephone=new Console(scanner, controller);
		connection.resetConnection();
		Assert.assertEquals(connection.isRecording(), false);
	}
	
	private Scanner GetScannerWithThisString(String texto) {
		texto=texto+" \nQ";
		InputStream inputStream=new ByteArrayInputStream(texto.getBytes());
		System.setIn(inputStream);
		return new Scanner(System.in);
	}

	private ArrayList<Mailbox> SetupArrayListMailboxes() {
		ArrayList<Mailbox> mails=new ArrayList<Mailbox>();
		mails.add(new Mailbox("",""));
		mails.add(new Mailbox("",""));
		mails.add(new Mailbox("",""));
		return mails;
	}

}
