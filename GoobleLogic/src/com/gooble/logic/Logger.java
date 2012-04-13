package com.gooble.logic;

public class Logger {

   public static void log(String... strings){
      if (strings.length == 0)
         System.out.println();
      for (String s: strings){
         System.out.println(s);
      }
   }
   
}
