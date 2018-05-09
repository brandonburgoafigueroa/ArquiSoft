import controller.*;
import persistence.DBContext;
import persistence.IPersistence;
import view.Console;
import view.UserInterface;

import java.util.Scanner;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class Main
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT, new DBContext());
      Scanner consoleInput = new Scanner(System.in);
      IObservers observers=new Observers();
      IPersistence persistence=new DBContext();

      Connection c = new Connection(system, observers);

      Console console = new Console(consoleInput, c);

      UserInterface FirstUI = new UserInterface(c);

      UserInterface SecondUI = new UserInterface(c);

      observers.addObservable(FirstUI);
      observers.addObservable(SecondUI);
      observers.addObservable(console);
      c.resetConnection();
      console.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
