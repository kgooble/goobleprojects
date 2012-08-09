package com.gooble.logic.app.entity;

import java.lang.reflect.Method;

public class Entity {
   private long id;
   private boolean isNew;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }
   public void setNew(boolean isNew){
      this.isNew = isNew;
   }
   public boolean isNew(){
      return isNew;
   }
   public void setField(String fieldName, Object fieldValue){
      String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      Method setter;
      try {
         setter = getClass().getDeclaredMethod("set" + capitalizedFieldName, fieldValue.getClass());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      try {
         setter.invoke(this, fieldValue);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   public Object getField(String fieldName){
      String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      Method getter;
      try {
         getter = getClass().getDeclaredMethod("get" + capitalizedFieldName);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      try {
         return getter.invoke(this);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
