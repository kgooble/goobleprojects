package com.gooble.logic.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class VariableValuesActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.variable_values);
      
      String name = (String) getIntent().getExtras().get("name");
      TextView nameLabel = (TextView) findViewById(R.id.variable_name_label);
      nameLabel.setText(name);
      /*
       * TODO: Load all existing values into table
       */
      
      final Activity activity = this;
      final LinearLayout container = (LinearLayout) findViewById(R.id.value_container);
      Button addValueButton = (Button) findViewById(R.id.add_value_button);
      addValueButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            LayoutInflater inflater = getLayoutInflater();
            final View newValue = inflater.inflate(R.layout.value_row, container, false);
            
            Button deleteButton = (Button) newValue.findViewById(R.id.delete_value_button);
            deleteButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  container.removeView(newValue);
               }
            });
            
            container.addView(newValue);
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
