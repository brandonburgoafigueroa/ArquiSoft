import java.util.Scanner;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Telephone implements IObservable
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
   public Telephone(Scanner aScanner, Connection connection)
   {
      scanner = aScanner;
      this.connection=connection;
      this.connection.AddObservable(this);
   }
   /**
      Speak a message to System.out.
      @param message the text that will be "spoken"
   */
   public void Update(String message)
   {
      System.out.println(message);
   }
   
   public String speakT(String output)
   {
	   return output;
   }

   /**
      Loops reading user input and passes the input to the
      Connection object's methods dial, record or hangup.
      @param //c the connection that connects this phone to the
      voice mail system
   */
   public void run()
   {
      boolean more = true;
      while (more)
      {
         String input = scanner.nextLine();
         more=connection.executeCommand(input);
      }
      System.exit(0);
   }

   private Scanner scanner;
   private Connection connection;

}
