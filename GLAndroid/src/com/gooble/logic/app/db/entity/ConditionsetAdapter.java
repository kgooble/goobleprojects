package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.Conditionset;
import com.gooble.logic.app.entity.ConditionsetFactory;
import com.gooble.logic.app.entity.EntityList;

public class ConditionsetAdapter extends EntityAdapter<Conditionset> {

   public ConditionsetAdapter(Context context) {
      super(context, new ConditionsetFactory());
   }

   public EntityList<Conditionset> getConditionsetsForRelation(long relationId) {
      return getEntitiesMatchingIdField(Tables.Conditionset.RELATIONID, relationId);
   }

}
