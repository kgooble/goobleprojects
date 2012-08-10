package com.gooble.logic.app.db.entity;

import com.gooble.logic.app.db.AllTables;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DatabaseHelper extends SQLiteOpenHelper{

   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "GoobleLogicDatabase";

   public DatabaseHelper(Context context) {
      super(context, TABLE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      new AllTables().create(db);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      new AllTables().drop(db);
      onCreate(db);
   }
   
   public Cursor getAll(String tableName){
      return getReadableDatabase().rawQuery("SELECT * FROM " + tableName, null);
   }
   
   public Cursor getById(long id, String tableName){
      Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " WHERE " + Tables._ID + " = " + id, null);
      if (cursor.getCount() > 1)
         throw new RuntimeException("More than one " + tableName + " found with id " + id);
      
      return cursor;
   }

   public void store(Entity entity){
      if (entity.isNew()){
         insert(entity);
      }else{
         update(entity);
      }
   }
   
   public void insert(Entity entity) {
      ContentValues values = createContentValuesFromFields(entity);
      long id = getWritableDatabase().insert(entity.getTableName(), null, values);
      entity.setId(id);
   }

   public void update(Entity entity) {
      ContentValues values = createContentValuesFromFields(entity);
      getWritableDatabase().update(entity.getTableName(), values, Tables._ID + " = " + entity.getId(), null);
   }

   private ContentValues createContentValuesFromFields(Entity entity) {
      ContentValues values = new ContentValues();
      Iterable<String> fields = entity.getFields();
      for (String field : fields){
         Class<?> type = entity.getFieldType(field);
         if (type == Integer.class){
            values.put(field, (Integer) entity.getField(field));
         }
         if (type == Long.class){
            values.put(field, (Long) entity.getField(field));
         }
         if (type == Double.class){
            values.put(field, (Double) entity.getField(field));
         }
         if (type == String.class){
            values.put(field, (String) entity.getField(field));
         }
         if (type == Boolean.class){
            Boolean value = (Boolean) entity.getField(field);
            values.put(field, value? 1 : 0);
         }
      }
      return values;
   }
}
