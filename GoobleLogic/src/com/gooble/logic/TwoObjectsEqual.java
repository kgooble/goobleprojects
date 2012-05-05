package com.gooble.logic;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TwoObjectsEqual{
   public static boolean byReflection(Object obj1, Object obj2) {
      Field[] fields1 = obj1.getClass().getDeclaredFields();
      Field[] fields2 = obj2.getClass().getDeclaredFields();
      if (!Arrays.equals(fields1, fields2))
         return false;
      
      for (int i = 0; i < fields1.length; i++){
         fields1[i].setAccessible(true);
         fields2[i].setAccessible(true);
         try {
            if (!fields1[i].get(obj1).equals(fields2[i].get(obj2)))
               return false;
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      return true;
   }
}