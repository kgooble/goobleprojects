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
      
      final Context context = this;
      Button variables = (Button) findViewById(R.id.variables_button);
      
      variables.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            startActivity(new Intent(context, VariablesActivity.class));
         }
      });
   }
}
