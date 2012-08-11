package com.gooble.logic.app.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.db.Tables;

public class EntityFields {

   private final Class<? extends Entity> entityClass;

   public EntityFields(Class<? extends Entity> entityClass) {
      this.entityClass = entityClass;
   }
   
   public List<String> getFields(){
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

   public List<String> getTablePrefixedFields(String tableName) {
      Field[] fields = entityClass.getDeclaredFields();
      List<String> fieldNames = new ArrayList<String>();
      fieldNames.add(tableName + "." + Tables._ID);
      for (Field f : fields){
         fieldNames.add(tableName + "." + f.getName().toLowerCase());
      }
      return fieldNames;
   }
   
}
