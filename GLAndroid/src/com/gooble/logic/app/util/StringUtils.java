package com.gooble.logic.app.util;

import java.util.Collection;

public class StringUtils {
   public static String join(String separator, Collection<String> strings){
      int i = 0;
      StringBuilder sb = new StringBuilder();
      for (String str : strings){
         sb.append(str);
         if (i < strings.size() - 1){
            sb.append(separator);
         }
         i ++;
      }
      return sb.toString();
   }
}
