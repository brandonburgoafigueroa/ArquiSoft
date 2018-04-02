import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.Assert;

public class TelephoneTest {
	@Test
	public void EnterAChainInPhoneAndPrintTheChainShouldReturnMeSame() {
		Scanner scanner=new Scanner(System.in);
		Telephone telephone=new Telephone(scanner);
		String output="Enter mailbox number followeb by #";
		Assert.assertEquals(telephone.speakT(output), "Enter mailbox number followeb by #");
		
	}
	@Test
	public void IncomeLetterHAndICheckThattheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("H");
		Telephone telephone=new Telephone(scanner);
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem, telephone);
		telephone.run(connection);
		Assert.assertEquals(connection.isConnected(), true);
	}
	
	@Test
	public void IncomeLetterQAndICheckThattheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Q");
		Telephone telephone=new Telephone(scanner);
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem, telephone);
		telephone.run(connection);
		Assert.assertEquals(connection.isConnected(), true);
	}
	
	@Test
	public void EnterAStringAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("Hola");
		Telephone telephone=new Telephone(scanner);
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem, telephone);
		telephone.run(connection);
		Assert.assertEquals(connection.isRecording(), false);
	}
	
	@Test
	public void EnterTwoLettersAndCheckThatTheConnectionIsEstablishedShouldReturnTrue() {
		Scanner scanner=GetScannerWithThisString("#");
		Telephone telephone=new Telephone(scanner);
		MailSystem mailSystem=new MailSystem(20);
		Connection connection=new Connection(mailSystem, telephone);
		telephone.run(connection);
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
