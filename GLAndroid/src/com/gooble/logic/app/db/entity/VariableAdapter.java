package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.entity.VariableFactory;

public class VariableAdapter extends EntityAdapter<Variable>{

   public VariableAdapter(Context context) {
      super(context, new VariableFactory());
   }
   
   public EntityList<Variable> getVariablesForPuzzle(Long puzzleId) {
      return getEntitiesMatchingIdField(Tables.Variable.PUZZLEID, puzzleId);
   }

}
