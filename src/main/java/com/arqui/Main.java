package com.arqui;

import com.arqui.Api.VoiceMailService;
import com.arqui.Controller.Controller;
import com.arqui.Core.Connection;
import com.arqui.Interfaces.*;
import com.arqui.Core.MailSystem;
import com.arqui.Presenters.Presenter;
import com.arqui.Presenters.PresentersManagerManager;
import com.arqui.Repository.Database;
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

      IMailSystem system = new MailSystem(MAILBOX_COUNT,context);

      Scanner consoleInput = new Scanner(System.in);

      IPresentersManager presentersManager=new PresentersManagerManager();

      IConnection connection = new Connection(system, presentersManager);

      IApi VoiceMailService = new VoiceMailService(connection);

      IController controller=new Controller(connection);

      Console console = new Console(consoleInput, controller);


      UserInterface FirstUI = new UserInterface(controller);

      UserInterface SecondUI = new UserInterface(controller);

      Presenter presenter=new Presenter();

      presenter.addView(console);

      presenter.addView(FirstUI);

      presenter.addView(SecondUI);*/

      presentersManager.addPresenter(presenter);

      connection.resetConnection();

      console.run();
   }

   private static final int MAILBOX_COUNT = 20;
}
