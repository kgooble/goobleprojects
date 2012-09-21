package com.gooble.logic.app.relations;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.R;
import com.gooble.logic.app.db.entity.ConditionAdapter;
import com.gooble.logic.app.db.entity.ConditionsetAdapter;
import com.gooble.logic.app.db.entity.PopinEntityListAdapter;
import com.gooble.logic.app.db.entity.RelationAdapter;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.entity.Condition;
import com.gooble.logic.app.entity.Conditionset;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Relation;
import com.gooble.logic.app.util.SpinnerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ConditionSetActivity extends Activity {

   private List<TableRow> conditionRows;

   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.condition_set);

      // TODO: Load all existing conditions for this condition set into
      // conditionRows
      conditionRows = new ArrayList<TableRow>();
      final Activity activity = this;
      final TableLayout layout = (TableLayout) findViewById(R.id.condition_set_layout);
      
      final Long conditionsetId = getIntent().getLongExtra("conditionsetid", -1);
      
      Conditionset conditionset = null;
      Long relationId = null;
      if (conditionsetId == -1){
         relationId = getIntent().getLongExtra("relationid", -1);
         conditionset = new Conditionset();
         conditionset.setRelationid(relationId);
      }else{
         conditionset = new ConditionsetAdapter(this).getById(conditionsetId);
         relationId = conditionset.getRelationid();
      }
      
      final Relation relation = new RelationAdapter(this).getById(relationId);
      
      final List<String> operators = new VariableAdapter(this).getById(relation.getVariableid()).getOperators();
      
      final EntityList<Condition> conditions = new ConditionAdapter(this).getAllForConditionSet(conditionset.getId());
      
      PopinEntityListAdapter adapter = new PopinEntityListAdapter(this, conditions, R.id.condition_set_layout, R.layout.condition_row, 
            new int[]{}, new OnClickListener[]{});
      adapter.registerContainer(new String[]{}, new int[]{R.id.operand_1, R.id.operator, R.id.operand_2});
      

      Button saveConditionsButton = (Button) findViewById(R.id.save_condition_set_button);
      //TODO Make it save- should then load on the other page
      saveConditionsButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.finish();
         }
      });
      
      Button addConditionButton = (Button) findViewById(R.id.add_condition_button);

      addConditionButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            if (!conditionRows.isEmpty()) {
               TextView and = new TextView(activity);
               and.setText(R.string.and_condition);
               and.setLayoutParams(new TableLayout.LayoutParams(
                     TableLayout.LayoutParams.WRAP_CONTENT,
                     TableLayout.LayoutParams.WRAP_CONTENT));
               layout.addView(and);
            }
            
            TableRow conditionRow = new TableRow(activity);
            conditionRow.setLayoutParams(new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT,
                  TableRow.LayoutParams.WRAP_CONTENT));
            
            //TODO: These would actually be facade calls to the domain for actual lists
            
            //TODO: populate with values for current variable + actual variable
            conditionRow.addView(createSpinnerWithValues("X", "Y", "Z", "bob"));
            
            //TODO: populate with actual valid operators
            // for strings, < and > don't work
            conditionRow.addView(createSpinnerWithValues(operators.toArray(new String[0])));
            
            //TODO: populate with values for current variable 
            conditionRow.addView(createSpinnerWithValues("bob"));
            
            layout.addView(conditionRow);
            
            conditionRows.add(conditionRow);
         }
      });
   }
   
   private void loadConditions(){
      // for a particular conditionset...
   }
   
   
   private Spinner createSpinnerWithValues(String... values){
      return new SpinnerFactory(this).createRowSpinnerWithValues(values);
   }
}
