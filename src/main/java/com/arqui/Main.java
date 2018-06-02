package com.arqui;

import com.arqui.Core.MailSystem;
import com.arqui.Views.Console;

import java.util.Scanner;



/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class Main
{
   public static void main(String[] args)
   {

      IPersistence context=new DBContext();
      MailSystem system = new MailSystem(MAILBOX_COUNT,context);
      Scanner consoleInput = new Scanner(System.in);
      IObservers observers=new Observers();

      Connection c = new Connection(system, observers);
      VoiceMailService vc = new VoiceMailService(c);
      Console console = new Console(consoleInput, c);

      UserInterface FirstUI = new UserInterface(c);

      UserInterface SecondUI = new UserInterface(c);
      observers.addObservable(FirstUI);
      observers.addObservable(SecondUI);
      observers.addObservable(console);
      c.resetConnection();
      vc.startAPIService();

      console.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
