package com.gooble.logic.app.relations;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.app.R;
import com.gooble.logic.app.R.id;
import com.gooble.logic.app.R.layout;
import com.gooble.logic.app.R.string;
import com.gooble.logic.app.util.SpinnerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
            conditionRow.addView(createSpinnerWithValues(">", "<", "="));
            
            //TODO: populate with values for current variable 
            conditionRow.addView(createSpinnerWithValues("bob"));
            
            layout.addView(conditionRow);
            
            conditionRows.add(conditionRow);
         }
      });
   }
   
   private Spinner createSpinnerWithValues(String... values){
      return new SpinnerFactory(this).createRowSpinnerWithValues(values);
   }
}
