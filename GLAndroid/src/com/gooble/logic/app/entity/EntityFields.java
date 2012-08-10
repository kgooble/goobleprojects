package com.gooble.logic.app.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityFields {

   private final Class<? extends Entity> entityClass;

   public EntityFields(Class<? extends Entity> entityClass) {
      this.entityClass = entityClass;
   }
   
   public Iterable<String> getFields(){
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
