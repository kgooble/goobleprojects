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
}
