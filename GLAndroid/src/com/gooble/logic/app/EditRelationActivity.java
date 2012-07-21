package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditRelationActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.edit_relation);
      
      Spinner spinner = (Spinner) findViewById(R.id.relation_variable_list);
      
      //Set the current relation's name in the name field.
      
      //TODO: Load this list from the puzzle's variables
      ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, new String[] { "name",
                  "shoes", "age" });
      spinner.setAdapter(aa);
      
      final Activity activity = this;
      Button addConditionSetButton = (Button) findViewById(R.id.add_condition_set_button);
      
      addConditionSetButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.startActivity(new Intent(activity, ConditionSetActivity.class));
         }
      });
      // TODO: Load condition sets as Labels with Edit buttons next to them
   }
   
}
