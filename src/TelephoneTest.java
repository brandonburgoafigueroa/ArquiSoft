import junit.framework.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class TelephoneTest {
	@Test
	public void EnterAChainInPhoneAndPrintTheChainShouldReturnMeSame() {
		Scanner scanner=new Scanner(System.in);
		Connection connection=new Connection(new MailSystem(20));
		Telephone telephone=new Telephone(scanner, connection);
		String output="Enter mailbox number followeb by #";
		Assert.assertEquals(telephone.speakT(output), "Enter mailbox number followeb by #");
		
	}
	@Test
	public void IncomeLetterHAndICheckThattheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("H");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Telephone telephone=new Telephone(scanner, connection);
		connection.startConnection();
		telephone.run();
		Assert.assertEquals(connection.isConnected(), true);
	}

	@Test
	public void IncomeLetterQAndICheckThattheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Q");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Telephone telephone=new Telephone(scanner, connection);
		connection.startConnection();
		telephone.run();
		Assert.assertEquals(true, connection.isConnected());
	}
	
	@Test
	public void EnterAStringAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Hola");
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Telephone telephone=new Telephone(scanner, connection);
		telephone.run();
		Assert.assertEquals(connection.isRecording(), false);
	}
	
	@Test
	public void EnterTwoLettersAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("#");

		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem);
		Telephone telephone=new Telephone(scanner, connection);
		telephone.run();
		Assert.assertEquals(connection.isRecording(), false);
	}
	
	public Scanner GetScannerWithThisString(String texto) {
		texto=texto+" \nQ";
		InputStream a=new ByteArrayInputStream(texto.getBytes());
		System.setIn(a);
		a=new ByteArrayInputStream("Q".getBytes());
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}

}
