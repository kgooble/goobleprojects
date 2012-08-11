package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

import com.gooble.logic.api.domain.FieldMap;
import com.gooble.logic.api.domain.FieldMapper;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.VariableAdapter;

public class VariableDomain implements VariableFacade{

   public List<Long> save(Context context, Long puzzleId, List<Long> ids, List<String> names) {
      VariableAdapter helper = new VariableAdapter(context);
      
      FieldMap valueMap = new FieldMap();
      valueMap.put(Tables.Variable.NAME, names);
      valueMap.putStaticField(Tables.Variable.PUZZLEID, puzzleId);
      
      return new FieldMapper(helper).save(ids, valueMap);
   }

}
