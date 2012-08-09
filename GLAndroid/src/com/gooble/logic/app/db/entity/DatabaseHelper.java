package com.gooble.logic.app.db.entity;

import java.lang.reflect.InvocationTargetException;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.sql.CreateTableSql;
import com.gooble.logic.app.db.sql.DropTableSql;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.EntityFactory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseHelper<E extends Entity> extends SQLiteOpenHelper{

   private final EntityFactory<E> entityFactory;

   public DatabaseHelper(Context context, String name, CursorFactory factory, int version, EntityFactory<E> entityFactory) {
      super(context, name, factory, version);
      this.entityFactory = entityFactory;
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL(new CreateTableSql().generateFor(entityFactory.getEntityClass()));
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL(new DropTableSql().generateFor(entityFactory.getEntityClass()));
      onCreate(db);
   }
   
   public E get(int id){
      Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + entityFactory.getTableName() + " WHERE " + Tables._ID + " = " + id, null);
      
      if (cursor.getCount() == 0)
         return null;
      if (cursor.getCount() > 1)
         throw new RuntimeException("More than one " + entityFactory.getTableName() + " found with id " + id);
      
      cursor.moveToFirst();
      
      E e = entityFactory.create();
      e.setNew(false);
      setFieldsToEntity(e, id, cursor);
      return e;
   }

   private void setFieldsToEntity(E e, int id, Cursor cursor) {
      e.setId(id);
      for (String field : entityFactory.getFields()){
         int index = cursor.getColumnIndex(field);
         Class<?> type = entityFactory.getFieldType(field);
         
         Object value = null;
         if (type == Integer.TYPE){
            value = cursor.getInt(index);
         }
         if (type == Double.TYPE){
            value = cursor.getDouble(index);
         }
         if (type == String.class){
            value = cursor.getString(index);
         }
         e.setField(field, value);
      }
   }

}
