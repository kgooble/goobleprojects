package com.gooble.logic.app.db.sql;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.util.StringUtils;

public class CreateTableSql {

   private final static MessageFormat CREATE_TABLE_SQL = 
         new MessageFormat("CREATE TABLE {0} ({1});");
   
   public String generateFor(Class<? extends Entity> entityClass) {
      Field[] fields = entityClass.getDeclaredFields();
      
      List<String> fieldSqls = new ArrayList<String>();
      fieldSqls.add(Tables._ID + " INTEGER PRIMARY KEY");
      for (Field f : fields){
         addSqlForColumn(fieldSqls, f);
      }
      
      return CREATE_TABLE_SQL.format(new Object[]{entityClass.getSimpleName().toLowerCase(), StringUtils.join(", ", fieldSqls)});
   }

   private void addSqlForColumn(List<String> fieldSqls, Field f) {
      String fieldName = f.getName().toLowerCase();
      String fieldTypeSql = "";
      Class<?> fieldType = f.getType();
      if (fieldType == Integer.class || fieldType == Long.class){
         fieldTypeSql = "INTEGER";
      }else if (fieldType == String.class){
         fieldTypeSql = "TEXT";
      }else if (fieldType == Double.class){
         fieldTypeSql = "REAL";
      }else{
         return;
      }
      fieldSqls.add(fieldName + " " + fieldTypeSql);
   }

}
