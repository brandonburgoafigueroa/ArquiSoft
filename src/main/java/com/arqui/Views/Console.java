package com.arqui.View;

import com.arqui.Connection;
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

   public void show(String text)
   {
      System.out.println(text);
   }
   public void showText(String text)
   {
      String Text=display.getText(text);
      System.out.println(Text);
   }

   @Override
   public void showError(String errorName) {
      String Text=display.getError(errorName);
      System.out.println(Text);
   }

   @Override
   public void showOptions() {
      int i=1;
      ArrayList<String> options=display.getOptions();
      for (String option:options)
      {
         System.out.println("Enter "+i+" to "+option);
         i++;
      }
   }

   @Override
   public void setDisplay(IDisplay display) {
      this.display=display;
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
   private IDisplay display;
}
