package com.gooble.logic.app.hints;

import com.gooble.logic.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EditHintActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.edit_hint);
      
      final Activity activity = this;
      
      //TODO: make sure to pass the Hint to these activities,
      // so they can load their data
      Button variablesButton = (Button) findViewById(R.id.edit_variables_button);
      variablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.startActivity(new Intent(activity, HintVariablesActivity.class));
         }
      });
      
      Button propertyLinksButton = (Button) findViewById(R.id.edit_property_links_button);
      propertyLinksButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.startActivity(new Intent(activity, PropertyLinksActivity.class));
         }
      });
      
      Button relationsButton = (Button) findViewById(R.id.edit_relations_button);
      relationsButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            activity.startActivity(new Intent(activity, HintRelationsActivity.class));
         }
      });
   }
   
}
