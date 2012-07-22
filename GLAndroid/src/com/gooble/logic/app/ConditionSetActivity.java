package com.gooble.logic.app;

import java.util.ArrayList;
import java.util.List;

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

      Button addConditionButton = (Button) findViewById(R.id.add_condition_button);

      final Activity activity = this;
      final TableLayout layout = (TableLayout) findViewById(R.id.condition_set_layout);
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
      Spinner operatorSpinner = new Spinner(this);
      SpinnerAdapter operatorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values); 
      operatorSpinner.setAdapter(operatorAdapter);
      operatorSpinner.setLayoutParams(new TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
         ));
      return operatorSpinner;
   }
}
