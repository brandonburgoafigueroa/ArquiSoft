package com.arqui.Views;

import com.arqui.Core.Connection;
import com.arqui.DisplayState.IDisplay;
import com.arqui.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements IView
{
   private List<String> Informations;
   private List<String> Options;
   public Console(Scanner aScanner, Connection connection)
   {
      scanner = aScanner;
      this.connection=connection;
      Informations=new ArrayList<>();
      Options=new ArrayList<>();
   }

   public void show(String text)
   {
      System.out.println(text);
   }
//presenters
   @Override
   public void setInformation(String information) {
      Informations.add(information);
   }

   @Override
   public void setOption(String option) {
      Options.add(option);
   }

   @Override
   public void showView() {
      for (String text:Informations) {
         System.out.println(text);
      }
      for (String option:Options) {
         System.out.println(option);
      }
      Informations.clear();
      Options.clear();
   }

   //end presenteres

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
