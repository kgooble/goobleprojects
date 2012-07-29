package com.gooble.logic.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HintsActivity extends Activity {
   private int thingToBeBelow;

   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.hints);
      
      //TODO: load all existing hints [short name, edit button]
      
      final RelativeLayout layout = (RelativeLayout) findViewById(R.id.hints_layout);
      
      thingToBeBelow = R.id.add_hint_button;
      
      // TODO: Load hints as Labels with Edit buttons next to them
      // TODO: reduce duplication between this & edit relation activity 
      for (int i = 0; i < 3; i++){
         thingToBeBelow = addHint(layout, thingToBeBelow, "Hint " + i);
      }
      
      Button addHintButton = (Button) findViewById(R.id.add_hint_button);
      addHintButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            thingToBeBelow = addHint(layout, thingToBeBelow, "New Hint");
         }
      });
   }

   private int addHint(RelativeLayout layout, int thingToBeBelow, String hintStr) {
      TextView hintLabel = new TextView(this);
      hintLabel.setId(hintLabel.hashCode());
      
      Button editButton = new Button(this);
      editButton.setId(editButton.hashCode());
      
      hintLabel.setText(hintStr);
      RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
            );
      params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
      params.addRule(RelativeLayout.BELOW, thingToBeBelow);
      params.addRule(RelativeLayout.ALIGN_BASELINE, editButton.getId());
      params.addRule(RelativeLayout.ALIGN_BOTTOM, editButton.getId());
      hintLabel.setLayoutParams(params);
      
      editButton.setText(R.string.edit);
      RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
            );
      buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
      buttonParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
      editButton.setLayoutParams(buttonParams);
      
      layout.addView(hintLabel);
      layout.addView(editButton);
      
      thingToBeBelow = editButton.getId();
      return thingToBeBelow;
   }
}
