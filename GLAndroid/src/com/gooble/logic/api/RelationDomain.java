package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

import com.gooble.logic.api.domain.FieldMap;
import com.gooble.logic.api.domain.FieldMapper;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.RelationAdapter;

public class RelationDomain implements RelationFacade {

   public List<Long> save(Context context, Long puzzleId, List<Long> ids, List<String> names) {
      RelationAdapter helper = new RelationAdapter(context);
      
      FieldMap valueMap = new FieldMap();
      valueMap.put(Tables.Relation.NAME, names);
      valueMap.putStaticField(Tables.Relation.PUZZLEID, puzzleId);
      
      return new FieldMapper(helper).save(ids, valueMap);
   }

}
