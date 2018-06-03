package com.arqui.Views;

import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements IView
{
   private List<String> Informations;
   private List<String> Options;
   public Console(Scanner aScanner, IConnection connection)
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
   @Override
   public void setInformation(String information) {
      Informations.add(information);
   }

   @Override
   public void setOption(String option) {
      String Option="Enter "+Options.size()+1+" to "+option;

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


   @Override
   public void setPersistenceText(String persistenceType) {
      System.out.println(persistenceType);
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
   private IConnection connection;
}
