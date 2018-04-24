package test;

import controller.Connection;
import controller.MailSystem;
import controller.Mailbox;
import org.junit.Before;
import org.junit.Test;
import view.Console;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
public class ConnectionTest {

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedTelephone = mock(Console.class);
	    connection = new Connection(mockedMailsystem);
	    connection.addObservable(mockedTelephone);
		connection.startConnection();
        Mailbox chosenMailbox = new Mailbox(idMailBox, HI_MESSAGE);
        when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
	}

	@Test
	public void theConnectionWasStarted() {
		connection.startConnection();
	    assertTrue(connection.isConnected());
	}

	@Test
	public void shouldShowInitialMessageWhenTheApplicationWasStarted() {

        verify(mockedTelephone, times(1)).update(ENTER_MAILBOX_MESSAGE);
	}
	
	@Test
	public void shouldChooseValidMailBoxWhenSelectTheMailBoxAndSelectNumeral() {
		dialMailBox(idMailBox);
		assertTrue(connection.isRecording());
		verify(mockedTelephone).update(HI_MESSAGE);
	}
	@Test
	public void shouldGetIntoMailBoxMenuWhenSelectTheMailBox() {

		dialMailBox(idMailBox);
		dialMailBoxMenu();
		assertTrue(connection.isMailBoxMenu());
	}


	@Test
	public void shouldGetIntoMessageMenuWhenSelectTheMailBox() {

		String mailBoxMenuOption = "1";
		dialMailBox(idMailBox);
		dialMailBoxMenu();
		listenMessages();

		assertTrue(connection.isMessageMenu());
	}
	@Test
	public void shouldGetIntoChangePassCodeMenuWhenSelectMenuOption2() {

		String changePasscode = "2";
		dialMailBox(idMailBox);
		selectOptionOfMailBoxMenu(changePasscode);
		assertTrue(connection.isChangePassCode());
	}
	@Test
	public void shouldGetIntoChangeGreetingMenuAfterSelectMenuOption3() {

		String changeGreeting = "3";
		dialMailBox(idMailBox);
		selectOptionOfMailBoxMenu(changeGreeting);

		assertTrue(connection.isChangeGreeting());
	}

	@Test
	public void shouldChangePassCodeFrom1To2AfterSelectTheMailBox() {
		String newKeyMailBox = "2";
		dialMailBox(idMailBox);
		dialMailBoxMenu();
		changePasscode(newKeyMailBox);
		dialMailBox(idMailBox);
		dialMailBox(newKeyMailBox);

		assertTrue(connection.isMessageMenu());
	}


	@Test
	public void whenISendAMessageToAccountOneAndSeeTheLastMessageIShouldReturnTheMessageISend() {

		String mailBoxMenuOption = "1";
		dialMailBox(idMailBox);
		selectOptionOfMailBoxMenu(mailBoxMenuOption);
		verify(mockedTelephone).update(HI_MESSAGE);
	}



	@Test
	public void whenIDeleteTheLastMessageIShouldReturnTheStartMessage() {

		String deleteCurrentMessage = "3";
		dialMailBox(idMailBox);
		dialMailBoxMenu();
		selectOptionOfListenMessagesMenu(deleteCurrentMessage);
		verify(mockedTelephone).update(ENTER_MAILBOX_MESSAGE);
	}
	@Test
	public void whenIEnterTheMessageMenuAndSelectTheOptionExitShouldReturnTheStartMessage() {

		String returnMainMenu = "4";
		dialMailBox(idMailBox);
		dialMailBoxMenu();
		selectOptionOfListenMessagesMenu(returnMainMenu);
		verify(mockedTelephone).update(ENTER_MAILBOX_MESSAGE);
	}

	private void selectOptionOfListenMessagesMenu(String option) {
		connection.dial("1");
		connection.dial(option);
	}


	
	@Test
	public void shouldChangeGreetingFromStockOneToNewOne() {
		String optionForChangeGreeting = "3";
		String newGreeting="Hola, como estas?";
		dialMailBox(idMailBox);
		dialMailBoxMenu();
		changeGreeting(optionForChangeGreeting, newGreeting);
		dialMailBox(idMailBox);
		verify(mockedTelephone).update(newGreeting);
	}


	@Test
	public void whenIEnterANumberOfMailThatDoesNotExistIShouldReturnTheIncorrectLoginMessage()
	{
		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(null);
		dialMailBox(idMailBox);
        String INCORRECT_MAILBOX_NUMBER_MESSAGE = "Incorrect mailbox number. Try again!";
        verify(mockedTelephone).update(INCORRECT_MAILBOX_NUMBER_MESSAGE);
	}

	private void selectOptionOfMailBoxMenu(String mailBoxMenuOption) {
		dialMailBoxMenu();
		connection.dial(mailBoxMenuOption);
	}

	private void changePasscode(String newKeyMailBox) {

		String mailBoxMenuOption = "2";
		String hangDown = "h";
		connection.dial(mailBoxMenuOption);
		connection.dial(newKeyMailBox);
		connection.dial("#");
		connection.dial(hangDown);
	}
	private void changeGreeting(String optionForChangeGreeting, String newGreeting) {
		connection.dial(optionForChangeGreeting);
		connection.dial(newGreeting);
		connection.dial("#");
		connection.dial("h");
	}

	private void dialMailBox(String idMailBox) {
		connection.dial(idMailBox);
		connection.dial("#");
	}

	private void listenMessages() {
		connection.dial("1");
		connection.dial("#");
	}

	private void dialMailBoxMenu() {
		connection.dial(keyMailBox);
		connection.dial("#");
	}
    private MailSystem mockedMailsystem;
    private Console mockedTelephone;
    private Connection connection;
    private String ENTER_MAILBOX_MESSAGE = "Enter mailbox number followed by #";
    private String idMailBox = "1";
    private String keyMailBox = "1";
    private String HI_MESSAGE="Hola, como estas?";

}