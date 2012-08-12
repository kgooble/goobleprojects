package com.gooble.logic.app.db.entity;

import android.content.Context;
import android.database.Cursor;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variablevalue;
import com.gooble.logic.app.entity.VariablevalueFactory;

public class VariablevalueAdapter extends EntityAdapter<Variablevalue> {

   public VariablevalueAdapter(Context context) {
      super(context, new VariablevalueFactory());
   }
   
   public EntityList<Variablevalue> getForVariable(Long variableId) {
      Cursor result = helper.getReadableDatabase().rawQuery("SELECT * FROM " + factory.getTableName() + " WHERE " + Tables.Variablevalue.VARIABLEID + " = " + variableId, null);
      EntityList<Variablevalue> list = new EntityList<Variablevalue>();
      while (result.moveToNext()){
         list.add(loadEntity(result));
      }
      return list;
   }


}
