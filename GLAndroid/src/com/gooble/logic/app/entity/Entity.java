package com.gooble.logic.app.entity;

import java.lang.reflect.Method;

import com.gooble.logic.app.db.Tables;

public class Entity {
   private long id;
   private boolean isNew;

   public final long getId() {
      return id;
   }
   public final void setId(long id) {
      this.id = id;
   }
   public final void setNew(boolean isNew){
      this.isNew = isNew;
   }
   public final boolean isNew(){
      return isNew;
   }
   public final void setField(String fieldName, Object fieldValue){
      String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      Object originalValue = getField(fieldName);
      if (fieldValue == null && originalValue == null)
         return;
      Class<?> fieldClass;
      if (fieldValue == null)
         fieldClass = originalValue.getClass();
      else
         fieldClass = fieldValue.getClass();
      Method setter;
      try {
         setter = getClass().getDeclaredMethod("set" + capitalizedFieldName, fieldClass);
      } catch(NoSuchMethodException e){
         if (fieldClass == Integer.class || fieldClass == Integer.TYPE){
            try {
               setter = getClass().getDeclaredMethod("set" + capitalizedFieldName, Long.class);
               fieldValue = new Long((Integer) fieldValue);
            } catch (Exception e1) {
               throw new RuntimeException(e1);
            }
         }else{
            throw new RuntimeException(e);
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      try {
         setter.invoke(this, fieldValue);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   public final Object getField(String fieldName){
      String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      if (fieldName == Tables._ID)
         capitalizedFieldName = "Id";
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
   public final String getTableName() {
      return getClass().getSimpleName().toLowerCase();
   }
   public final Iterable<String> getFields() {
      return new EntityFields(getClass()).getFields();
   }
   public final Class<?> getFieldType(String field){
      return new EntityFields(getClass()).getFieldType(field);
   }
}
