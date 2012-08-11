package com.gooble.logic.api.domain;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.db.entity.EntityAdapter;
import com.gooble.logic.app.entity.Entity;

public class FieldMapper {

   private final EntityAdapter<?> adapter;

   public FieldMapper(EntityAdapter<?> adapter) {
      this.adapter = adapter;
   }

   public List<Long> save(List<Long> ids, FieldMap valueMap) {
      List<Long> newIds = new ArrayList<Long>();
      for (int i = 0; i < ids.size(); i++) {
         Entity e = getEntity(ids.get(i));
         setValues(valueMap, i, e);
         finish(newIds, e);
      }
      return newIds;
   }

   private Entity getEntity(Long id) {
      Entity e = adapter.getById(id);
      if (e == null)
         e = adapter.create();
      return e;
   }

   private void setValues(FieldMap values, int i, Entity e) {
      for (String field : values.keySet()) {
         Object value = values.get(field, i);
         e.setField(field, value);
      }
   }

   private void finish(List<Long> newIds, Entity e) {
      adapter.store(e); // store sets the id for the entity if it is new.
      newIds.add(e.getId());
   }

}
