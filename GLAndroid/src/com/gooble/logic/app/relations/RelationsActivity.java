package com.gooble.logic.app.relations;

import com.gooble.logic.app.R;
import com.gooble.logic.app.R.id;
import com.gooble.logic.app.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class RelationsActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.relations);
      
      //TODO: Load all relations in a list with edit & delete buttons next to them
      
      final Activity activity = this;
      final LinearLayout layout = (LinearLayout) findViewById(R.id.relation_container);
      Button addRelationButton = (Button) findViewById(R.id.add_relation_button);
      
      addRelationButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            LayoutInflater inflater = getLayoutInflater();
            final View relation = inflater.inflate(R.layout.relation_row, layout, false);
            
            Button addRelationButton = (Button) relation.findViewById(R.id.edit_relation_button);
            addRelationButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  activity.startActivity(new Intent(activity, EditRelationActivity.class));
               }
            });
            Button deleteButton = (Button) relation.findViewById(R.id.delete_relation_button);
            deleteButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  layout.removeView(relation);
               }
            });
            
            layout.addView(relation);
         }
      });
   }
   
}
