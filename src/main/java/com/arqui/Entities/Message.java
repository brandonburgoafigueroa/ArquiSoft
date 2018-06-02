package com.arqui.Entities;

public class Message
{
   public Message(String messageText)
   {
      text = messageText;
   }
   public Message(String From, String messageText)
   {
      from = From;
      text = messageText;
   }
   public String getText()
   {
      return text;
   }

   public String getFrom() {
      return from;
   }

   private String from;
   private String text;
}
