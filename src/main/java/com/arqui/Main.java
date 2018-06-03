package com.arqui;

import com.arqui.Api.VoiceMailService;
import com.arqui.Core.Connection;
import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IMailSystem;
import com.arqui.Core.MailSystem;
import com.arqui.Interfaces.IPersistence;
import com.arqui.Interfaces.IPresenters;
import com.arqui.Presenters.Presenter;
import com.arqui.Presenters.PresentersManager;
import com.arqui.Repository.Database;
import com.arqui.Repository.Memory;
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

      IPersistence context=new Database();
      IPersistence memory=new Memory();
      IMailSystem system = new MailSystem(MAILBOX_COUNT,context);
      Scanner consoleInput = new Scanner(System.in);
      IPresenters presenters=new PresentersManager();

      IConnection c = new Connection(system, presenters);
      VoiceMailService vc = new VoiceMailService(c);
      Console console = new Console(consoleInput, c);
//presenters

      UserInterface FirstUI = new UserInterface(c);

      UserInterface SecondUI = new UserInterface(c);

      Presenter cp=new Presenter();
      cp.addView(console);
      cp.addView(FirstUI);
      cp.addView(SecondUI);
      presenters.addPresenter(cp);
      c.resetConnection();
      vc.startAPIService();

      console.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
