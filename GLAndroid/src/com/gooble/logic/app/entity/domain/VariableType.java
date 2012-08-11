package com.gooble.logic.app.entity.domain;

public class VariableType {

   public final static String TEXT = "Text";
   public final static String NUMBER = "Number";
   public static final String[] ALL_TYPES = new String[] { TEXT, NUMBER };

   public static int getPosition(String type) {
      if (NUMBER.equals(type))
         return 1;
      return 0;
   }

   public static String getValue(int position) {
      if (position > ALL_TYPES.length || position < 0)
         return TEXT;
      return ALL_TYPES[position];
   }

}
