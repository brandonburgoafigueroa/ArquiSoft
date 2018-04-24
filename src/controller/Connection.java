import view.View;

import java.util.ArrayList;
import java.util.List;


public class Connection
{
   Connection(MailSystem s)
   {
       system = s;
       observables=new ArrayList();
   }

   public void startConnection()
   {
       currentRecording = "";
       accumulatedKeys = "";
       state = CONNECTED;
       updateObservables(INITIAL_PROMPT);
   }

   public void addObservable(View observable)
   {
      observables.add(observable);

   }

   private void updateObservables(String message)
   {
      for (View observer:observables) {
       observer.update(message);
      }
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            hangup();
        else if (isQuitCommand(input))
            return false;
        else if (isNumericalCommand(input))
            dial(input);
        else
            record(input);
        return true;
    }

    private void hangup()
    {
        if (state == RECORDING) {
            currentMailbox.addMessage(new Message(currentRecording));

        }
        startConnection();

    }
   public void dial(String key)
   {
      if (isConnected())
         connect(key);
      else if (isRecording())
         login(key);
      else if (isChangePassCode())
         changePasscode(key);
      else if (isChangeGreeting())
         changeGreeting(key);
      else if (isMailBoxMenu())
         mailboxMenu(key);
      else if (isMessageMenu())
         messageMenu(key);
   }

    private void connect(String key)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox = system.findMailbox(accumulatedKeys);
            if (currentMailbox != null)
            {
                state = RECORDING;

                updateObservables(currentMailbox.getGreeting());
            }
            else {
                updateObservables(INCORRECT_MAILBOX_MESSAGE);
            }
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }

    private void login(String key)
    {
        if (itIsANumeralCharacter(key))
        {
            if (currentMailbox.checkPasscode(accumulatedKeys))
            {
                state = MAILBOX_MENU;

                updateObservables(MAILBOX_MENU_TEXT);
            }
            else {
                updateObservables(INCORRECT_PASSCODE_MESSAGE);
            }
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }
    private void changePasscode(String key)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setPasscode(accumulatedKeys);
            state = MAILBOX_MENU;
            updateObservables(MAILBOX_MENU_TEXT);
            accumulatedKeys = "";
        }
        else
            accumulatedKeys += key;
    }
    private void changeGreeting(String key)
    {
        if (itIsANumeralCharacter(key))
        {
            currentMailbox.setGreeting(currentRecording);
            currentRecording = "";
            state = MAILBOX_MENU;
            updateObservables(MAILBOX_MENU_TEXT);
        }
    }

    private void mailboxMenu(String key)
    {
        switch (key) {
            case "1":
                state = MESSAGE_MENU;

                updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "2":
                state = CHANGE_PASSCODE;

                updateObservables(ENTER_NEW_PASSCODE_MESSAGE);
                break;
            case "3":
                state = CHANGE_GREETING;


                updateObservables(ENTER_NEW_GREETING_MESSAGE);
                break;
        }
    }

    private void messageMenu(String key)
    {
        switch (key) {
            case "1":
                String output = "";
                Message m = currentMailbox.getCurrentMessage();
                if (m == null) {
                    output += EMPTY_MAILBOX_MESSAGE + "\n";
                }
                else output += m.getText() + "\n";
                output += MESSAGE_MENU_TEXT;
                updateObservables(output);
                break;
            case "2":
                currentMailbox.saveCurrentMessage();
                updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "3":
                currentMailbox.removeCurrentMessage();
                updateObservables(MESSAGE_MENU_TEXT);
                break;
            case "4":
                state = MAILBOX_MENU;
                updateObservables(MAILBOX_MENU_TEXT);
                break;
        }

    }
    public boolean isConnected() {
	   return state==CONNECTED;
   }

   public boolean isRecording() {
	   return state==RECORDING;
   }

   public boolean isChangePassCode() {
	   return state == CHANGE_PASSCODE;
   }

   public boolean isChangeGreeting() {
	   return state == CHANGE_GREETING;
   }

   public boolean isMailBoxMenu() {
	   return state == MAILBOX_MENU;
   }

   public boolean isMessageMenu() {
	   return state == MESSAGE_MENU;
   }

   private void record(String voice)
   {
      if (state == RECORDING || state == CHANGE_GREETING)
         currentRecording += voice;
   }

    private boolean itIsANumeralCharacter(String key) {
        return key.equals("#");
    }


    private boolean isNumericalCommand(String input) {
        return input.length() == 1
                && "1234567890#".contains(input);
    }

    private boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase("Q");
    }

    private boolean isInputHangUpCommand(String input) {
        return input.equalsIgnoreCase("H");
    }
    private MailSystem system;
    private Mailbox currentMailbox;
    private String currentRecording;
    private String accumulatedKeys;
    private int state;
    private List<View> observables;
    private static final int CONNECTED = 1;
    private static final int RECORDING = 2;
    private static final int MAILBOX_MENU = 3;
    private static final int MESSAGE_MENU = 4;
    private static final int CHANGE_PASSCODE = 5;
    private static final int CHANGE_GREETING = 6;
    private String ENTER_NEW_GREETING_MESSAGE = "Record your greeting, then press the # key";
    private String INCORRECT_PASSCODE_MESSAGE = "Incorrect passcode. Try again!";
    private String ENTER_NEW_PASSCODE_MESSAGE = "Enter new passcode followed by the # key";
    private String EMPTY_MAILBOX_MESSAGE = "No messages.";
    private String INCORRECT_MAILBOX_MESSAGE = "Incorrect mailbox number. Try again!";
    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";
    private static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    private static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";

}











