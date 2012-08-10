package com.gooble.logic.app.hints;

import com.gooble.logic.app.R;
import com.gooble.logic.app.util.SpinnerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class HintRelationsActivity extends Activity {
   private int thingToBeBelow;

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.hint_relations);
      
      final RelativeLayout layout = (RelativeLayout) findViewById(R.id.hint_relations_layout);
      thingToBeBelow = R.id.save_relations_button;
      for (int i = 0; i < 2; i++){
         addRelation(layout);
      }
      
      Button addButton = (Button) findViewById(R.id.add_relation_button);
      addButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            addRelation(layout);            
         }
      });
   }

   private void addRelation(RelativeLayout layout) {
      // 3 spinners - VAR REL VAR
      Spinner firstVariable = new SpinnerFactory(this).createSpinnerWithValues("X(human)", "W(human)");
      firstVariable.setId(firstVariable.hashCode());
      Spinner relationList = new SpinnerFactory(this).createSpinnerWithValues("hates", "likes");
      relationList.setId(relationList.hashCode());
      Spinner secondVariable = new SpinnerFactory(this).createSpinnerWithValues("Y(human)", "Z(human)");
      secondVariable.setId(secondVariable.hashCode());
      
      RelativeLayout.LayoutParams firstParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      firstParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
      firstParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
      firstVariable.setLayoutParams(firstParams);
      
      RelativeLayout.LayoutParams relationParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      relationParams.addRule(RelativeLayout.BELOW, firstVariable.getId());
      relationList.setLayoutParams(relationParams);
      
      RelativeLayout.LayoutParams secondParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      secondParams.addRule(RelativeLayout.BELOW, relationList.getId());
      secondVariable.setLayoutParams(secondParams);
      
      TextView separator = new TextView(this);
      separator.setId(separator.hashCode());
      separator.setText(getString(R.string.and_condition));
      RelativeLayout.LayoutParams separatorParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      separatorParams.addRule(RelativeLayout.BELOW, secondVariable.getId());
      separator.setLayoutParams(separatorParams);
      
      layout.addView(firstVariable);
      layout.addView(relationList);
      layout.addView(secondVariable);
      layout.addView(separator);
      thingToBeBelow = separator.getId();
   }
}
