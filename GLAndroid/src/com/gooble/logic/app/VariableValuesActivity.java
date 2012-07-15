package com.gooble.logic.app;

import com.gooble.logic.app.util.TableLayoutRow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class VariableValuesActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.variable_values);
      
      /*
       * TODO: Load all existing values into table
       */
      
      /*
       * When adding a value, add a row with label "value" and text field to enter value
       */
      final Activity activity = this;
      Button addValueButton = (Button) findViewById(R.id.add_value_button);
      addValueButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            TableLayout layout = (TableLayout) findViewById(R.id.variable_values_layout);
            new TableLayoutRow(layout, activity).addRowLabeledWithTextField(getString(R.string.variable_value));
            //TODO: add delete button to row below? or same row?
         }
      });
      
      Button saveValuesButton = (Button) findViewById(R.id.save_values_button);
      saveValuesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            // TODO: save values
            
            // go back to prev activity
            activity.finish();
         }
      });
   }
}
