import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ConnectionTest {

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedTelephone = mock(Telephone.class);
	    connection = new Connection(mockedMailsystem);
	    connection.addObservable(mockedTelephone);
		connection.startConnection();
        Mailbox chosenMailbox = new Mailbox(idMailBox, HI_MESSAGE);
        when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
	}

	@Test
	public void shouldBeInConnectedStatus() {
		connection.startConnection();
	    assertTrue(connection.isConnected());
	}

	@Test
	public void shouldShowInitialMessage() {

        verify(mockedTelephone, times(1)).Update(ENTER_MAILBOX_MESSAGE);
	}
	
	@Test
	public void shouldChooseValidMailBox() {
		connection.dial(idMailBox);
		connection.dial("#");
		assertTrue(connection.isRecording());
		verify(mockedTelephone).Update(HI_MESSAGE);
	}
	@Test
    public void whenIStartConnectionShoudlBeReturnTrue()
    {
        connection.startConnection();
        Assert.assertEquals(connection.isConnected(), true);
    }
	@Test
	public void shouldGetIntoMailBoxMenu() {

		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");

		assertTrue(connection.isMailBoxMenu());
	}
	
	@Test
	public void shouldGetIntoMessageMenu() {

		String mailBoxMenuOption = "1";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);
		connection.dial("#");

		assertTrue(connection.isMessageMenu());
	}
	
	@Test
	public void shouldGetIntoChangePassCodeMenu() {

		String mailBoxMenuOption = "2";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);

		assertTrue(connection.isChangePassCode());
	}

	@Test
	public void shouldChangePassCodeFrom1To2() {

		String newKeyMailBox = "2";
		String mailBoxMenuOption = "2";
		String hangDown = "h";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);
		
		connection.dial(newKeyMailBox);
		connection.dial("#");
		connection.dial(hangDown);
		
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(newKeyMailBox);
		connection.dial("#");
		
		assertTrue(connection.isMessageMenu());
	}
	
	@Test
	public void whenISendAMessageToAccountOneAndSeeTheLastMessageIShouldReturnTheMessageISend() {

		String mailBoxMenuOption = "1";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);	
		connection.dial(mailBoxMenuOption);	
		verify(mockedTelephone).Update(HI_MESSAGE);
	}
    
	
	@Test
	public void whenIDeleteTheLastMessageIShouldReturnTheStartMessage() {

		String mailBoxMenuOption = "3";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial("1");	
		connection.dial(mailBoxMenuOption);	
		verify(mockedTelephone).Update(ENTER_MAILBOX_MESSAGE);
	}
	@Test
	public void whenIEnterTheMessageMenuAndSelectTheOptionExitShouldReturnTheStartMessage() {

		String mailBoxMenuOption = "4";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial("1");	
		connection.dial(mailBoxMenuOption);	
		verify(mockedTelephone).Update(ENTER_MAILBOX_MESSAGE);
	}
	
	@Test
	public void shouldGetIntoChangeGreetingMenu() {

		String mailBoxMenuOption = "3";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);

		assertTrue(connection.isChangeGreeting());
	}
	
	@Test
	public void shouldChangeGreetingFromStockOneToNewOne() {
		String optionForChangeGreeting = "3";
		String newGreeting="Hola, como estas?";
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(optionForChangeGreeting);

		connection.dial(newGreeting);
		connection.dial("#");
		connection.dial("h");

		connection.dial(idMailBox);
		connection.dial("#");

		verify(mockedTelephone).Update(newGreeting);
	}
	
	@Test
	public void whenIEnterANumberOfMailThatDoesNotExistIShouldReturnTheIncorrectLoginMessage()
	{
		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(null);
		connection.dial(idMailBox);
		connection.dial("#");
        String INCORRECT_MAILBOX_NUMBER_MESSAGE = "Incorrect mailbox number. Try again!";
        verify(mockedTelephone).Update(INCORRECT_MAILBOX_NUMBER_MESSAGE);
	}

    private MailSystem mockedMailsystem;
    private Telephone mockedTelephone;
    private Connection connection;
    private String ENTER_MAILBOX_MESSAGE = "Enter mailbox number followed by #";
    private String idMailBox = "1";
    private String keyMailBox = "1";
    private String HI_MESSAGE="Hola, como estas?";

}