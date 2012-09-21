package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Condition;
import com.gooble.logic.app.entity.ConditionFactory;
import com.gooble.logic.app.entity.EntityList;

public class ConditionAdapter extends EntityAdapter<Condition> {

   public ConditionAdapter(Context context) {
      super(context, new ConditionFactory());
   }

   public EntityList<Condition> getAllForConditionSet(long conditionsetId) {
      return getEntitiesMatchingIdField(Tables.Condition.CONDITIONSETID, conditionsetId);
   }

}
