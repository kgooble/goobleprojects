package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Relation;
import com.gooble.logic.app.entity.RelationFactory;

public class RelationAdapter extends EntityAdapter<Relation>{

   public RelationAdapter(Context context) {
      super(context, new RelationFactory());
   }

   public EntityList<Relation> getRelationsForPuzzle(Long puzzleId) {
      return getEntitiesMatchingIdField(Tables.Relation.PUZZLEID, puzzleId);
   }

}
