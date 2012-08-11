package com.gooble.logic.app.variables;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gooble.logic.api.VariableDomain;
import com.gooble.logic.api.VariableFacade;
import com.gooble.logic.app.R;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.EntityListAdapter;
import com.gooble.logic.app.db.entity.RowDeleteListener;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.util.ContainerColumns;

public class VariablesActivity extends Activity {
   
   private VariableFacade variableFacade;

   public VariablesActivity() {
      this.variableFacade = new VariableDomain();
   }
   
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.variables);
      final Long puzzleId = (Long) getIntent().getExtras().get("puzzleid");
      final VariableAdapter helper = new VariableAdapter(this);
      
      EntityList<Variable> variables = helper.getVariablesForPuzzle(puzzleId);
      final Activity activity = this;
      final EntityListAdapter entityListAdapter = new EntityListAdapter(this, variables, R.id.variable_container, R.layout.variable_row, 
         new int[]{R.id.edit_variable_button, R.id.delete_variable_button}, 
         new OnClickListener[]{
            new OnClickListener() {
               public void onClick(View v) {
                  List<Long> ids = new ContainerColumns(activity, R.id.variable_container).getIds();
                  List<String> names = new ContainerColumns(activity, R.id.variable_container).getStringsFromField(R.id.variable_name);
                  List<Long> newIds = variableFacade.save(activity, puzzleId, ids, names);
                  new ContainerColumns(activity, R.id.variable_container).updateIds(newIds);
                  
                  View variableRow = (View) v.getParent();
                  Intent intent = new Intent(activity, VariableValuesActivity.class);
                  Long variableId = (long) variableRow.getId();
                  intent.putExtra("variableid", variableId);
                  activity.startActivity(intent);
               }
            },
            new RowDeleteListener(helper)
      });
      entityListAdapter.registerContainer( 
            new String[]{Tables.Variable.NAME}, 
            new int[]{R.id.variable_name} 
      );
      
      /*
       * TODO: need radio set which states which one is the main variable--- 
       *       or maybe this should go on the variable screen.
       */
      
      Button addVariableButton = (Button) findViewById(R.id.add_variable_button);
      Button saveVariablesButton = (Button) findViewById(R.id.save_variables_button);
      saveVariablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            List<Long> ids = entityListAdapter.getIds();
            List<String> names = entityListAdapter.getStringsFromField(R.id.variable_name);
            variableFacade.save(activity, puzzleId, ids, names);
         }
      });
      
      addVariableButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            entityListAdapter.addNewEntity();
         }
      });
   }
}

