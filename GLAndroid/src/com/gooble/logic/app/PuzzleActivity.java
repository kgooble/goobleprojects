package com.gooble.logic.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.gooble.logic.app.db.entity.PuzzleHelper;
import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.hints.HintsActivity;
import com.gooble.logic.app.relations.RelationsActivity;
import com.gooble.logic.app.variables.VariablesActivity;

public class PuzzleActivity extends Activity {
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.edit_puzzle);
      attachActivityLaunchingOnclickToButton(R.id.variables_button, VariablesActivity.class);
      attachActivityLaunchingOnclickToButton(R.id.relations_button, RelationsActivity.class);
      attachActivityLaunchingOnclickToButton(R.id.hints_button, HintsActivity.class);
      
      Intent intent = getIntent();
      Long puzzleId = null;
      if (Intent.ACTION_EDIT.equals(intent.getAction())){
         puzzleId = (Long) intent.getExtras().get("puzzleid");
      }
      
      // load puzzle data
      final PuzzleHelper helper = new PuzzleHelper(this);
      final Puzzle puzzle = helper.getOrCreate((Long) puzzleId);
      
      final EditText puzzleName = (EditText)findViewById(R.id.puzzle_name);
      puzzleName.setText(puzzle.getName());
      
      Button saveButton = (Button) findViewById(R.id.save_button);
      saveButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            String name = puzzleName.getText().toString();
            puzzle.setName(name);
            helper.store(puzzle);
         }
      });
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
