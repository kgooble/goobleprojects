package com.gooble.logic.app;

import com.gooble.logic.app.util.SpinnerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class PropertyLinksActivity extends Activity {
   private int thingToBeBelow;

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.property_links);
      
      final RelativeLayout layout = (RelativeLayout) findViewById(R.id.property_links_layout);
      thingToBeBelow = R.id.save_property_links_button;
      for (int i = 0; i < 3; i++){
         addPropertyLink(layout);
      }
      
      Button addButton = (Button) findViewById(R.id.add_property_link_button);
      addButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            addPropertyLink(layout);            
         }
      });
   }

   private void addPropertyLink(RelativeLayout layout) {
      //two spinners
      Spinner firstVariable = new SpinnerFactory(this).createSpinnerWithValues("X(human)", "Y(shoe)");
      firstVariable.setId(firstVariable.hashCode());
      Spinner secondVariable = new SpinnerFactory(this).createSpinnerWithValues("D(dress)", "Z(human)");
      secondVariable.setId(secondVariable.hashCode());
      
      RelativeLayout.LayoutParams firstParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      firstParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
      firstParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
      firstVariable.setLayoutParams(firstParams);
      
      RelativeLayout.LayoutParams secondParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      secondParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
      secondParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
      secondVariable.setLayoutParams(secondParams);
      
      layout.addView(firstVariable);
      layout.addView(secondVariable);
      thingToBeBelow = firstVariable.getId();
   }
}
