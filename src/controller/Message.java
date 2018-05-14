package controller;

public class Message
{

   private String from;
   private String text;
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

}
