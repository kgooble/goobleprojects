package com.gooble.logic.app.db.sql;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.util.StringUtils;

public class CreateTableSql {

   private final static MessageFormat CREATE_TABLE_SQL = 
         new MessageFormat("CREATE TABLE {0} ({1});");
   
   private static final Map<Class<?>, String> CLASS_TO_SQL_KEYWORD;
   static{
      CLASS_TO_SQL_KEYWORD = new HashMap<Class<?>, String>();
      CLASS_TO_SQL_KEYWORD.put(Integer.class, "INTEGER");
      CLASS_TO_SQL_KEYWORD.put(Long.class, "INTEGER");
      CLASS_TO_SQL_KEYWORD.put(String.class, "TEXT");
      CLASS_TO_SQL_KEYWORD.put(Double.class, "REAL");
   }
   
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
      Class<?> fieldType = f.getType();
      if (!CLASS_TO_SQL_KEYWORD.containsKey(fieldType)){
         return;
      }
      String fieldTypeSql = CLASS_TO_SQL_KEYWORD.get(fieldType);
      fieldSqls.add(fieldName + " " + fieldTypeSql);
   }

}
