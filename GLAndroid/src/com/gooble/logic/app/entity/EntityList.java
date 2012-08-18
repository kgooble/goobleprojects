package com.gooble.logic.app.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityList<E extends Entity> extends ArrayList<E> {

   private static final long serialVersionUID = 1L;

   public List<?> getColumn(String field) {
      List<Object> column = new ArrayList<Object>();
      for (E e : this){
         column.add(e.getField(field));
      }
      return column;
   }
   
   public int findPosition(String field, Object value){
      if (value == null)
         return 0;
      int position = 0;
      for (int i = 0; i < size(); i++){
         if (value.equals(get(i).getField(field))){
            position = i;
            break;
         }
      }
      return position;
   }
}
