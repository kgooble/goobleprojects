package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.gooble.logic.app.api.VariableDomain;
import com.gooble.logic.app.api.VariableFacade;

public class VariablesActivity extends Activity {
   
   private VariableFacade variableFacade;

   public VariablesActivity() {
      this.variableFacade = new VariableDomain();
   }
   
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.variables);
      
      final Activity activity = this;
      Button addVariableButton = (Button) findViewById(R.id.add_variable_button);
      Button saveVariablesButton = (Button) findViewById(R.id.save_variables_button);
      saveVariablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableFacade.save();
         }
      });
      
      addVariableButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            TableLayout layout = (TableLayout) findViewById(R.id.variable_layout);
            TableRow.LayoutParams lparams = new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT);
            
            TableRow nameValueRow = new TableRow(activity);
            TableRow addValuesRow = new TableRow(activity);
            nameValueRow.setLayoutParams(lparams);
            addValuesRow.setLayoutParams(lparams);
            
            TextView name = new TextView(activity);
            name.setText(getString(R.string.variable_name));
            name.setLayoutParams(lparams);
            
            EditText edit = new EditText(activity);
            edit.setLayoutParams(lparams);
            
            /*
             * Onclick for this button should go to the Add Variable Values activity
             * set the clicked variable as the current variable that's being edited
             * 
             * it will also have to do the same thing as the "save" button
             */
            Button addVariableValuesButton = new Button(activity);
            addVariableValuesButton.setText(getString(R.string.add_variable_values));
            addVariableValuesButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  variableFacade.save();
                  activity.startActivity(new Intent(activity, VariableValuesActivity.class));
               }
            });
            
            nameValueRow.addView(name, new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
            nameValueRow.addView(edit, new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            addValuesRow.addView(addVariableValuesButton, new TableRow.LayoutParams(
                  TableRow.LayoutParams.WRAP_CONTENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
            
            layout.addView(nameValueRow, new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
            layout.addView(addValuesRow, new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT));
         }
      });
   }
}
