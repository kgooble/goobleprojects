package com.gooble.logic.api;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.entity.Variablevalue;
import com.gooble.logic.app.variables.VariablevalueAdapter;

import android.content.Context;

public class VariablevalueDomain implements VariablevalueFacade{

   public List<Long> save(Context context, Long variableId, List<Long> ids, List<String> values) {
      // TODO: extract pattern from this & variabledomain
      // create adapter for entity
      // for each id - if entity exists, set value (some column)- could be arbitrary number of columns
      // if it doesn't exist, set (1) link field (2) some column (same column as above)
      // accumulate new Ids for new entities
      VariablevalueAdapter helper = new VariablevalueAdapter(context);
      List<Long> newIds = new ArrayList<Long>();
      for (int i = 0; i < ids.size(); i++){
         Long id = ids.get(i);
         String value = values.get(i);
         Variablevalue varVal = helper.getById(id);
         if (varVal == null) {
            varVal = helper.create();
            varVal.setVariableid(variableId);
            varVal.setValue(value);
         } else {
            varVal.setValue(value);
         }
         newIds.add(varVal.getId());
         helper.store(varVal);
      }
      return newIds;
   }

}
