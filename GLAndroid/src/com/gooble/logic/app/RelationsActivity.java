package com.gooble.logic.app;

import com.gooble.logic.app.util.TableLayoutRow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class RelationsActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.relations);
      
      //TODO: Load all relations in a list with edit & delete buttons next to them
      
      final Activity activity = this;
      Button addRelationButton = (Button) findViewById(R.id.add_relation_button);
      
      addRelationButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            TableLayout layout = (TableLayout) findViewById(R.id.relations_layout);
            new TableLayoutRow(layout, activity).addRowLabeledWithTextField(getString(R.string.relation_name));
            TableRow.LayoutParams lparams = new TableRow.LayoutParams(
                  TableRow.LayoutParams.FILL_PARENT, 
                  TableRow.LayoutParams.WRAP_CONTENT);
            
            TableRow addValuesRow = new TableRow(activity);
            addValuesRow.setLayoutParams(lparams);
            
            /*
             * TODO: Reduce duplication between this activity & variables activity
             * 
             */
            Button addVariableValuesButton = new Button(activity);
            addVariableValuesButton.setText(getString(R.string.edit_relation));
            addVariableValuesButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  activity.startActivity(new Intent(activity, EditRelationActivity.class));
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
