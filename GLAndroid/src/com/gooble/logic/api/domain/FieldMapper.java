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
   
   public List<Long> save(List<Long> ids, FieldMap values){
      List<Long> newIds = new ArrayList<Long>();
      for (int i = 0; i < ids.size(); i++){
         Long id = ids.get(i);
         
         Entity e = adapter.getById(id);
         if (e == null)
            e = adapter.create();
         
         for (String field : values.keySet()){
            Object value = values.get(field, i);
            e.setField(field, value);
         }
         
         adapter.store(e);
         newIds.add(e.getId());
      }
      
      return newIds;
   }
   
}
