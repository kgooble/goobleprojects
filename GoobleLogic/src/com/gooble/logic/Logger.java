package com.gooble.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {

   private static PrintWriter OUT;

   public static void log(String... strings){
      if (OUT == null){
         try {
            OUT =  new PrintWriter(new File("logfile.txt"));
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         }
      }
      if (strings.length == 0){
         OUT.println();
      }
      for (String s: strings){
         OUT.println(s);
      }
   }
   
   public static void close(){
      if (OUT != null)
         OUT.close();
   }

}
