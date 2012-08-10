package com.gooble.logic.app.db.entity;

import android.content.Context;
import android.database.Cursor;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.EntityFactory;

public abstract class EntityAdapter<E extends Entity> {

   protected final EntityFactory<E> factory;
   protected final DatabaseHelper helper;

   public EntityAdapter(Context context, EntityFactory<E> factory) {
      this.helper = new DatabaseHelper(context);
      this.factory = factory;
   }

   public E getById(Long id) {
      Cursor cursor = helper.getById(id, factory.getTableName());
      if (cursor.getCount() == 0)
         return null;
      return loadEntity(id, cursor);
   }

   public Cursor getAll() {
      return helper.getAll(factory.getTableName());
   }

   public void store(E e) {
      helper.store(e);
   }

   public void delete(Long id) {
      if (id == null)
         return;
      helper.deleteRow(factory.getTableName(), id);
   }

   public E create() {
      return factory.createNew();
   }

   protected E loadEntity(Cursor cursor) {
      int idIndex = cursor.getColumnIndex(Tables._ID);
      return loadEntity(cursor.getLong(idIndex), cursor);
   }

   protected E loadEntity(long id, Cursor cursor) {
      E e = factory.createNew();
      e.setNew(false);
      setFieldsToEntity(e, id, cursor);
      return e;
   }

   private void setFieldsToEntity(E e, long id, Cursor cursor) {
      e.setId(id);
      for (String field : factory.getFields()) {
         int index = cursor.getColumnIndex(field);
         Class<?> type = factory.getFieldType(field);

         Object value = null;
         if (type == Integer.class) {
            value = cursor.getInt(index);
         }
         if (type == Long.class) {
            value = cursor.getLong(index);
         }
         if (type == Double.class) {
            value = cursor.getDouble(index);
         }
         if (type == String.class) {
            value = cursor.getString(index);
         }
         if (type == Boolean.class) {
            value = cursor.getInt(index) == 1;
         }
         e.setField(field, value);
      }
   }

}
