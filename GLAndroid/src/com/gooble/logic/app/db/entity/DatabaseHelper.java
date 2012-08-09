package com.gooble.logic.app.db.entity;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.sql.CreateTableSql;
import com.gooble.logic.app.db.sql.DropTableSql;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.EntityFactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper<E extends Entity> extends SQLiteOpenHelper{

   private static final int DATABASE_VERSION = 1;
   private final EntityFactory<E> entityFactory;

   public DatabaseHelper(Context context, EntityFactory<E> entityFactory) {
      super(context, entityFactory.getTableName(), null, DATABASE_VERSION);
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
   
   public E create(){
      E e = entityFactory.create();
      e.setNew(true);
      return e;
   }
   
   public Cursor getAll(){
      return getReadableDatabase().rawQuery("SELECT * FROM " + entityFactory.getTableName(), null);
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
         if (type == Integer.class){
            value = cursor.getInt(index);
         }
         if (type == Double.class){
            value = cursor.getDouble(index);
         }
         if (type == String.class){
            value = cursor.getString(index);
         }
         if (type == Boolean.class){
            value = cursor.getInt(index) == 1;
         }
         e.setField(field, value);
      }
   }

   public void store(E entity){
      if (entity.isNew()){
         insert(entity);
      }else{
         update(entity);
      }
   }
   
   public void insert(E entity) {
      ContentValues values = new ContentValues();
      Iterable<String> fields = entityFactory.getFields();
      for (String field : fields){
         Class<?> type = entityFactory.getFieldType(field);
         if (type == Integer.class){
            values.put(field, (Integer) entity.getField(field));
         }
         if (type == Double.class){
            values.put(field, (Double) entity.getField(field));
         }
         if (type == String.class){
            values.put(field, (String) entity.getField(field));
         }
         if (type == Boolean.class){
            values.put(field, (Integer) entity.getField(field));
         }
      }
      long id = getWritableDatabase().insert(entityFactory.getTableName(), null, values);
      entity.setId(id);
   }

   public void update(E entity) {
      
   }

}
