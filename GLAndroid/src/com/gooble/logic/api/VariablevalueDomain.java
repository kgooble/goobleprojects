package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

import com.gooble.logic.api.domain.FieldMap;
import com.gooble.logic.api.domain.FieldMapper;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.PuzzleAdapter;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.db.entity.VariablevalueAdapter;
import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.entity.Variable;

public class VariablevalueDomain implements VariablevalueFacade{

   public List<Long> save(Context context, Long variableId, String variableType, boolean isMainVar, List<Long> ids, List<String> values) {
      Variable variable = updateVariableType(context, variableId, variableType);
      if (isMainVar){
         setMainVariable(context, variable);
      }
      return saveVariablevalues(context, variableId, ids, values);
   }

   private Variable updateVariableType(Context context, Long variableId, String variableType) {
      VariableAdapter varHelper = new VariableAdapter(context);
      Variable variable = varHelper.getById(variableId);
      variable.setType(variableType);
      varHelper.store(variable);
      return variable;
   }

   private void setMainVariable(Context context, Variable variable) {
      PuzzleAdapter puzzleHelper = new PuzzleAdapter(context);
      Puzzle thePuzzle = puzzleHelper.getById(variable.getPuzzleid());
      thePuzzle.setMainvariableid(variable.getId());
      puzzleHelper.store(thePuzzle);
   }
   
   private List<Long> saveVariablevalues(Context context, Long variableId, List<Long> ids, List<String> values) {
      VariablevalueAdapter helper = new VariablevalueAdapter(context);
      
      FieldMap valueMap = new FieldMap();
      valueMap.putStaticField(Tables.Variablevalue.VARIABLEID, variableId);
      valueMap.put(Tables.Variablevalue.VALUE, values);
      
      return new FieldMapper(helper).save(ids, valueMap);
   }

}
