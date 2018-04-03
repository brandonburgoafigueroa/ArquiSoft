import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.util.Scanner;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Telephone p = new Telephone(console);
       Connection c = new Connection(system, p);
       UIObserver frame = new UIObserver(c);
       frame.pack();
       frame.setVisible(true);
       frame.setSize(400,400);

      p.run(c);

   }

   private static final int MAILBOX_COUNT = 20;
}
