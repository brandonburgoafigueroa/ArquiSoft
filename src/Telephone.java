import java.util.Scanner;

public class Telephone implements IObservable
{
   Telephone(Scanner aScanner, Connection connection)
   {
      scanner = aScanner;
      this.connection=connection;
      this.connection.addObservable(this);
   }
   public void Update(String message)
   {
      System.out.println(message);
   }
   
   public String speakT(String output)
   {
	   return output;
   }
   public void run()
   {
      boolean more = true;
      while (more) {
         String input = scanner.nextLine();
         more = connection.executeCommand(input);
      }
   }

   private Scanner scanner;
   private Connection connection;

}
