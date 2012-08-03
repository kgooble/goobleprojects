package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gooble.logic.app.api.VariableDomain;
import com.gooble.logic.app.api.VariableFacade;
import com.gooble.logic.app.util.TableLayoutRow;

public class VariablesActivity extends Activity {
   
   private VariableFacade variableFacade;

   public VariablesActivity() {
      this.variableFacade = new VariableDomain();
   }
   
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.variables);
      
      /*
       * TODO: Load all existing variable names with Edit and Delete buttons next to them
       * TODO: need radio set which states which one is the main variable--- 
       *       or maybe this should go on the variable screen.
       */
      
      final Activity activity = this;
      Button addVariableButton = (Button) findViewById(R.id.add_variable_button);
      Button saveVariablesButton = (Button) findViewById(R.id.save_variables_button);
      saveVariablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableFacade.save();
            // TODO: maybe go back to previous activity here? Finish?
         }
      });
      
      addVariableButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            TableLayout layout = (TableLayout) findViewById(R.id.variable_layout);
            new TableLayoutRow(layout, activity).addRowLabeledWithTextField(getString(R.string.variable_name));
            TableRow.LayoutParams lparams = new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT);
            
            TableRow addValuesRow = new TableRow(activity);
            addValuesRow.setLayoutParams(lparams);
            
            /*
             * TODO: Onclick for this button should go to the Add Variable Values activity
             * set the clicked variable as the current variable that's being edited
             * 
             * it will also have to do the same thing as the "save" button
             *  (i.e save to DB all the variables for this puzzle)
             */
            Button addVariableValuesButton = new Button(activity);
            addVariableValuesButton.setText(getString(R.string.add_variable_values));
            addVariableValuesButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  variableFacade.save();
                  activity.startActivity(new Intent(activity, VariableValuesActivity.class));
               }
            });
            addValuesRow.addView(addVariableValuesButton, new TableRow.LayoutParams(
                  TableRow.LayoutParams.WRAP_CONTENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
            layout.addView(addValuesRow, new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
         }
      });
   }
}
