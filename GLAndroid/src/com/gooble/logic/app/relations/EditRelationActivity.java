package com.gooble.logic.app.relations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.gooble.logic.app.R;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.ConditionsetAdapter;
import com.gooble.logic.app.db.entity.PopinEntityListAdapter;
import com.gooble.logic.app.db.entity.RelationAdapter;
import com.gooble.logic.app.db.entity.RowDeleteListener;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.entity.Conditionset;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Relation;
import com.gooble.logic.app.entity.Variable;

public class EditRelationActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.edit_relation);

      final Long relationId = getIntent().getLongExtra("relationid", -1);
      final RelationAdapter relationAdapter = new RelationAdapter(this);
      final Relation relation = relationAdapter.getById(relationId);
      EntityList<Variable> variables = new VariableAdapter(this).getVariablesForPuzzle(relation.getPuzzleid());

      //Set the current relation's name in the name field.
      TextView relationNameLabel = (TextView) findViewById(R.id.relation_name);
      relationNameLabel.setText(relation.getName());

      
      final Spinner variableSpinner = (Spinner) findViewById(R.id.relation_variable_list);
      List<?> variableNames = variables.getColumn(Tables.Variable.NAME);
      ArrayAdapter<String> varArrayAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, variableNames.toArray(new String[variableNames.size()]));
      variableSpinner.setAdapter(varArrayAdapter);
      variableSpinner.setSelection(variables.findPosition(Tables._ID, relation.getVariableid()));

      listenForAddConditionset(relationId);
      loadConditionsets(relation, variableSpinner);

      final Map<String, Long> nameToVarId = new HashMap<String, Long>();
      for (int i = 0; i < variables.size(); i++){
         String name = (String) variableNames.get(i);
         nameToVarId.put(name, variables.get(i).getId());
      }
      Button saveButton = (Button) findViewById(R.id.save_relation_button);
      saveButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            relation.setVariableid(nameToVarId.get(((TextView)variableSpinner.getSelectedView()).getText().toString()));
            relationAdapter.store(relation);
         }
      });
   }

   private void loadConditionsets(final Relation relation, final Spinner variableSpinner) {
      final Activity activity = this;
      ConditionsetAdapter conditionsetAdapter = new ConditionsetAdapter(this);
      EntityList<Conditionset> conditionSets = conditionsetAdapter.getConditionsetsForRelation(relation.getId());
      final PopinEntityListAdapter entityListAdapter = new PopinEntityListAdapter(this, conditionSets, R.id.conditionset_container, R.layout.conditionset_row, 
            new int[]{R.id.edit_conditionset_button, R.id.delete_conditionset_button}, 
            new OnClickListener[]{new OnClickListener() {
               public void onClick(View v) {
                  relation.setVariableid(variableSpinner.getSelectedItemId());
                  new RelationAdapter(activity).store(relation);
                  
                  //TODO: This same logic is repeated in at least VariablesActivity. extract
                  View conditionsetRow = (View) v.getParent();
                  Intent intent = new Intent(activity, ConditionSetActivity.class);
                  Long conditionsetId = (long) conditionsetRow.getId();
                  intent.putExtra("conditionsetid", conditionsetId);
                  activity.startActivity(intent);
               }
            },
            new RowDeleteListener(conditionsetAdapter)
      });

      entityListAdapter.registerContainer(
            new String[]{Tables.Conditionset.NAME}, 
            new int[]{R.id.conditionset_label});
   }

   private void listenForAddConditionset(final Long relationId) {
      final Activity activity = this;
      Button addConditionSetButton = (Button) findViewById(R.id.add_condition_set_button);
      addConditionSetButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            Intent intent = new Intent(activity, ConditionSetActivity.class);
            intent.putExtra("relationid", relationId);
            activity.startActivity(intent);
         }
      });
   }

}
