

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ConsoleTest {
	@Test
	public void EnterAChainInPhoneAndPrintTheChainShouldReturnMeSame() {
		Scanner scanner=new Scanner(System.in);
		Connection connection=new Connection(new MailSystem(20));
		Console telephone=new Console(scanner, connection);
		String ENTER_MAILBOX_MESSAGE = "Enter mailbox number followed by #";
		String output= ENTER_MAILBOX_MESSAGE;
		assertEquals(telephone.speakT(output), ENTER_MAILBOX_MESSAGE);
		
	}
	@Test
	public void IncomeLetterHAndICheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("H");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Console telephone=new Console(scanner, connection);
		connection.startConnection();
		telephone.run();
		assertEquals(connection.isConnected(), true);
	}

	@Test
	public void IncomeLetterQAndICheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Q");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Console telephone=new Console(scanner, connection);
		connection.startConnection();
		telephone.run();
		assertEquals(true, connection.isConnected());
	}
	
	@Test
	public void EnterAStringAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Hola");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Console telephone=new Console(scanner, connection);
		telephone.run();
		assertEquals(connection.isRecording(), false);
	}
	
	@Test
	public void EnterTwoLettersAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("#");

		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Console telephone=new Console(scanner, connection);
		telephone.run();
		assertEquals(connection.isRecording(), false);
	}
	
	private Scanner GetScannerWithThisString(String texto) {
		texto=texto+" \nQ";
		InputStream inputStream=new ByteArrayInputStream(texto.getBytes());
		System.setIn(inputStream);
		return new Scanner(System.in);
	}

}
