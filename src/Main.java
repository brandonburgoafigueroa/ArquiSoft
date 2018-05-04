import controller.Connection;
import controller.MailSystem;
import controller.Observers;
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
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Observers observers=new Observers();
      Connection c = new Connection(system, observers);
      Console p = new Console(console, c);
      UserInterface FirstUI = new UserInterface(c);
      FirstUI.setVisible(true);
      UserInterface SecondUI = new UserInterface(c);
      SecondUI.setVisible(true);
      observers.addObservable(FirstUI);
      observers.addObservable(SecondUI);
      observers.addObservable(p);
      c.resetConnection();
      p.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
