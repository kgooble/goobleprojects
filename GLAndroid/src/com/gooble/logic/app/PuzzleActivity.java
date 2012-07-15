package com.gooble.logic.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PuzzleActivity extends Activity {
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.edit_puzzle);
      attachActivityLaunchingOnclickToButton(R.id.variables_button, VariablesActivity.class);
      attachActivityLaunchingOnclickToButton(R.id.relations_button, RelationsActivity.class);
   }

   private void attachActivityLaunchingOnclickToButton(int buttonId, final Class<? extends Activity> activityClass) {
      final Context context = this;
      Button button = (Button) findViewById(buttonId);
      button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            // TODO: Pass in, as extras, the puzzle ID so it knows what variables to load 
            startActivity(new Intent(context, activityClass));
         }
      });
   }
}
