package com.gooble.logic.app.hints;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.gooble.logic.app.R;
import com.gooble.logic.app.util.SpinnerFactory;

public class HintVariablesActivity extends Activity {
   private int thingToBeBelow;

   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.hint_variables);
      
      Bundle extras = getIntent().getExtras();
      //TODO: get the hint from the extras
      //TODO: load variables currently in play - value-type pairs
      //TODO: add save button functionality
      final RelativeLayout layout = (RelativeLayout) findViewById(R.id.hint_variables_layout);
      thingToBeBelow = R.id.save_hint_variables_button;
      for (int i = 0; i < 3; i++){
         addVariable(layout, i, "X"+i);
      }
      
      Button addButton = (Button) findViewById(R.id.add_variable_button);
      addButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            addVariable(layout, 0, "Xnew");            
         }
      });
   }

   private void addVariable(RelativeLayout layout, int i, String value) {
      EditText nameField = new EditText(this);
      Spinner variableTypes = new SpinnerFactory(this).createSpinnerWithValues("TypeA" + i, "TypeAB" + i);
      variableTypes.setId(variableTypes.hashCode());
      
      nameField.setText(value);
      nameField.setId(nameField.hashCode()); //TODO Set Id to something better
      RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
      params.addRule(RelativeLayout.BELOW, thingToBeBelow);
      params.addRule(RelativeLayout.ALIGN_BASELINE, variableTypes.getId());
      params.addRule(RelativeLayout.ALIGN_BOTTOM, variableTypes.getId());
      nameField.setLayoutParams(params);
      
      RelativeLayout.LayoutParams spinnerParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
         );
      spinnerParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
      spinnerParams.addRule(RelativeLayout.BELOW, thingToBeBelow);
      //variableTypes.setSelected(...) //TODO set selected
      variableTypes.setLayoutParams(spinnerParams);
      
      layout.addView(nameField);
      layout.addView(variableTypes);
      thingToBeBelow = variableTypes.getId();
   }
}
