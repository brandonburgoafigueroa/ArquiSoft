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
   public void SetConnection(Connection connection)
   {
      this.connection=connection;
   }
   public Telephone(Scanner aScanner)
   {
      scanner = aScanner;
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
         if (input == null) return;
         if (input.equalsIgnoreCase("H"))
            connection.hangup();
         else if (input.equalsIgnoreCase("Q"))
            more = false; 
         else if (input.length() == 1
            && "1234567890#".indexOf(input) >= 0)
            connection.dial(input);
         else
            connection.record(input);
      }
   }

   public void run(Connection connection)
   {
      boolean more = true;
      while (more)
      {
         String input = scanner.nextLine();
         if (input == null) return;
         if (input.equalsIgnoreCase("H"))
            connection.hangup();
         else if (input.equalsIgnoreCase("Q"))
            more = false;
         else if (input.length() == 1
                 && "1234567890#".indexOf(input) >= 0)
            connection.dial(input);
         else
            connection.record(input);
      }
   }
   private Scanner scanner;
   private Connection connection;

}
