package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

import com.gooble.logic.api.domain.FieldMap;
import com.gooble.logic.api.domain.FieldMapper;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.variables.VariablevalueAdapter;

public class VariablevalueDomain implements VariablevalueFacade{

   public List<Long> save(Context context, Long variableId, String variableType, List<Long> ids, List<String> values) {
      VariableAdapter varHelper = new VariableAdapter(context);
      Variable variable = varHelper.getById(variableId);
      variable.setType(variableType);
      varHelper.store(variable);
      
      VariablevalueAdapter helper = new VariablevalueAdapter(context);
      
      FieldMap valueMap = new FieldMap();
      valueMap.putStaticField(Tables.Variablevalue.VARIABLEID, variableId);
      valueMap.put(Tables.Variablevalue.VALUE, values);
      
      return new FieldMapper(helper).save(ids, valueMap);
   }

}
