import controller.Connection;
import controller.MailSystem;
import persistence.DBContext;
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
      DBContext db = new DBContext();
      db.sendMessage("pepe","juan","hola pepe como estas");
      db.showAllMessages();
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Connection c = new Connection(system);
      Console p = new Console(console, c);
      UserInterface FirstUI = new UserInterface(c);
      FirstUI.setVisible(true);
      UserInterface SecondUI = new UserInterface(c);
      SecondUI.setVisible(true);
      c.resetConnection();
      p.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
