package com.gooble.logic.app.db.entity;

import android.content.Context;
import android.database.Cursor;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.EntityFactory;
import com.gooble.logic.app.entity.EntityList;

public abstract class EntityAdapter<E extends Entity> {

   protected final EntityFactory<E> factory;
   protected final DatabaseHelper helper;

   public EntityAdapter(Context context, EntityFactory<E> factory) {
      this.helper = new DatabaseHelper(context);
      this.factory = factory;
   }

   public E getById(Long id) {
      Cursor cursor = helper.getById(id, factory);
      if (!cursor.moveToNext())
         return null;
      E entity = loadEntity(id, cursor);
      cursor.close();
      return entity;
   }

   public Cursor getAll() {
      return helper.getAll(factory);
   }

   public void store(Entity e) {
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
   
   protected EntityList<E> getEntitiesMatchingIdField(String idField, Long id){
      Cursor result = helper.getReadableDatabase().query(factory.getTableName(), factory.getTablePrefixedFields(), idField + " = " + id, null, null, null, null);
      return loadList(result);
   }

   protected EntityList<E> loadList(Cursor cursor) {
      EntityList<E> list = new EntityList<E>();
      while (cursor.moveToNext()){
         list.add(loadEntity(cursor));
      }
      cursor.close();
      return list;
   }

   private void setFieldsToEntity(E e, long id, Cursor cursor) {
      e.setId(id);
      for (String field : factory.getFields()) {
         int index = cursor.getColumnIndex(field);
         Class<?> type = factory.getFieldType(field);

         //TODO : this logic appears in two other places...
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
