package com.gooble.logic.app.db.entity;

import android.content.Context;
import android.database.Cursor;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.entity.VariableFactory;

public class VariableAdapter extends EntityAdapter<Variable>{

   public VariableAdapter(Context context) {
      super(context, new VariableFactory());
   }

   public EntityList<Variable> getVariablesForPuzzle(Long puzzleId) {
      Cursor result = helper.getReadableDatabase().rawQuery("SELECT * FROM " + factory.getTableName() + " WHERE " + Tables.Variable.PUZZLEID + " = " + puzzleId, null);
      EntityList<Variable> list = new EntityList<Variable>();
      while (result.moveToNext()){
         list.add(loadEntity(result));
      }
      return list;
   }

}
