package com.arqui;

import com.arqui.DisplayState.IDisplay;

import java.util.ArrayList;
import java.util.Scanner;

public class Console implements View
{
   public Console(Scanner aScanner, Connection connection)
   {
      scanner = aScanner;
      this.connection=connection;
   }
   public void showText(String message)
   {
      System.out.println(message);
   }

   @Override
   public void showOptions(IDisplay display) {
      ArrayList<String> options=display.getOptions();
      int i=1;
      for (String option:options)
      {
         System.out.println("Enter "+i+" to "+option);
         i++;
      }
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
         connection.executeCommand(input);
      }
   }

   private Scanner scanner;
   private Connection connection;
}
