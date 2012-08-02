package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class EditRelationActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.edit_relation);
      
      Spinner spinner = (Spinner) findViewById(R.id.relation_variable_list);
      
      //Set the current relation's name in the name field.
      
      //TODO: Load this list from the puzzle's variables
      ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, new String[] { "name",
                  "shoes", "age" });
      spinner.setAdapter(aa);
      
      final Activity activity = this;
      Button addConditionSetButton = (Button) findViewById(R.id.add_condition_set_button);
      
      addConditionSetButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.startActivity(new Intent(activity, ConditionSetActivity.class));
         }
      });
      
      RelativeLayout layout = (RelativeLayout) findViewById(R.id.edit_relation_layout);
      int thingToBeBelow = R.id.save_relation_button;
      
      // TODO: Load condition sets as Labels with Edit buttons next to them
      for (int i = 0; i < 3; i++){
         TextView conditionLabel = new TextView(this);
         conditionLabel.setId(conditionLabel.hashCode());
         
         Button editButton = new Button(this);
         editButton.setId(editButton.hashCode());
         
         conditionLabel.setText("Condition text " + i);
         RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
               RelativeLayout.LayoutParams.WRAP_CONTENT,
               RelativeLayout.LayoutParams.WRAP_CONTENT
               );
         params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
         params.addRule(RelativeLayout.BELOW, thingToBeBelow);
         params.addRule(RelativeLayout.ALIGN_BASELINE, editButton.getId());
         params.addRule(RelativeLayout.ALIGN_BOTTOM, editButton.getId());
         conditionLabel.setLayoutParams(params);
         
         editButton.setText(R.string.edit);
         RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
               RelativeLayout.LayoutParams.WRAP_CONTENT,
               RelativeLayout.LayoutParams.WRAP_CONTENT
               );
         buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
         buttonParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
         editButton.setLayoutParams(buttonParams);
         
         layout.addView(conditionLabel);
         layout.addView(editButton);
         
         thingToBeBelow = editButton.getId();
      }
   }
   
}
