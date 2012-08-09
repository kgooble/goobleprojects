package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GLAndroidActivity extends Activity {
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.main);
      
      Button viewButton = (Button) findViewById(R.id.view_puzzles_button);
      viewButton.setOnClickListener(new GoToViewPuzzles(this));
      Button newButton = (Button) findViewById(R.id.new_puzzle_button);
      newButton.setOnClickListener(new GoToNewPuzzle(this));
   }
   
   private final static class GoToNewPuzzle implements OnClickListener{

      private final GLAndroidActivity activity;

      public GoToNewPuzzle(GLAndroidActivity activity) {
         this.activity = activity;
      }

      public void onClick(View v) {
         Intent intent = new Intent(activity, PuzzleActivity.class);
         intent.setAction(Intent.ACTION_INSERT);
         activity.startActivity(intent);
      }
   }
   
   private final static class GoToViewPuzzles implements OnClickListener{

      private final GLAndroidActivity activity;

      public GoToViewPuzzles(GLAndroidActivity activity) {
         this.activity = activity;
      }

      public void onClick(View v) {
         Intent intent = new Intent(activity, ViewPuzzlesActivity.class);
         activity.startActivity(intent);
      }
      
   }
}