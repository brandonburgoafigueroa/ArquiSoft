package com.arqui;

import com.arqui.Api.VoiceMailService;
import com.arqui.Core.Connection;
import com.arqui.Core.IConnection;
import com.arqui.Core.MailSystem;
import com.arqui.Presenters.ConsolePresenter;
import com.arqui.Presenters.PresentersManager;
import com.arqui.Repository.DBContext;
import com.arqui.Views.Console;
import com.arqui.Views.UserInterface;

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
      IPresenters observers=new PresentersManager();

      IConnection c = new Connection(system, observers);
      VoiceMailService vc = new VoiceMailService(c);
      Console console = new Console(consoleInput, c);
//presenters

      UserInterface FirstUI = new UserInterface(c);

      UserInterface SecondUI = new UserInterface(c);

      ConsolePresenter cp=new ConsolePresenter();
      cp.addView(console);
      cp.addView(FirstUI);
      cp.addView(SecondUI);
      observers.addPresenter(cp);
      c.resetConnection();
      vc.startAPIService();

      console.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
