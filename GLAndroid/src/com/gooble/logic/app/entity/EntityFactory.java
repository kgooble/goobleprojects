package com.gooble.logic.app.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class EntityFactory<E extends Entity> {

   private final Class<E> entityClass;

   public EntityFactory(Class<E> entityClass){
      this.entityClass = entityClass;
   }
   
   public Class<E> getEntityClass() {
      return entityClass;
   }

   public String getTableName() {
      return entityClass.getSimpleName();
   }

   public E create() {
      try {
         return entityClass.getConstructor().newInstance();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }      
   }

   public Iterable<String> getFields() {
      Field[] fields = entityClass.getDeclaredFields();
      List<String> fieldNames = new ArrayList<String>();
      for (Field f : fields){
         fieldNames.add(f.getName().toLowerCase());
      }
      return fieldNames;
   }
   
   public Class<?> getFieldType(String field){
      try {
         return entityClass.getDeclaredField(field).getType();
      } catch (Exception e){
         throw new RuntimeException(e);
      }
   }

}
